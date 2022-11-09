package inicio;

import carrinhocompra.modelo.CarrinhoCompra;
import carrinhocompra.modelo.CarrinhoCompraItem;
import config.Config;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import produto.modelo.Produto;
import produto.modelo.ProdutoDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe que implementa o controle da ação de formar a página inicial da
 * aplicação
 */
public class InicioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            List<Produto> produtosEmEstoque = produtoDAO.obterTodosEmEstoque();
            request.setAttribute("produtosEmEstoque", produtosEmEstoque);
        } catch (SQLException ex) {
        }
        Cookie carrinhoCompra = null;
        Cookie[] cookies = request.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals(Config.COOKIE_CARRINHOCOMPRA)) {
                carrinhoCompra = cookies[i];
                break;
            }
        }
        if (carrinhoCompra == null) {
            carrinhoCompra = new Cookie(Config.COOKIE_CARRINHOCOMPRA, "");
        } else {
            List<CarrinhoCompraItem> produtosCarrinhoCompra = (List<CarrinhoCompraItem>) CarrinhoCompra.obterCarrinhoCompra(carrinhoCompra.getValue());
            if (produtosCarrinhoCompra != null && !produtosCarrinhoCompra.isEmpty()) {
                request.setAttribute("produtosCarrinhoCompra", produtosCarrinhoCompra);
            }
        }
        carrinhoCompra.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(carrinhoCompra);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

}

package carrinhocompra.controle;

import carrinhocompra.modelo.CarrinhoCompra;
import config.Config;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe que implementa o controle da ação de remover um produto no carrinho de
 * compra
 */
public class RemoverCarrinhoProdutoServlet extends HttpServlet {

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
        int produtoId = Integer.parseInt(request.getParameter("produtoId"));
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals(Config.COOKIE_CARRINHOCOMPRA)) {
                cookie = cookies[i];
                break;
            }
        }
        String novaString = CarrinhoCompra.removerProduto(cookie.getValue(), produtoId);
        cookie.setValue(novaString);
        response.addCookie(cookie);
        request.setAttribute("mensagem", "Produto removido do carrinho de compra");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio");
        dispatcher.forward(request, response);
    }

}

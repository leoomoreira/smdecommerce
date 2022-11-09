package produto.controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import produto.modelo.Produto;
import produto.modelo.ProdutoDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe que implementa o controle da ação de listar os produtos existentes
 */
public class ListarProdutoServlet extends HttpServlet {

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
        List<Produto> produtos = null;
        try {
            produtos = produtoDAO.obterTodos();
            if (produtos == null || produtos.isEmpty()) {
                request.setAttribute("mensagem", "Nenhum produto encontrado");
            } else {
                request.setAttribute("produtos", produtos);
            }
        } catch (SQLException ex) {
            request.setAttribute("mensagem", ex.getMessage());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/administrador/listarProduto.jsp");
        dispatcher.forward(request, response);
    }

}

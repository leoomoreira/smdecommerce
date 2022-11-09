package produto.controle;

import java.io.IOException;
import java.sql.SQLException;
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
 * Classe que implementa o controle da ação de mostrar um produto existente
 */
public class MostrarProdutoServlet extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("id"));
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = null;
        try {
            produto = produtoDAO.obter(id);
            if (produto == null) {
                request.setAttribute("mensagem", "Produto não encontrado");
            } else {
                request.setAttribute("produto", produto);
            }
        } catch (SQLException ex) {
            request.setAttribute("mensagem", ex.getMessage());
        }
        if (produto == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("ListarProduto");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/administrador/alterarProduto.jsp");
            dispatcher.forward(request, response);
        }
    }

}

package usuario.controle;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import usuario.modelo.UsuarioDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe que implementa o controle da ação de inserir um novo cliente
 */
public class InserirNovoClienteServlet extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String mensagem = null;
        try {
            usuarioDAO.inserir(nome, endereco, email, login, senha);
            mensagem = "Cliente inserido com sucesso";
        } catch (SQLException ex) {
            mensagem = "Não foi possível inserir o cliente";
        }
        request.setAttribute("mensagem", mensagem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrar.jsp");
        dispatcher.forward(request, response);
    }

}

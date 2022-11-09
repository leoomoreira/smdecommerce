package acesso.controle;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import usuario.modelo.Usuario;
import usuario.modelo.UsuarioDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe que implementa o controle da ação de login de usuário
 */
public class LoginServlet extends HttpServlet {

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
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean sucesso = false;
        String perfil = null;
        try {
            Usuario usuario = usuarioDAO.obter(login);
            if (usuario != null && usuario.getSenha().equals(senha)) {
                HttpSession sessao = request.getSession(true);
                if (usuario.getAdministrador()) {
                    sessao.setAttribute("administrador", usuario);
                    perfil = "administrador";
                } else {
                    sessao.setAttribute("cliente", usuario);
                    perfil = "cliente";
                }
                sucesso = true;
            }
        } catch (SQLException ex) {
            sucesso = false;
        }
        if (!sucesso) {
            request.setAttribute("mensagem", "Login ou senha incorreta");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/" + perfil + "/principal.jsp");
            dispatcher.forward(request, response);
        }
    }

}

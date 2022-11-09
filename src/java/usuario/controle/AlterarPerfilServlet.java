package usuario.controle;

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
 * Classe que implementa a ação de alterar o perfil de usuário
 */
public class AlterarPerfilServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        boolean administrador = false;
        if (session.getAttribute("administrador") != null) {
            administrador = true;
        }
        Usuario usuario = null;
        if (administrador) {
            usuario = (Usuario) session.getAttribute("administrador");
        } else {
            usuario = (Usuario) session.getAttribute("cliente");
        }
        int id = usuario.getId();
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String mensagem = null;
        try {
            usuarioDAO.alterar(nome, endereco, email, login, senha, id);
            mensagem = "Usuário alterado com sucesso";
            usuario = usuarioDAO.obter(id);
            if (administrador) {
                session.setAttribute("administrador", usuario);
            } else {
                session.setAttribute("cliente", usuario);
            }
        } catch (SQLException ex) {
            mensagem = "Não foi possível alterar o usuário";
        }
        request.setAttribute("mensagem", mensagem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("VerPerfil");
        dispatcher.forward(request, response);
    }

}

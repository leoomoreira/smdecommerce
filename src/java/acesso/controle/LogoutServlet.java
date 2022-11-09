package acesso.controle;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe que implementa o controle da ação de logout de usuário
 */
public class LogoutServlet extends HttpServlet {

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
        HttpSession sessao = request.getSession();
        sessao.invalidate();
        if (request.getAttribute("mensagem") == null) {
            request.setAttribute("mensagem", "Sua sessão foi encerrada");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio");
        dispatcher.forward(request, response);
    }
}

package produto.controle;

import static config.Config.UPLOAD_FOTO_PRODUTO_CAMINHO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import javax.servlet.ServletContext;
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
 * Classe que implementa o controle da ação de mostrar a foto de um produto
 * existente
 */
public class ExibirFotoProdutoServlet extends HttpServlet {

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
        } catch (SQLException ex) {
            produto = null;
        }
        if (produto != null && produto.getFoto() != null && produto.getFoto().trim().length() > 0) {
            File arquivoFoto = new File(UPLOAD_FOTO_PRODUTO_CAMINHO + produto.getFoto());
            if (arquivoFoto.exists()) {
                ServletContext context = this.getServletContext();
                String mimeType = context.getMimeType(arquivoFoto.getAbsolutePath());
                if (mimeType == null) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                response.setContentLengthLong(arquivoFoto.length());
                response.setContentType(mimeType);
                FileInputStream in = new FileInputStream(arquivoFoto);
                OutputStream out = response.getOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                in.close();
                out.close();
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
    }

}

package produto.controle;

import static config.Config.UPLOAD_FOTO_PRODUTO_CAMINHO;
import static config.Config.UPLOAD_FOTO_PRODUTO_CAMINHO_TEMP;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import produto.modelo.ProdutoDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe que implementa o controle da ação de alterar um produto existente
 */
public class AlterarProdutoServlet extends HttpServlet {

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
        int id = -1;
        String descricao = null;
        int quantidade = -1;
        double preco = -1;
        FileItem foto = null;
        String fotoAtual = null;
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            boolean sucesso = false;
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setRepository(new File(UPLOAD_FOTO_PRODUTO_CAMINHO_TEMP));

                ServletFileUpload upload = new ServletFileUpload(factory);

                List<FileItem> itens = upload.parseRequest(request);
                Iterator<FileItem> iter = itens.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (item.isFormField() && item.getFieldName().equals("id")) {
                        id = Integer.parseInt(item.getString());
                    }
                    if (item.isFormField() && item.getFieldName().equals("descricao")) {
                        descricao = item.getString();
                    }
                    if (item.isFormField() && item.getFieldName().equals("quantidade")) {
                        quantidade = Integer.parseInt(item.getString());
                    }
                    if (item.isFormField() && item.getFieldName().equals("preco")) {
                        preco = Double.parseDouble(item.getString());
                    }
                    if (item.isFormField() && item.getFieldName().equals("fotoAtual")) {
                        fotoAtual = item.getString();
                    }
                    if (!item.isFormField() && item.getFieldName().equals("foto")) {
                        foto = item;
                    }
                }
                if (id != -1) {
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    if (foto == null || foto.getName() == null || foto.getName().trim().length() == 0) {
                        produtoDAO.alterar(id, descricao, quantidade, preco, fotoAtual);
                    } else {
                        File fotoFile = new File(UPLOAD_FOTO_PRODUTO_CAMINHO + id + foto.getName().substring(foto.getName().lastIndexOf(".")));
                        if (fotoFile.exists()) {
                            fotoFile.delete();
                        }
                        foto.write(fotoFile);
                        produtoDAO.alterar(id, descricao, quantidade, preco, id + foto.getName().substring(foto.getName().lastIndexOf(".")));
                    }
                    sucesso = true;
                }
                if (sucesso) {
                    request.setAttribute("mensagem", "Produto alterado com sucesso");
                } else {
                    request.setAttribute("mensagem", "Não foi possível processar o upload da foto deste produto");
                }
            } catch (Exception ex) {
                request.setAttribute("mensagem", "Não foi possível processar o upload da foto deste produto");
            }
        } else {
            request.setAttribute("mensagem", "Não foi possível processar o upload da foto deste produto");
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("MostrarProduto?id=" + id);
        requestDispatcher.forward(request, response);
    }

}

package carrinhocompra.modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import produto.modelo.ProdutoDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe que representa as ações do carrinho de compra
 */
public final class CarrinhoCompra {

    /**
     * Construtor privado
     */
    private CarrinhoCompra() {

    }

    /**
     * Método que transforma a string do carrinho de compra em uma lista de
     * itens do carrinho de compra na forma de objetos
     *
     * @param string
     * @return
     */
    public static List<CarrinhoCompraItem> obterCarrinhoCompra(String string) {
        List<CarrinhoCompraItem> resultado = new ArrayList<>();
        if (string == null || string.trim().length() == 0) {
            return resultado;
        } else {
            if (string.contains("&")) {
                String[] itens = string.split("&");
                for (int i = 0; i < itens.length; i++) {
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    CarrinhoCompraItem cci = new CarrinhoCompraItem();
                    int produtoId = Integer.parseInt(itens[i].split("@")[0]);
                    int produtoQuantidade = Integer.parseInt(itens[i].split("@")[1]);
                    try {
                        cci.setProduto(produtoDAO.obter(produtoId));
                    } catch (SQLException ex) {
                        cci.setProduto(null);
                    }
                    cci.setQuantidade(produtoQuantidade);
                    resultado.add(cci);
                }
            } else {
                ProdutoDAO produtoDAO = new ProdutoDAO();
                CarrinhoCompraItem cci = new CarrinhoCompraItem();
                int produtoId = Integer.parseInt(string.split("@")[0]);
                int produtoQuantidade = Integer.parseInt(string.split("@")[1]);
                try {
                    cci.setProduto(produtoDAO.obter(produtoId));
                } catch (SQLException ex) {
                    cci.setProduto(null);
                }
                cci.setQuantidade(produtoQuantidade);
                resultado.add(cci);
            }
            return resultado;
        }
    }

    /**
     * Método utilizado para adicionar um produto ou atualizar a quantidade do
     * produto na string que representa o carrinho de compra
     *
     * @param string
     * @param produtoId
     * @param produtoQuantidade
     * @return
     */
    public static String adicionarProduto(String string, int produtoId, int produtoQuantidade) {
        String resultado = "";
        List<CarrinhoCompraItem> carrinhoCompra = obterCarrinhoCompra(string);
        boolean encontrou = false;
        for (CarrinhoCompraItem cci : carrinhoCompra) {
            if (cci.getProduto().getId() == produtoId) {
                cci.setQuantidade(cci.getQuantidade() + produtoQuantidade);
                encontrou = true;
                break;
            }
        }
        if (!encontrou) {
            CarrinhoCompraItem cci = new CarrinhoCompraItem();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            try {
                cci.setProduto(produtoDAO.obter(produtoId));
            } catch (SQLException ex) {
                cci.setProduto(null);
            }
            cci.setQuantidade(produtoQuantidade);
            carrinhoCompra.add(cci);
        }
        for (int i = 0; i < carrinhoCompra.size(); i++) {
            CarrinhoCompraItem cci = carrinhoCompra.get(i);
            resultado += cci.getProduto().getId() + "@" + cci.getQuantidade();
            if (i < carrinhoCompra.size() - 1) {
                resultado += "&";
            }
        }
        return resultado;
    }

    /**
     * Método utilizado para remover um produto da string que representa o
     * carrinho de compra
     *
     * @param string
     * @param produtoId
     * @return
     */
    public static String removerProduto(String string, int produtoId) {
        String resultado = "";
        List<CarrinhoCompraItem> carrinhoCompra = obterCarrinhoCompra(string);
        for (int i = 0; i < carrinhoCompra.size(); i++) {
            CarrinhoCompraItem cci = carrinhoCompra.get(i);
            if (cci.getProduto().getId() == produtoId) {
                carrinhoCompra.remove(i);
                break;
            }
        }
        for (int i = 0; i < carrinhoCompra.size(); i++) {
            CarrinhoCompraItem cci = carrinhoCompra.get(i);
            resultado += cci.getProduto().getId() + "@" + cci.getQuantidade();
            if (i < carrinhoCompra.size() - 1) {
                resultado += "&";
            }
        }
        return resultado;
    }

}

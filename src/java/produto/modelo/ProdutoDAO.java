package produto.modelo;

import static config.Config.JDBC_DRIVER;
import static config.Config.JDBC_PASSWORD;
import static config.Config.JDBC_URL;
import static config.Config.JDBC_USER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe que implementa o padrão DAO para a entidade produto
 */
public class ProdutoDAO {

    /**
     * Método utilizado para obter todos os produtos
     *
     * @return
     * @throws SQLException
     */
    public List<Produto> obterTodos() throws SQLException {
        List<Produto> resultado = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT id, descricao, quantidade, preco, foto FROM produto")) {
                while (resultSet.next()) {
                    Produto p = new Produto();
                    p.setId(resultSet.getInt("id"));
                    p.setDescricao(resultSet.getString("descricao"));
                    p.setQuantidade(resultSet.getInt("quantidade"));
                    p.setPreco(resultSet.getDouble("preco"));
                    p.setFoto(resultSet.getString("foto"));
                    resultado.add(p);
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        return resultado;
    }
    
    /**
     * Método utilizado para obter todos os produtos em estoque
     *
     * @return
     * @throws SQLException
     */
    public List<Produto> obterTodosEmEstoque() throws SQLException {
        List<Produto> resultado = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT id, descricao, quantidade, preco, foto FROM produto WHERE quantidade > 0")) {
                while (resultSet.next()) {
                    Produto p = new Produto();
                    p.setId(resultSet.getInt("id"));
                    p.setDescricao(resultSet.getString("descricao"));
                    p.setQuantidade(resultSet.getInt("quantidade"));
                    p.setPreco(resultSet.getDouble("preco"));
                    p.setFoto(resultSet.getString("foto"));
                    resultado.add(p);
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        return resultado;
    }

    /**
     * Método utilizado para obter um produto pelo identificador
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Produto obter(int id) throws SQLException {
        Produto p = null;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, quantidade, preco, foto FROM produto WHERE id = ?")) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        p = new Produto();
                        p.setId(resultSet.getInt("id"));
                        p.setDescricao(resultSet.getString("descricao"));
                        p.setQuantidade(resultSet.getInt("quantidade"));
                        p.setPreco(resultSet.getDouble("preco"));
                        p.setFoto(resultSet.getString("foto"));
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        if (p == null) {
            throw new SQLException("Registro não encontrado");
        }
        return p;
    }
    
    /**
     * Método utilizado para alterar os dados de um produto
     * 
     * @param id
     * @param descricao
     * @param quantidade
     * @param preco
     * @param foto
     * @throws SQLException 
     */
    public void alterar(int id, String descricao, int quantidade, double preco, String foto) throws SQLException {
        int resultado = 0;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD); 
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produto SET descricao = ?, quantidade = ?, preco = ?, foto = ? WHERE id = ?")) {
                preparedStatement.setString(1, descricao);
                preparedStatement.setInt(2, quantidade);
                preparedStatement.setDouble(3, preco);
                preparedStatement.setString(4, foto);
                preparedStatement.setInt(5, id);
                resultado = preparedStatement.executeUpdate();
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        if (resultado == 0) {
            throw new SQLException("Registro não foi atualizado com sucesso");
        }
    }

}

package usuario.modelo;

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
 * Classe que implementa o padrão DAO para a entidade usuário
 */
public class UsuarioDAO {

    /**
     * Método utilizado para obter todos os usuários
     * 
     * @return
     * @throws SQLException 
     */
    public List<Usuario> obterTodos() throws SQLException {
        List<Usuario> resultado = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD); 
                Statement statement = connection.createStatement(); 
                ResultSet resultSet = statement.executeQuery("SELECT id, nome, endereco, email, login, senha, administrador FROM usuario")) {
                while (resultSet.next()) {
                    Usuario u = new Usuario();
                    u.setId(resultSet.getInt("id"));
                    u.setNome(resultSet.getString("nome"));
                    u.setEndereco(resultSet.getString("endereco"));
                    u.setEmail(resultSet.getString("email"));
                    u.setLogin(resultSet.getString("login"));
                    u.setSenha(resultSet.getString("senha"));
                    u.setAdministrador(resultSet.getBoolean("administrador"));
                    resultado.add(u);
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        return resultado;
    }

    /**
     * Método utilizado para obter um usuário pelo identificador
     * 
     * @param id
     * @return
     * @throws SQLException 
     */
    public Usuario obter(int id) throws SQLException {
        Usuario u = null;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD); 
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, endereco, email, login, senha, administrador FROM usuario WHERE id = ?")) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        u = new Usuario();
                        u.setId(resultSet.getInt("id"));
                        u.setNome(resultSet.getString("nome"));
                        u.setEndereco(resultSet.getString("endereco"));
                        u.setEmail(resultSet.getString("email"));
                        u.setLogin(resultSet.getString("login"));
                        u.setSenha(resultSet.getString("senha"));
                        u.setAdministrador(resultSet.getBoolean("administrador"));
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        if (u == null) {
            throw new SQLException("Registro não encontrado");
        }
        return u;
    }

    /**
     * Método utilizado para obter um usuário pelo login
     * 
     * @param login
     * @return
     * @throws SQLException 
     */
    public Usuario obter(String login) throws SQLException {
        Usuario u = null;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD); 
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, endereco, email, login, senha, administrador FROM usuario WHERE login = ?")) {
                preparedStatement.setString(1, login);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        u = new Usuario();
                        u.setId(resultSet.getInt("id"));
                        u.setNome(resultSet.getString("nome"));
                        u.setEndereco(resultSet.getString("endereco"));
                        u.setEmail(resultSet.getString("email"));
                        u.setLogin(resultSet.getString("login"));
                        u.setSenha(resultSet.getString("senha"));
                        u.setAdministrador(resultSet.getBoolean("administrador"));
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        if (u == null) {
            throw new SQLException("Registro não encontrado");
        }
        return u;
    }

    /**
     * Método utilizado para inserir um usuário
     * 
     * @param nome
     * @param endereco
     * @param email
     * @param login
     * @param senha
     * @throws SQLException 
     */
    public void inserir(String nome, String endereco, String email, String login, String senha) throws SQLException {
        int resultado = 0;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD); 
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO usuario (nome, endereco, email, login, senha, administrador) VALUES (?, ?, ?, ?, ?, false)")) {
                preparedStatement.setString(1, nome);
                preparedStatement.setString(2, endereco);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, login);
                preparedStatement.setString(5, senha);
                resultado = preparedStatement.executeUpdate();
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        if (resultado == 0) {
            throw new SQLException("Registro não foi inserido com sucesso");
        }
    }

    /**
     * Método utilizado para alterar os dados de um usuário
     * 
     * @param nome
     * @param endereco
     * @param email
     * @param login
     * @param senha
     * @param id
     * @throws SQLException 
     */
    public void alterar(String nome, String endereco, String email, String login, String senha, int id) throws SQLException {
        int resultado = 0;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD); 
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE usuario SET nome = ?, endereco = ?, email = ?, login = ?, senha = ? WHERE id = ?")) {
                preparedStatement.setString(1, nome);
                preparedStatement.setString(2, endereco);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, login);
                preparedStatement.setString(5, senha);
                preparedStatement.setInt(6, id);
                resultado = preparedStatement.executeUpdate();
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        if (resultado == 0) {
            throw new SQLException("Registro não foi alterado com sucesso");
        }
    }

    /**
     * Método utilizado para remover um usuário pelo identificador
     * 
     * @param id
     * @throws SQLException 
     */
    public void remover(int id) throws SQLException {
        int resultado = 0;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);  
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM usuario WHERE id = ?")) {
                preparedStatement.setInt(1, id);
                resultado = preparedStatement.executeUpdate();
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        if (resultado == 0) {
            throw new SQLException("Registro não foi removido com sucesso");
        }
    }

}

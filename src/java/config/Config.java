package config;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe implementada para ter acesso ao arquivo de configuração
 */
public final class Config {

    public static final String JDBC_DRIVER = getPropriedade("JDBC_DRIVER");
    public static final String JDBC_URL = getPropriedade("JDBC_URL");
    public static final String JDBC_USER = getPropriedade("JDBC_USER");
    public static final String JDBC_PASSWORD = getPropriedade("JDBC_PASSWORD");
    
    public static final String COOKIE_CARRINHOCOMPRA = getPropriedade("COOKIE_CARRINHOCOMPRA");
    
    public static final String UPLOAD_FOTO_PRODUTO_CAMINHO = getPropriedade("UPLOAD_FOTO_PRODUTO_CAMINHO");
    public static final String UPLOAD_FOTO_PRODUTO_CAMINHO_TEMP = getPropriedade("UPLOAD_FOTO_PRODUTO_CAMINHO_TEMP");

    /**
     * Construtor privado para não permitir instanciação
     */
    private Config() {

    }

    /**
     * Método utilizado para retornar os dados do arquivo de configuração
     *
     * @param chave
     * @return
     */
    private static String getPropriedade(String chave) {
        Properties properties = new Properties();
        try {
            properties.load(Config.class.getResourceAsStream("config.properties"));
            return properties.getProperty(chave);
        } catch (IOException ex) {
            return null;
        }
    }

}

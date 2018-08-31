package dao.connection;

import dao.exception.connection.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionSetter {

    private static final Logger logger = LogManager.getLogger(ConnectionSetter.class);

    private static DBResourceManager dbResourceManager = DBResourceManager.getInstance();

    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String AUTO_RECONNECT = "autoReconnect";
    private static final String CHARACTER_ENCODING = "characterEncoding";
    private static final String USE_UNICODE = "useUnicode";

    static Connection setConnection() throws ConnectionPoolException {
        String url = dbResourceManager.getValue(DBParameter.DB_URL);
        Properties properties = setProperties(new Properties());
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, properties);
        } catch (SQLException e){
            logger.log(Level.ERROR, "Setting connection failed!", e);
            throw new ConnectionPoolException();
        }
        return connection;
    }

    private static Properties setProperties(Properties properties){
        properties.put(USER, dbResourceManager.getValue(DBParameter.DB_USER));
        properties.put(PASSWORD, dbResourceManager.getValue(DBParameter.DB_PASSWORD));
        properties.put(AUTO_RECONNECT, dbResourceManager.getValue(DBParameter.DB_AUTO_RECONNECT));
        properties.put(CHARACTER_ENCODING, dbResourceManager.getValue(DBParameter.DB_ENCODING));
        properties.put(USE_UNICODE, dbResourceManager.getValue(DBParameter.DB_USE_UNICODE));

        return properties;
    }
}

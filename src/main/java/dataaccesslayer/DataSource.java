package dataaccesslayer;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *	data source that follows Singleton pattern.
 *  Grabs connection credentials from properties file. private data source constructor
 *  Note: Login auth not implemented yet.
 *	@author Lily Schmeer
 *	@version 1.0
 *	@since JDK 21.0.4
 */
public class DataSource {
    private static Connection connection = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    /**
     * Private constructor
     */
    private DataSource() {

    }

    public static synchronized Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                openProperties();
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);

            } else {

                System.out.println("Existing connection already exists, cannot create new one.");

            }
            // multi-catch sqlexception, classnotfoundexception
        } catch (SQLException | ClassNotFoundException exc) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, exc);

        }
        return connection;
    }

    private static void openProperties() {
        Properties props = new Properties();
        try (InputStream in = DataSource.class.getClassLoader().getResourceAsStream("prop/database.properties")) {
            props.load(in);

//      Trim to prevent any issues with trailing/leading whitespace in the properties file
            url = props.getProperty("jdbc.url").trim();
            username = props.getProperty("jdbc.username").trim();
            password = props.getProperty("jdbc.password").trim();
        } catch (IOException exc) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, exc);
        }
    }
}

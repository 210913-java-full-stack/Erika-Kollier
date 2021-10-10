package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 *
 */
public class ConnectionManagerCat {
    private static Connection conn;

    private ConnectionManagerCat() {

    }


    /*
    This is a static method for returning a connection in the factory singleton design pattern
     */
    public static Connection getConnection() {
        if(conn == null) {
            try {
                ClassLoader cl = Thread.currentThread().getContextClassLoader();

                InputStream input = cl.getResourceAsStream("connection.properties");
                Properties props = new Properties();
                props.load(input);

                Class.forName("org.mariadb.jdbc.Driver");

                //"jdbc:mariadb://hostname:port/databaseName?user=username&password=password"
                String connString = "jdbc:mariadb://" +
                        props.getProperty("hostname") + ":" +
                        props.getProperty("port") + "/" +
                        props.getProperty("databaseName") + "?user=" +
                        props.getProperty("user") + "&password=" +
                        props.getProperty("password");

                conn = DriverManager.getConnection(connString);
            } catch(SQLException | IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

}

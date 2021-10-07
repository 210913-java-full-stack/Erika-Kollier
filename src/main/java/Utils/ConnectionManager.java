package Utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 *
 */
public class ConnectionManager {
    private static Connection conn;
    private static String myDBProp = "src/main/resources/connection.properties";

    private ConnectionManager() {

    }


    /*
    This is a static method for returning a connection in the factory singleton design pattern
     */
    public static Connection getConnection() {
        if(conn == null) {
            try {
                Properties props = new Properties();
                FileReader connectionProperties = new FileReader(myDBProp);
                props.load(connectionProperties);

                //"jdbc:mariadb://hostname:port/databaseName?user=username&password=password"
                String connString = "jdbc:mariadb://" +
                        props.getProperty("hostname") + ":" +
                        props.getProperty("port") + "/" +
                        props.getProperty("databaseName") + "?user=" +
                        props.getProperty("user") + "&password=" +
                        props.getProperty("password");

                conn = DriverManager.getConnection(connString);
            } catch(SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

}

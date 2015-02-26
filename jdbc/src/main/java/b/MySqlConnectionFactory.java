package b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ze.liu
 * @since 2014/5/22
 */
public class MySqlConnectionFactory implements ConnectionFactory {

    private Connection connection;

    @Override
    public Connection getConnection() {
        if(connection!=null) {
            return connection;
        }
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "root";
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

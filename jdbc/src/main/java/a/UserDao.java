package a;

import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author ze.liu
 * @since 2014/5/22
 */
public class UserDao {

    public void addUser(User user) {
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "root";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            String sql = "insert into hello (name, age) values (?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
//            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        User u = new User("hello","123");
        UserDao userDao = new UserDao();
        userDao.addUser(u);
    }
}

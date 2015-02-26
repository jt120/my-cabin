package c;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ze.liu
 * @since 2014/5/22
 */
public class UserDao extends AbstractDao {



    public static void main(String[] args) {
        AbstractDao<User> userDao = new UserDao();
        //protected类型的方法，实例如果要访问，必须在同一个包中
        User u = userDao.query(1);
        System.out.println(u);
//        User user = new User("a", "123");
//        userDao.add(user);

    }

    @Override
    protected Object doQuery(int id) {
        String sql = "select name,password from t_user where id=?";
        PojoConverter<User> pojoConverter = new PojoConverter<User>(User.class);

        PreparedStatement preparedStatement = getpPreparedStatement(sql, id);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return pojoConverter.fromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(preparedStatement);
        return null;
    }


}

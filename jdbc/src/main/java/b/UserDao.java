package b;

import model.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ze.liu
 * @since 2014/5/22
 */
public class UserDao extends AbstractDao {

    public void add(User user) {

        String sql = "insert into t_user (name, password) values (?,?)";
        Converter converter = getConverter(UserDao.class);
        Object[] objects = converter.makeObject(user);
        PreparedStatement preparedStatement = getpPreparedStatement(sql, objects);
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(preparedStatement);
        close();
    }


    public static void main(String[] args) {
        AbstractDao<User> userDao = new UserDao();
//        User user = new User("a", "123");
//        userDao.add(user);

        User u = userDao.query(1);
        System.out.println(u);

        /*String s = "b.UserDao$UserConverter";
        try {
            Class<?> clz = Class.forName(s);
            Constructor[] constructors = clz.getConstructors();
            int n = constructors[0].getModifiers();
            System.out.println(Modifier.toString(n));
            clz.newInstance();
            Converter converter1 = (UserConverter) clz.newInstance();
            Converter converter2 = (Converter) constructors[0].newInstance(new UserDao());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    protected Object doQuery(int id) {
        String sql = "select name,password from t_user where id=?";
        Converter<User> converter = getConverter(UserDao.class);

        PreparedStatement preparedStatement = getpPreparedStatement(sql, id);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return converter.fromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(preparedStatement);
        return null;
    }


    public static class UserConverter implements Converter <User> {

        @Override
        public Object[] makeObject(User user) {
            Object[] objects = new Object[2];
            objects[0] = user.getName();
            objects[1] = user.getPassword();
            return objects;
        }

        @Override
        public User fromResultSet(ResultSet resultSet) {
            User user = new User();
            try {
                user.setName(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
                return user;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}

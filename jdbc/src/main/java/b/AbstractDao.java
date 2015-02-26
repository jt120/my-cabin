package b;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author ze.liu
 * @since 2014/5/22
 */
public abstract class AbstractDao <T> {

    private ConnectionFactory connectionFactory;

    public AbstractDao() {
        ConnectionFactory connectionFactory = new MySqlConnectionFactory();
        this.setConnectionFactory(connectionFactory);
    }


    protected PreparedStatement getpPreparedStatement(String sql, Object ... objs) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connectionFactory.getConnection().prepareStatement(sql);
            for (int i = 0; i < objs.length; i++) {
                preparedStatement.setObject(i+1, objs[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void close() {
        connectionFactory.close();
    }

    public void close(PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected Converter getConverter(Class clz) {
        String name = clz.getName();
        String converterName = name + "$UserConverter";
        try {
            Class nClz = Class.forName(converterName);
            Converter converter = (Converter) nClz.newInstance();
            return converter;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected T query(int id) {
        try {
            return doQuery(id);
        } finally {
            this.close();
        }
    }

    protected abstract T doQuery(int id);
}

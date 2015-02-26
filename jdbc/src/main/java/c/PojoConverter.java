package c;

import model.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ze.liu
 * @since 2014/5/23
 */
public class PojoConverter <T> {

    private Class<T> clz;

    private Field[] fields;

    public PojoConverter(Class<T> clz) {
        this.clz = clz;
        this.fields = parseClz(clz);

    }

    public Field[] parseClz(Class<T> clz) {
        return clz.getDeclaredFields();
    }

    public T fromResultSet(ResultSet resultSet) {
        T t = null;
        try {
            t = clz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for(Field field:fields) {
            String fieldName = field.getName();
            try {
                String s = resultSet.getString(fieldName);
                String nName = "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1,
                        fieldName.length());
                Method method = clz.getMethod(nName, String.class);
                method.invoke(t, s);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return t;
    }
}

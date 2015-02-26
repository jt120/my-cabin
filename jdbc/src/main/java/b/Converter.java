package b;

import java.sql.ResultSet;

/**
 * @author ze.liu
 * @since 2014/5/22
 */
public interface Converter <T> {

    Object[] makeObject(T t);

    T fromResultSet(ResultSet resultSet);
}

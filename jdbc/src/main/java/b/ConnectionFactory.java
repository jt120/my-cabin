package b;

import java.sql.Connection;

/**
 * @author ze.liu
 * @since 2014/5/22
 */
public interface ConnectionFactory {

    Connection getConnection();

    void close();
}

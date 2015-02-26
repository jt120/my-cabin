package ne;

import com.jt.cabin.util.DbUtil;
import model.User;
import org.apache.commons.dbutils.AsyncQueryRunner;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.wrappers.StringTrimmedResultSet;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * since 2015/2/25.
 */
public class DbUtilsTest {

    Connection conn;

    @Before
    public void init() {
        conn = DbUtil.getConn();
    }

    @Test
    public void test01() throws Exception {
        try {
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<User> rt = new BeanHandler(User.class);
            User query = queryRunner.query(conn, "select name, password from t_user where name=?",
                    rt,"hello");
            System.out.println(query);
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    @Test
    public void testInsert() throws Exception {
        try {
            QueryRunner q = new QueryRunner();
            int count = q.update(conn, "insert into t_user (name, password) values (?,?)", "hello1", "123");
            System.out.println("insert count " + count);
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    @Test
    public void testQueryMore() throws Exception {
        QueryRunner q = new QueryRunner();
        try {
            ResultSetHandler<List<User>> resultSetHandler = new BeanListHandler<User>(User.class);
            List<User> result = q.query(conn, "select * from t_user where name like ?", resultSetHandler, "hello%");
            System.out.println(result);
        } finally {
            DbUtils.closeQuietly(conn);
        }

    }

    @Test
    public void testQueryOpt() throws Exception {
        QueryRunner q = new QueryRunner();
        ResultSetHandler<User> h = new ResultSetHandler<User>() {
            @Override
            public User handle(ResultSet rs) throws SQLException {
                boolean next = rs.next();
                if (!next) {
                    return null;
                }
                User user = new User();
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        };
        try {
            User user = q.query(conn, "select * from t_user where name = ?", h, "hello2");
            System.out.println(user);
        } finally {
            DbUtils.closeQuietly(conn);
        }

    }

    @Test
    public void testQueryAsyc() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(1);
        try {
            AsyncQueryRunner q = new AsyncQueryRunner(es);
            ResultSetHandler<User> h = new BeanHandler<User>(User.class);
            Future<User> user = q.query(conn, "select * from t_user where name = ?", h, "test2");
            User user1 = user.get(10, TimeUnit.SECONDS);
            System.out.println(user1);
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    @Test
    public void testQueryTrim() throws Exception {
        QueryRunner q = new QueryRunner();
        ResultSetHandler<User> h = new ResultSetHandler<User>() {
            @Override
            public User handle(ResultSet rs) throws SQLException {
                ResultSet wrap = StringTrimmedResultSet.wrap(rs);
                boolean next = wrap.next();
                if (!next) {
                    return null;
                }
                User user = new User();
                user.setName(wrap.getString("name"));
                user.setPassword(wrap.getString("password"));
                return user;
            }
        };
        try {
            User user = q.query(conn, "select * from t_user where name = ?", h, "test2");
            System.out.println(user);
        } finally {
            DbUtils.closeQuietly(conn);
        }

    }
}

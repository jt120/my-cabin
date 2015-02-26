package com.jt.dao;

import com.jt.bean.User;
import com.jt.cabin.util.DbUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author ze.liu
 * @since 2014/5/16
 */
@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    public UserDaoImpl() {
        log.info("userdao create");
    }

    @Override
    public long add(User user) {
        Connection conn = DbUtil.getConn();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<Long> resultSetHandler = new ScalarHandler<Long>(1);
        try {
            conn.setAutoCommit(false);
            long id = queryRunner.insert(conn, "insert into t_user (name, password) values (?,?)", resultSetHandler,
                    user.getName(), user.getPassword());
            conn.commit();
            return id;
        } catch (SQLException e) {
            log.warn("insert error", e);
            try {
                DbUtils.rollback(conn);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            log.info("conn {}", conn);
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DbUtils.closeQuietly(conn);
        }
        return 0;
    }

    @Override
    public User findByName(String name) {
        Connection conn = DbUtil.getConn();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<User> resultSetHandler = new BeanHandler<User>(User.class);
        try {
            return queryRunner.query(conn, "select id, name, password from t_user where name =" +
                    " ?", resultSetHandler, name);
        } catch (SQLException e) {
            log.warn("find error", e);
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return null;
    }
}

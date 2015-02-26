package com.jt.dao;

import com.jt.bean.Order;
import com.jt.cabin.util.DbUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * since 2015/2/26.
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    private static final Logger log = LoggerFactory.getLogger(OrderDaoImpl.class);


    @Override
    public long add(Order order) {
        Connection conn = DbUtil.getConn();
        QueryRunner queryRunner = new QueryRunner();
        //ResultSetHandler<Long> resultSetHandler = new ScalarHandler<Long>(1);
        try {
            conn.setAutoCommit(false);
            //long id = queryRunner.insert(conn, "insert into t_order (order_no, user_id) values (?,?)",
            //        resultSetHandler, order.getOrderNo(), order.getUser().getId());
            //用update, 不需要传resultSetHandler
            long count = queryRunner.update(conn, "insert into t_order (order_no, user_id) values (?,?)", order.getOrderNo(), order.getUser().getId());
            conn.commit();
            return count;
        } catch (SQLException e) {
            log.warn("insert error", e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DbUtils.closeQuietly(conn);
        }
        return 0;
    }

    /**
     * 如果使用connection, 那么需要自己关闭
     * @param orderNo
     * @return
     */
    @Override
    public Order findByOrderNo(String orderNo) {
        Connection conn = DbUtil.getConn();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<Order> resultSetHandler = new BeanHandler<Order>(Order.class);
        try {
            return queryRunner.query(conn, "select id, order_no orderNO, user_id userId from t_order where order_no =" +
                            " ?", resultSetHandler, orderNo);
        } catch (SQLException e) {
            log.warn("find error", e);
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return null;
    }

    @Override
    public List<Order> findByUserId(long userId) {
        Connection conn = DbUtil.getConn();
        QueryRunner q = new QueryRunner();
        ResultSetHandler<List<Order>> h = new BeanListHandler<Order>(Order.class);
        try {
            return q.query(conn, "select id, order_no orderNo, user_id userId from t_order where user_id = ?", h,
                    userId);
        } catch (SQLException e) {
            log.warn("query error", e);
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return null;
    }
}

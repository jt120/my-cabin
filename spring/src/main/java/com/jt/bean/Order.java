package com.jt.bean;

/**
 * since 2015/2/26.
 */
public class Order {

    private long id;

    private String orderNo;

    private User user;

    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", orderNo='").append(orderNo).append('\'');
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}

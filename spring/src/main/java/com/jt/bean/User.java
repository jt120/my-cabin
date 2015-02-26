package com.jt.bean;

import com.google.common.base.Objects;

/**
 * @author ze.liu
 * @since 2014/5/16
 */
public class User {

    private long id;
    private String name;
    private String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name, this.password);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof User) {
            User user = (User) obj;
            return user.getName().equals(this.getName());
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

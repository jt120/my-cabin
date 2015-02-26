package com.jt.web.model;

import javax.validation.Constraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author ze.liu
 * @since 2014/5/29
 */
public class User {
    @NotNull
    private String name;
    private String password;

    private Group groupList;

    private List<Book> books;
    @Pattern(regexp = "\\d{11}")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Group getGroupList() {
        return groupList;
    }

    public void setGroupList(Group groupList) {
        this.groupList = groupList;
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

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

package com.jt.web.model;

import javax.validation.constraints.NotNull;

/**
 * Created by ze.liu on 2014/7/7.
 */
public class Book {

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

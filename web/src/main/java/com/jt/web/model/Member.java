package com.jt.web.model;

import java.util.List;

/**
 * Created by ze.liu on 2014/7/25.
 */
public class Member {

    private List<Person> persons;

    public Member() {

    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}

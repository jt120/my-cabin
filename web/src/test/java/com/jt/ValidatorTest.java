package com.jt;

import com.jt.web.model.Book;
import com.jt.web.model.Group;
import com.jt.web.model.User;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by ze.liu on 2014/7/7.
 */
public class ValidatorTest {

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    @Test
    public void test01() {
        User user = new User();
        //user.setName("hello");
        user.setMobile("12345678901");
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user);

        for (ConstraintViolation c : constraintViolations) {
            throw new IllegalArgumentException(c.getMessage());
        }
    }

    @Test
    public void test02() {
        User user = new User();
        user.setName("hello");
        Group group = new Group();
        user.setGroupList(group);
        group.setName("haah");
        List<Book> list = new ArrayList<Book>();
        Book book = new Book();
        list.add(book);
        user.setBooks(list);
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user);
        Set<ConstraintViolation<Group>> constraintViolations2 =
                validator.validate(user.getGroupList());
        for(Book book1:user.getBooks()) {
            Set<ConstraintViolation<Book>> constraintViolations3 =
                    validator.validate(book1);
            for (ConstraintViolation c : constraintViolations3) {
                throw new IllegalArgumentException(c.getMessage());
            }
        }

        for (ConstraintViolation c : constraintViolations) {
            throw new IllegalArgumentException(c.getMessage());
        }
        for (ConstraintViolation c : constraintViolations2) {
            throw new IllegalArgumentException(c.getMessage());
        }



    }
}

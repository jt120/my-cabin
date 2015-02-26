package annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author ze.liu
 * @since 2014/6/24
 */
public class Book {

    @Validate(msg = "NotNull")
    String title;

    String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static void main(String[] args) {
        Book book = new Book();
        book.setTitle("java实战");
        Field[] fields = Book.class.getDeclaredFields();
        for(Field field:fields) {
            System.out.println(field);
            if(field.isAnnotationPresent(Validate.class)) {
                Validate v = field.getAnnotation(Validate.class);
                String value = v.msg();
                if(value.equals("NotNull")) {
                    try {
                        if(field.get(book) == null) {
                            System.out.println("title 为 null");
                        } else {
                            System.out.println("title 为 " + field.get(book));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

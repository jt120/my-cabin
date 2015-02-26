package za;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * @author ze.liu
 * @since 2014/6/10
 */
public class Person {

    String name;

    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        Person p = new Person();
        p.setName("hello");
        p.setAge(1);
        //
        //String s = Objects.toStringHelper(p).add("name",p.getName()).add("age",p.getAge()).toString();
        //System.out.println(s);

        try {
            BeanInfo bi = Introspector.getBeanInfo(Person.class);
            PropertyDescriptor[] pds = bi.getPropertyDescriptors();
            for(PropertyDescriptor pd:pds) {
                System.out.println(pd.getPropertyType());
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }


    }
}

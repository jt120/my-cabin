package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ze.liu on 2014/7/1.
 */
public class ParaTest {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        user.setUsername("hello");
        Field[] fields = User.class.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(NotNull.class)) {
                NotNull annotation = f.getAnnotation(NotNull.class);
                String msg = annotation.value();
                String name = f.getName();
                String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
                Method m = User.class.getMethod(methodName);
                String r = (String) m.invoke(user);
                if (r == null) {
                    throw new IllegalArgumentException(msg);
                }
            }
        }
    }
}

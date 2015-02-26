package reflect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ze.liu on 2014/7/1.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {
    String value() default "";
}

package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ze.liu
 * @since 2014/6/24
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {

    String msg() default "";
}

package headfirst.a.unicorn.chain.annot;

import java.lang.annotation.*;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年06月23日 10:48:00
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    String value() default "";
}

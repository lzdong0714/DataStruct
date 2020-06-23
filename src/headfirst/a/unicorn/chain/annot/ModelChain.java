package headfirst.a.unicorn.chain.annot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年06月23日 10:44:00
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ModelChain {
    int chainIndex() default 0;

}

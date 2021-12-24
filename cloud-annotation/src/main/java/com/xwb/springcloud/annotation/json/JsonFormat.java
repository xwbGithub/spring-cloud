package com.xwb.springcloud.annotation.json;


import java.lang.annotation.*;

/**
 * 作用于属性上，用户按要求格式化日期
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JsonFormat {
    String pattern() default "yyyy-MM-dd HH:mm:ss";
}

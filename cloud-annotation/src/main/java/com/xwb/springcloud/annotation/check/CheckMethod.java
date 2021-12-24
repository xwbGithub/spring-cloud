package com.xwb.springcloud.annotation.check;

import java.lang.annotation.*;

@Target(value = {ElementType.METHOD}) //作用于方法
@Retention(RetentionPolicy.RUNTIME) //运行时有效
@Inherited //可以被继承
public @interface CheckMethod {
    String value() default "";
}

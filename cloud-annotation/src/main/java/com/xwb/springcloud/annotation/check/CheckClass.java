package com.xwb.springcloud.annotation.check;

import java.lang.annotation.*;

@Target(value = {ElementType.TYPE}) //作用于类
@Retention(RetentionPolicy.RUNTIME) //运行时有效
@Inherited //可以被继承
public @interface CheckClass {
    String value() default "";

    String msg() default "";
}

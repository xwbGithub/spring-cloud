package com.xwb.springcloud.annotation.check;

import java.lang.annotation.*;

@Target(value = {ElementType.FIELD}) //作用于属性
@Retention(RetentionPolicy.RUNTIME) //运行时有效
@Inherited //可以被继承
public @interface CheckField {
    String value() default "";
}

package com.xwb.springcloud.annotation.check;

import java.lang.annotation.*;

@Target(value = {ElementType.PARAMETER}) //作用于属性
@Retention(RetentionPolicy.RUNTIME) //运行时有效
@Inherited //可以被继承
public @interface CheckParam {
    String value() default "";
}

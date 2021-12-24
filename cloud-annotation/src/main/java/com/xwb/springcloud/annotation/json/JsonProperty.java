package com.xwb.springcloud.annotation.json;


import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JsonProperty {
    String value(); //使用的时候必须给值
}

package com.xwb.springcloud.annotation.json;

import java.lang.annotation.*;

/**
 * 作用在属性上，用于忽略一些属性
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JsonIgnore {
}

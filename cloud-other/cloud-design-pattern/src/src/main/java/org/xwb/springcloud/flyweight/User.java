package org.xwb.springcloud.flyweight;

import lombok.Data;

/**
 * @author Administrator
 * @description
 */
@Data
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }
}

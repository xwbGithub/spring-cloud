package org.xwb.springcloud.prototype;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 羊
 *
 * @author Administrator
 * @description
 */
@Data
@Slf4j
public class Sheep implements Cloneable {
    private String name;
    private int age;
    private String color;

    public Sheep() {
    }

    public Sheep(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Sheep [name=" + name + "],age=[" + age + "],color=[" + color + "]";
    }

    @Override
    protected Object clone() {

        Sheep sheep = null;
        try {
            sheep = (Sheep) super.clone();
        } catch (CloneNotSupportedException e) {
            log.info("克隆失败!{}", e.getMessage());
            e.printStackTrace();
        }

        return sheep;
    }
}

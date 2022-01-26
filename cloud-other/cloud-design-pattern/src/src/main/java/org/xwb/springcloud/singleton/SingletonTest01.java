package org.xwb.springcloud.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description 饿汉式静态变量
 */
@SuppressWarnings("All")
@Slf4j
public class SingletonTest01 {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        log.info("比对结果：{}",singleton==singleton2);
    }
}

/**
 * 饿汉式，(静态变量)
 */

class Singleton {

    /**
     * 1、构造器私有化，外部new
     */
    private Singleton() {

    }

    //本部内部类创建对象实例
    private final static Singleton instance = new Singleton();

    //
    public static Singleton getInstance() {
        return instance;
    }
}

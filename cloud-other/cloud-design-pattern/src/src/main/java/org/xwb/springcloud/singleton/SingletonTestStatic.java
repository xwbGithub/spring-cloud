package org.xwb.springcloud.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description 饿汉式静态代码块
 */

@SuppressWarnings("All")
@Slf4j
public class SingletonTestStatic {
    public static void main(String[] args) {
        SingletonStatic singleton = SingletonStatic.getInstance();
        SingletonStatic singleton2 = SingletonStatic.getInstance();

        log.info("比对结果：{}",singleton == singleton2);
    }
}

/**
 * 饿汉式，(静态代码块)
 */

class SingletonStatic {

    /**
     * 1、构造器私有化，外部new
     */
    private SingletonStatic() {
        instance = new SingletonStatic();
    }

    //本部内部类创建对象实例
    private static SingletonStatic instance;

    //
    public static SingletonStatic getInstance() {
        return instance;
    }
}

package org.xwb.springcloud.singleton.hungry;

import lombok.extern.slf4j.Slf4j;

/**
 * @description 饿汉式
 */
@SuppressWarnings("All")
@Slf4j
public class SingletonHungryManSyncCode {
    public static void main(String[] args) {
        SingletonHungrySyncCode singleton = SingletonHungrySyncCode.getInstance();
        SingletonHungrySyncCode singleton1 = SingletonHungrySyncCode.getInstance();
        log.info("比对结果：{}", singleton == singleton1);
        log.info("singleton :hasCode:{}", singleton.hashCode());
        log.info("singleton1:hasCode:{}", singleton1.hashCode());
    }

}


/**
 * 懒汉式，(静态代码块)
 */

class SingletonHungrySyncCode {

    private static SingletonHungrySyncCode instance;


    private SingletonHungrySyncCode() {
    }

    public static  SingletonHungrySyncCode getInstance() {
        if (null == instance) {
            //进入if再加锁，创建多个实例，不安全(不推荐使用)
            synchronized (SingletonHungrySyncCode.class) {
                instance = new SingletonHungrySyncCode();
            }
        }
        return instance;
    }
}

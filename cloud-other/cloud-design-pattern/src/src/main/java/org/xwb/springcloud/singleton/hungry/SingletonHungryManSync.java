package org.xwb.springcloud.singleton.hungry;

import lombok.extern.slf4j.Slf4j;

/**
 * @description 饿汉式
 */
@SuppressWarnings("All")
@Slf4j
public class SingletonHungryManSync {
    public static void main(String[] args) {
        SingletonHungryManSyncCode singleton = SingletonHungrySync.getInstance();
        SingletonHungryManSyncCode singleton1 = SingletonHungrySync.getInstance();
        log.info("比对结果：{}", singleton == singleton1);
        log.info("singleton :hasCode:{}", singleton.hashCode());
        log.info("singleton1:hasCode:{}", singleton1.hashCode());
    }

}


/**
 * 懒汉式，(静态代码块)
 */

class SingletonHungrySync {

    private static SingletonHungryManSyncCode instance;


    private SingletonHungrySync() {
    }

    //提供一个静态的公有方法，同时加入同步处理代码synchronized， 解决线程安全问题
    public static synchronized SingletonHungryManSyncCode getInstance() {
        if (null == instance) {
            instance = new SingletonHungryManSyncCode();
        }
        return instance;
    }
}

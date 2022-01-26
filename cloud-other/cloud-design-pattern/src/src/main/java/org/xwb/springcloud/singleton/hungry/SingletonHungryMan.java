package org.xwb.springcloud.singleton.hungry;

import lombok.extern.slf4j.Slf4j;

/**
 * @description 饿汉式
 */
@SuppressWarnings("All")
@Slf4j
public class SingletonHungryMan {
    public static void main(String[] args) {
        SingletonHungry singleton=SingletonHungry.getInstance();
        SingletonHungry singleton1=SingletonHungry.getInstance();
        log.info("比对结果：{}",singleton==singleton1);
        log.info("singleton:hasCode:{}",singleton.hashCode());
        log.info("singleton1:hasCode:{}",singleton1.hashCode());
    }

}


/**
 * 饿汉式，(静态代码块)
 */

class SingletonHungry {

    private static SingletonHungry instance;


    private SingletonHungry() {
    }
    //提供一个静态公有方法，当使用到该方法时候，才去创建instance
    //即懒汉式
    public static SingletonHungry getInstance() {
        if(null==instance){
            instance=new SingletonHungry();
        }
        return instance;
    }
}

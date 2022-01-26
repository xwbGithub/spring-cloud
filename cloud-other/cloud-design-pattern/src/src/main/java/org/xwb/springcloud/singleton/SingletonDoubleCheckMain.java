package org.xwb.springcloud.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description 单例双重检查
 */
@Slf4j
public class SingletonDoubleCheckMain {
    public static void main(String[] args) {
        SingletonDoubleCheck singleton = SingletonDoubleCheck.getSingleton();
        SingletonDoubleCheck singleton2 = SingletonDoubleCheck.getSingleton();

        log.info("比对结果：{}", singleton == singleton2);
        log.info("singleton :hashCode：{}", singleton.hashCode());
        log.info("singleton2:hashCode：{}", singleton.hashCode());
    }
}

class SingletonDoubleCheck {

    private static volatile SingletonDoubleCheck singleton;

    private SingletonDoubleCheck() {
    }

    /**
     *  提供一个静态的公有方法，加入双重检查代码，解决线程安全问题，同时解决懒加载问题
     */
    public static synchronized SingletonDoubleCheck getSingleton() {
        if (null == singleton) {
            synchronized (SingletonDoubleCheck.class) {
                if (null == singleton) {
                    singleton = new SingletonDoubleCheck();
                }
            }
        }
        return singleton;
    }
}

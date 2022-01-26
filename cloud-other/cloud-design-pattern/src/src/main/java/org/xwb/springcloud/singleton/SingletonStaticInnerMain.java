package org.xwb.springcloud.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description 静态内部类完成单例
 */
@SuppressWarnings("All")
@Slf4j
public class SingletonStaticInnerMain {
    public static void main(String[] args) {
        SingletonStaticInner singleton = SingletonStaticInner.getInstance();
        SingletonStaticInner singleton2 = SingletonStaticInner.getInstance();

        log.info("静态内部类完成单例模式");
        log.info("比对结果：{}", singleton == singleton2);
        log.info("singleton :hashCode：{}", singleton.hashCode());
        log.info("singleton2:hashCode：{}", singleton.hashCode());
    }
}

class SingletonStaticInner {

    private SingletonStaticInner() {
    }
    /**
     * 写一个静态内部类，该类中有一个静态属性SingletonStaticInner
     */
    private static class SingletonInstance {
        private static final SingletonStaticInner INSTANCE = new SingletonStaticInner();
    }
    /**
     * 提供一个静态的公有方法，直接返回SingletonInstance.INSTANCE;
     */
    public static SingletonStaticInner getInstance() {
        return SingletonInstance.INSTANCE;
    }
}

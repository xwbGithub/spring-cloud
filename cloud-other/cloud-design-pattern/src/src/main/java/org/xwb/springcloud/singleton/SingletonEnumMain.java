package org.xwb.springcloud.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description 静态内部类完成单例
 */
@SuppressWarnings("All")
@Slf4j
public class SingletonEnumMain {
    public static void main(String[] args) {
        SingletonEnum singleton = SingletonEnum.INSTANCE;
        SingletonEnum singleton2 = SingletonEnum.INSTANCE;

        log.info("比对结果：{}", singleton == singleton2);
        log.info("singleton :hashCode：{}", singleton.hashCode());
        log.info("singleton2:hashCode：{}", singleton.hashCode());
    }
}

enum SingletonEnum {
    //属性
    INSTANCE;
    public void sayOK(){
        System.out.println("ok");

    }
}

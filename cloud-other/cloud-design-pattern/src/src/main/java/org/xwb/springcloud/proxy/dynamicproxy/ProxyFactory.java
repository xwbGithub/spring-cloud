package org.xwb.springcloud.proxy.dynamicproxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @description
 */
@Slf4j
public class ProxyFactory {
    //维护一个目标对象，Object
    private Object target;

    //构造器，对target进行初始化
    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        /**
         *     public static Object newProxyInstance(ClassLoader loader,
         *                                           Class<?>[] interfaces,
         *                                           InvocationHandler h)
         */
        //1、ClassLoader loader:指定当前目标对象中适用的类加载器
        //2、Class<?>[]  interfaces:目标对象实现的接口类型，使用泛型方法确认类型。
        //3、InvocationHandler 事件处理，直行目标对象的方法，会触发事件处理器的方法。

        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            log.info("jdk代理开始...");
            return method.invoke(target, args);
        };
        return Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    }
}

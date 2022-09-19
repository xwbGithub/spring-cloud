package org.xwb.springcloud.proxy.cglibproxy;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description
 */
@Slf4j
public class ProxyFactory implements MethodInterceptor {

    //维护一个目标对象
    private Object target;

    //构造器，传入一个被代理的对象
    public ProxyFactory(Object target) {
        this.target = target;
    }

// 返回一个代理对象： 是target对象的代理对象
    public Object getProxyInstance(){
        //1、创建一个工具类
        Enhancer enhancer=new Enhancer();
        //2、设置父类
        enhancer.setSuperclass(target.getClass());
        //3、设置回调函数（就是他自己）
        enhancer.setCallback(this);
        //4、创建自雷对象，即代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object arg0, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("Cglib代理模式 ~ 开始");
        Object invoke = method.invoke(target, args);
        log.info("Cglib代理模式 ~ 提交");
        return  invoke;
    }
}

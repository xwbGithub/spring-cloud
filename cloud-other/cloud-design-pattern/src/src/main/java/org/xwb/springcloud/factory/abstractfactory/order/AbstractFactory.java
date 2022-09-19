package org.xwb.springcloud.factory.abstractfactory.order;

import org.xwb.springcloud.factory.abstractfactory.pizza.Pizza;

/**
 * @author Administrator
 * @description
 */
//一个抽象工厂模式的抽象层(接口)
public interface AbstractFactory {
    /**
     * 创建工厂类，子类实现
     * @param orderType
     * @return
     */
    public Pizza createPizza(String orderType);
}

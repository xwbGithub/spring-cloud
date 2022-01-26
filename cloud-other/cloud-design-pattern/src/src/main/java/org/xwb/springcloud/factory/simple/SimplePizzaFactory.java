package org.xwb.springcloud.factory.simple;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description 简单工厂类
 */
@Slf4j
public class SimplePizzaFactory {
    /**
     * 根据orderType返回对应的pizza对象
     * @return
     */
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        log.info("\n使用简单工厂模式");
        if ("greek".equals(orderType)) {
            pizza = new GreekPizza();
            pizza.setName("希腊披萨");
        } else if ("chess".equals(orderType)) {
            pizza = new ChessPizza();
            pizza.setName("奶酪披萨");
        } else if ("pepper".equals(orderType)) {
            pizza = new ChessPizza();
            pizza.setName("胡椒披萨");
        }
        return pizza;
    }
}

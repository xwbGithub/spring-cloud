package org.xwb.springcloud.factory.simple;

/**
 * @author Administrator
 * @description 相当于一个客户端，发出订购任务
 */
public class PizzaStore {
    public static void main(String[] args) {
        //传统用法
        //new OrderPizza();
        //简单工厂模式
        new OrderPizzaSimpleFactory(new SimplePizzaFactory());
    }
}

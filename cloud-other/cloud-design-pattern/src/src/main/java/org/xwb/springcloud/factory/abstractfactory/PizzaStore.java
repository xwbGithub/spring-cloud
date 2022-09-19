package org.xwb.springcloud.factory.abstractfactory;

import org.xwb.springcloud.factory.abstractfactory.order.BJFactory;
import org.xwb.springcloud.factory.abstractfactory.order.LDFactory;
import org.xwb.springcloud.factory.abstractfactory.order.OrderPizza;

/**
 * @author Administrator
 * @description
 */
public class PizzaStore {
    public static void main(String[] args) {
//        new OrderPizza(new BJFactory());
        new OrderPizza(new LDFactory());
    }
}

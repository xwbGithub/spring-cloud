package org.xwb.springcloud.factory.method.order;

import org.xwb.springcloud.factory.method.pizza.LDPepperPizza;
import org.xwb.springcloud.factory.method.pizza.LDcheesePizza;
import org.xwb.springcloud.factory.method.pizza.Pizza;

/**
 * @author Administrator
 * @description
 */
public class LDOrderPizza extends OrderPizza {
    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new LDcheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}

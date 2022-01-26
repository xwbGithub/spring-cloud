package org.xwb.springcloud.factory.method.order;

import org.xwb.springcloud.factory.method.pizza.BJPepperPizza;
import org.xwb.springcloud.factory.method.pizza.BJcheesePizza;
import org.xwb.springcloud.factory.method.pizza.Pizza;

/**
 * @description
 */
public class BJOrderPizza extends  OrderPizza{
    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza =null;
        if(orderType.equals("cheese")){
            pizza=new BJcheesePizza();
        }else if(orderType.equals("pepper")){
            pizza=new BJPepperPizza();
        }
        return pizza;
    }
}

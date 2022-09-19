package org.xwb.springcloud.factory.abstractfactory.order;

import lombok.extern.slf4j.Slf4j;
import org.xwb.springcloud.factory.abstractfactory.pizza.BJPepperPizza;
import org.xwb.springcloud.factory.abstractfactory.pizza.BJcheesePizza;
import org.xwb.springcloud.factory.abstractfactory.pizza.Pizza;
import org.xwb.springcloud.utils.CommonField;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class BJFactory implements AbstractFactory {
    @Override
    public Pizza createPizza(String orderType) {
        log.info("使用的是抽象工厂模式");
        Pizza pizza = null;
        if (CommonField.CHEESE.equals(orderType)) {
            pizza = new BJcheesePizza();
        } else if (CommonField.PEPPER.equals(orderType)) {
            pizza = new BJPepperPizza();
        }
        return pizza;
    }
}

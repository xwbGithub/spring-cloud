package org.xwb.springcloud.factory.abstractfactory.order;

import lombok.extern.slf4j.Slf4j;
import org.xwb.springcloud.factory.abstractfactory.pizza.Pizza;
import org.xwb.springcloud.utils.CommonUtils;

/**
 * @description
 */
@Slf4j
public class OrderPizza {
    AbstractFactory factory;

    public OrderPizza(AbstractFactory factory){
        setAbstractFactory(factory);
    }

    private void setAbstractFactory(AbstractFactory factory) {
        Pizza pizza = null;
        //用户输入
        String orderType = "";
        this.factory = factory;
        do {
            orderType = CommonUtils.getType();
            //factory 可能是北京的工厂子类，也有可能是伦敦的工厂子类
            pizza = factory.createPizza(orderType);
            //订购成功
            if (null != pizza) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            }
        } while (true);
    }
}

package org.xwb.springcloud.factory.simple;

import lombok.extern.slf4j.Slf4j;
import org.xwb.springcloud.utils.CommonUtils;

/**
 * @author Administrator
 * @description 订单披萨
 */
@Slf4j
public class OrderPizzaSimpleFactory {
    public OrderPizzaSimpleFactory(SimplePizzaFactory simpleFactory) {
        setSimpleFactory(simpleFactory);
    }


    /**
     * 定义一个简单工厂对象
     */
    SimplePizzaFactory simpleFactory;
    Pizza pizza = null;

    public void setSimpleFactory(SimplePizzaFactory simpleFactory) {
        //用户输入的
        String orderType = "";
        this.simpleFactory = simpleFactory;
        do {
            orderType = CommonUtils.getType();
            pizza = simpleFactory.createPizza(orderType);
            //订购成功
            if (null != pizza) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                //订购失败
                log.info("订购披萨失败！");
            }
        } while (true);
    }
}

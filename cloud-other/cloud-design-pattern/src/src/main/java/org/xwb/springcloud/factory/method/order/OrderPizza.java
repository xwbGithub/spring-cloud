package org.xwb.springcloud.factory.method.order;

import org.xwb.springcloud.factory.method.pizza.Pizza;
import org.xwb.springcloud.utils.CommonUtils;

/**
 * @author Administrator
 */
public abstract class OrderPizza {

    //定义一个抽象方法，createPizza，让各个工厂子类自己实现
    abstract Pizza createPizza(String orderType);

    public OrderPizza(){
        Pizza pizza=null;
        String orderType;
        do {
            orderType= CommonUtils.getType();
            //抽象方法，有工厂子类完成
            pizza=createPizza(orderType);
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        }while (true);
    }
}

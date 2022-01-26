package org.xwb.springcloud.factory.simple;

import lombok.extern.slf4j.Slf4j;
import org.xwb.springcloud.utils.CommonUtils;


/**
 * 此方法为传统的调用方法(做对比操作)
 *
 * @author Administrator
 * @description 订单披萨
 */
@Slf4j
@Deprecated
public class OrderPizza {
    //构造器
    public OrderPizza() {
        Pizza pizza = null;
        String orderType;// 订购披萨的类型
        do {
            orderType = CommonUtils.getType();
            if ("greek".equals(orderType)) {
                pizza = new GreekPizza();
            } else if ("chess".equals(orderType)) {
                pizza = new ChessPizza();
            } else if ("pepper".equals(orderType)) {
                pizza = new ChessPizza();
            } else {
                break;
            }
            pizza.setName(orderType);
            //输出pizza
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } while (true);
    }
}

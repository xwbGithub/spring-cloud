package org.xwb.springcloud.decorator.decorator;

import org.xwb.springcloud.decorator.coffee.Drink;

/** 牛奶调味品
 * @author Administrator
 * @description
 */
public class MilkDecorator extends Decorator {
    public MilkDecorator(Drink obj) {
        super(obj);
        setDescription(" 牛奶 ");
        setPrice(2.0f);
    }
}

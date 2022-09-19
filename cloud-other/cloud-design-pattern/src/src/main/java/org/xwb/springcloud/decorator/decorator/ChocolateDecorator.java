package org.xwb.springcloud.decorator.decorator;

import org.xwb.springcloud.decorator.coffee.Drink;

/** 巧克力
 * @author Administrator
 * @description 具体的Decorator, 这里就是调味品
 */
public class ChocolateDecorator extends Decorator {
    public ChocolateDecorator(Drink obj) {
        super(obj);
        setDescription(" 巧克力 ");
        //调味品的价格
        setPrice(3.0f);
    }
}

package org.xwb.springcloud.decorator.decorator;

import org.xwb.springcloud.decorator.coffee.Drink;

/**
 * 豆浆
 *
 * @author Administrator
 * @description
 */
public class SoyDecorator extends Decorator {
    public SoyDecorator(Drink obj) {
        super(obj);
        setDescription(" 豆浆 ");
        setPrice(1.5f);
    }
}

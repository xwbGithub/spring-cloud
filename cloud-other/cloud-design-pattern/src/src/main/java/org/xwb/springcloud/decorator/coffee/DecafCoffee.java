package org.xwb.springcloud.decorator.coffee;

import org.xwb.springcloud.decorator.coffee.Coffee;

/**
 * @description 无因咖啡
 */
public class DecafCoffee extends Coffee {
    public DecafCoffee() {
        setDescription(" 无因咖啡 ");
        setPrice(1.0f);
    }
}

package org.xwb.springcloud.decorator.coffee;

/**
 * @author Administrator
 * @description 咖啡
 */
public class Coffee extends Drink {
    @Override
    public float cost() {
        return super.getPrice();
    }
}

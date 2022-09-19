package org.xwb.springcloud.decorator.decorator;

import org.xwb.springcloud.decorator.coffee.Drink;

/**
 * 装饰者
 *
 * @author Administrator
 * @description
 */
public class Decorator extends Drink {

    private Drink obj;

    //组合
    public Decorator(Drink obj) {
        this.obj = obj;
    }

    @Override
    public float cost() {
        //自己的价格 + drink的价格
        return super.getPrice() + obj.cost();
    }

    @Override
    public String getDescription() {
        //输出了被装饰者的信息
        return super.description + "" + super.getPrice() + " && " + obj.getDescription();
    }
}

package org.xwb.springcloud.decorator.coffee;

import lombok.Data;

/** 饮料
 * @author Administrator
 * @description
 */
@Data
public abstract class Drink {
    //描述
    public  String description;
    //价格
    private float price=0.0f;

    //计算费用的抽象方法
    //子类去实现
    public abstract float cost();
}

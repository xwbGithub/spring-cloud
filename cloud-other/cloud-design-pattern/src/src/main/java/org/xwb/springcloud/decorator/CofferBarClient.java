package org.xwb.springcloud.decorator;

import lombok.extern.slf4j.Slf4j;
import org.xwb.springcloud.decorator.coffee.DecafCoffee;
import org.xwb.springcloud.decorator.coffee.Drink;
import org.xwb.springcloud.decorator.coffee.LongBlackCoffer;
import org.xwb.springcloud.decorator.decorator.ChocolateDecorator;
import org.xwb.springcloud.decorator.decorator.MilkDecorator;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class CofferBarClient {
    public static void main(String[] args) {
        //装饰者模式下的订单：2份巧克力+一份牛奶的LongBlack

        //1、点一份LongBlack
        Drink order=new LongBlackCoffer();
        log.info("费用1：{}",order.cost());
        log.info("描述1：{}",order.getDescription());

        //order加一分牛奶
        order=new MilkDecorator(order);
        log.info("费用1：{}",order.cost());
        log.info("描述1：{}",order.getDescription());
        //order 加一分巧克力
        order=new ChocolateDecorator(order);
        log.info("费用1：{}",order.cost());
        log.info("描述1：{}",order.getDescription());
        //order 加一分巧克力
        order=new ChocolateDecorator(order);
        log.info("费用1：{}",order.cost());
        log.info("描述1：{}",order.getDescription());

        log.info("=======================================");

        Drink order2=new DecafCoffee();
        log.info("费用2：{}",order2.cost());
        log.info("描述2：{}",order2.getDescription());

        order2=new ChocolateDecorator(order2);
        log.info("费用2：加入一份巧克力{}",order2.cost());
        log.info("描述2：加入一份巧克力{}",order2.getDescription());
    }
}

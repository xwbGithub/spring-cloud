package org.xwb.springcloud.factory.abstractfactory.pizza;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class BJcheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("北京奶酪pizza");
        log.info("北京奶酪pizza 准备原材料");
    }
}

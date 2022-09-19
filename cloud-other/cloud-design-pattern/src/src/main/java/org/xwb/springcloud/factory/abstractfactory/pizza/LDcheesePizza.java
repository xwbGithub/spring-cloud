package org.xwb.springcloud.factory.abstractfactory.pizza;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class LDcheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("伦敦奶酪pizza");
        log.info("伦敦奶酪pizza 准备原材料");
    }
}

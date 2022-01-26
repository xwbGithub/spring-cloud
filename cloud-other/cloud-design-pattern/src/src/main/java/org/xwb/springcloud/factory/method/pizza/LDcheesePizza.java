package org.xwb.springcloud.factory.method.pizza;

import lombok.extern.slf4j.Slf4j;

/**
 * @description
 */
@Slf4j
public class LDcheesePizza extends Pizza{
    @Override
    public void prepare() {
        setName("伦敦奶酪pizza");
        log.info("伦敦奶酪pizza 准备原材料");
    }
}

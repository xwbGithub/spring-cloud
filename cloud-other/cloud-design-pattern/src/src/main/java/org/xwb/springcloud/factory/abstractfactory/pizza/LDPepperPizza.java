package org.xwb.springcloud.factory.abstractfactory.pizza;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class LDPepperPizza extends Pizza {
    @Override
    public void prepare() {
        setName("伦敦胡椒pizza");
        log.info("伦敦胡椒pizza 准备原材料");
    }
}

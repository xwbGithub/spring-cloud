package org.xwb.springcloud.factory.method.pizza;

import lombok.extern.slf4j.Slf4j;

/**
 * @description
 */
@Slf4j
public class BJPepperPizza extends Pizza{
    @Override
    public void prepare() {
        setName("北京胡椒pizza");
        log.info("北京胡椒pizza 准备原材料");
    }
}

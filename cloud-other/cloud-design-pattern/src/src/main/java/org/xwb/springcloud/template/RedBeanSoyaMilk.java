package org.xwb.springcloud.template;

import lombok.extern.slf4j.Slf4j;

/**
 * @description
 */
@Slf4j
public class RedBeanSoyaMilk extends  SoyaMilk{
    @Override
    void addCondiments() {
        log.info("第二步：加入红豆");
    }
}

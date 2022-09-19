package org.xwb.springcloud.template;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class PeanutSoyaMilk extends  SoyaMilk{
    @Override
    void addCondiments() {
        log.info("第二步：加入花生");
    }
}

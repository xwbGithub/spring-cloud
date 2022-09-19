package org.xwb.springcloud.template;

import lombok.extern.slf4j.Slf4j;

/**
 * @description
 */
@Slf4j
public class Client {
    public static void main(String[] args) {
        log.info("------制作红豆豆浆------");
        SoyaMilk redBeanSoyaMilk = new RedBeanSoyaMilk();
        redBeanSoyaMilk.make();
        log.info("=====================================");
        log.info("------制作花生豆浆------");
        SoyaMilk peanutSoyaMilk = new PeanutSoyaMilk();
        peanutSoyaMilk.make();
        log.info("=====================================");
        log.info("------制作纯豆浆------");
        SoyaMilk pureSoyaMilk = new PureSoyaMilk();
        pureSoyaMilk.make();

    }
}

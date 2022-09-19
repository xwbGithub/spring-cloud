package org.xwb.springcloud.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * 投影仪
 * @description
 */
@Slf4j
public class Projector {
    //饿汉式
    private static Projector projector = new Projector();

    public static Projector getInstance() {
        return projector;
    }
    public void on() {
        log.info(" projector on ");
    }
    public void off() {
        log.info(" projector off ");
    }
    public void focus() {
        log.info(" projector is focus");
    }
}

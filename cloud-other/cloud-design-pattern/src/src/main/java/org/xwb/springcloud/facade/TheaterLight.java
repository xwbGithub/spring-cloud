package org.xwb.springcloud.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * 影院灯光
 *
 * @author Administrator
 * @description
 */
@Slf4j
public class TheaterLight {
    //使用饿汉式单例模式
    private static TheaterLight instance = new TheaterLight();

    public static TheaterLight getInstance() {
        return instance;
    }

    public void on() {
        log.info(" TheaterLight up ");
    }

    public void dim() {
        log.info(" TheaterLight dim ");
    }

    public void bright() {
        log.info(" TheaterLight bright ");
    }
}

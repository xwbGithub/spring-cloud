package org.xwb.springcloud.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class DVDPlayer {
    //使用饿汉式单例模式
    private static DVDPlayer instance = new DVDPlayer();

    public static DVDPlayer getInstance() {
        return instance;
    }

    public void on() {
        log.info(" dvd on ");
    }
    public void off() {
        log.info(" dvd off ");
    }
    public void play() {
        log.info(" dvd is play");
    }

    public void pause() {
        log.info(" dvd is pause");
    }
}

package org.xwb.springcloud.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * 立体声
 *
 * @author Administrator
 * @description
 */
@Slf4j
public class Stereo {
    //饿汉式
    private static Stereo projector = new Stereo();

    public static Stereo getInstance() {
        return projector;
    }

    public void on() {
        log.info(" Stereo on ");
    }

    public void off() {
        log.info(" Stereo off ");
    }

    public void up() {
        log.info(" Stereo is up");
    }
}

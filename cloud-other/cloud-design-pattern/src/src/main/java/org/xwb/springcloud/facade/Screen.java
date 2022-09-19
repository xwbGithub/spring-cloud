package org.xwb.springcloud.facade;

import lombok.extern.slf4j.Slf4j;

/** 屏幕
 * @description
 */
@Slf4j
public class Screen {
    //使用饿汉式单例模式
    private static Screen instance = new Screen();

    public static Screen getInstance() {
        return instance;
    }

    public void up() {
        log.info(" Screen up ");
    }
    public void on() {
        log.info(" Screen on ");
    }
    public void down() {
        log.info(" Screen down ");
    }
}

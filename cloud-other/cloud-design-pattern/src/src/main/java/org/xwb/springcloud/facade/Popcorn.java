package org.xwb.springcloud.facade;

import lombok.extern.slf4j.Slf4j;

/** 爆米花机
 * @description
 */
@Slf4j
public class Popcorn {
    //饿汉式
    private static Popcorn popcorn = new Popcorn();

    public static Popcorn getInstance() {
        return popcorn;
    }
    public void on() {
        log.info(" popcorn on ");
    }
    public void off() {
        log.info(" popcorn off ");
    }
    public void pop() {
        log.info(" popcorn is poping");
    }
}

package org.xwb.springcloud.adapter.classadapter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class Client {
    public static void main(String[] args) {
        log.info("\n类适配器模式==============");
        Phone phone=new Phone();
        phone.charging(new VoltageAdapter());
    }
}

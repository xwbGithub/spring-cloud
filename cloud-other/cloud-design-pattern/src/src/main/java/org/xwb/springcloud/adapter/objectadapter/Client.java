package org.xwb.springcloud.adapter.objectadapter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class Client {
    public static void main(String[] args) {
        log.info("\n对象适配器模式==============");
        Phone phone=new Phone();
        phone.charging(new VoltageAdapter(new Voltage220V()));
    }
}

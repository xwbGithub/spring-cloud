package org.xwb.springcloud.adapter.objectadapter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class Phone {
    //充电方法
    public void charging(Voltage5V voltage5V) {
        if (voltage5V.output5V() == 5) {
            log.info("可以充电");
        } else if (voltage5V.output5V() > 5) {
            log.info("电压大于5V,无法充电");
        }
    }
}

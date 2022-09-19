package org.xwb.springcloud.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class XiaoMi implements Brand {
    @Override
    public void open() {
        log.info("小米手机开机了");
    }

    @Override
    public void close() {
        log.info("小米手机关机了");

    }

    @Override
    public void call() {
        log.info("小米手机打电话了");
    }
}

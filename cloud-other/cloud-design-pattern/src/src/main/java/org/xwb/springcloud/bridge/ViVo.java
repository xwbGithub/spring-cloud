package org.xwb.springcloud.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class ViVo implements  Brand{
    @Override
    public void open() {
        log.info("ViVo手机开机了");
    }

    @Override
    public void close() {
        log.info("ViVo手机关机了");

    }

    @Override
    public void call() {
        log.info("ViVo手机打电话了");
    }
}

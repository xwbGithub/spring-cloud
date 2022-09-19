package org.xwb.springcloud.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class UpRightPhone extends Phone {

    public UpRightPhone(Brand brand) {
        super(brand);
    }

    @Override
    protected void open() {
        super.open();
        log.info("直立样式手机开机");
    }

    @Override
    protected void close() {
        super.close();
        log.info("直立样式手机关机");
    }

    @Override
    protected void call() {
        super.call();
        log.info("直立样式手机打电话");
    }
}

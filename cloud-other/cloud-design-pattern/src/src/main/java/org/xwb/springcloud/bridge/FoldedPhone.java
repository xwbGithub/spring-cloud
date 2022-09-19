package org.xwb.springcloud.bridge;

import lombok.extern.slf4j.Slf4j;

/** 折叠手机类继承phone(抽象)类
 * @author Administrator
 * @description
 */
@Slf4j
public class FoldedPhone extends Phone {

    public FoldedPhone(Brand brand) {
        super(brand);
    }

    @Override
    protected void open() {
        super.open();
        log.info("折叠样式手机");
    }

    @Override
    protected void close() {
        super.close();
        log.info("折叠样式手机");
    }

    @Override
    protected void call() {
        super.call();
        log.info("折叠样式手机");
    }
}

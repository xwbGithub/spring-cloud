package org.xwb.springcloud.factory.simple;

import lombok.extern.slf4j.Slf4j;

/**
 * @description 希腊披萨
 */
@Slf4j
public class GreekPizza extends  Pizza{
    @Override
    public void prepare() {
        log.info("  给希腊披萨准备原材料");
    }
}

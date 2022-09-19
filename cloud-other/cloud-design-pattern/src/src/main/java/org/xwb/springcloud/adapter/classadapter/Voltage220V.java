package org.xwb.springcloud.adapter.classadapter;

import lombok.extern.slf4j.Slf4j;

/** 被适配的类
 * @author Administrator
 * @description
 */
@Slf4j
public class Voltage220V {
    public int output220(){
        int src=220;
        log.info("输出{}伏电压",src);
        return src;
    }
}

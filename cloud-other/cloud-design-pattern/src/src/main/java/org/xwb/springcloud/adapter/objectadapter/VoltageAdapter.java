package org.xwb.springcloud.adapter.objectadapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 适配器类
 *
 * @author Administrator
 * @description
 */
@Slf4j
public class VoltageAdapter implements Voltage5V {
    private Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        int dst = 0;
        if (null != voltage220V) {
            //获取220v电压
            int srcV = voltage220V.output220();
            log.info("适用对象适配器,进行适配...");
            //转为5v(降压处理)
            dst = srcV / 44;
            log.info("适配完成,输出电压为：{}伏", dst);
        }
        return dst;
    }
}

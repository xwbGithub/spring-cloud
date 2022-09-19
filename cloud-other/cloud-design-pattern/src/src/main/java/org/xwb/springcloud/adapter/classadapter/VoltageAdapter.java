package org.xwb.springcloud.adapter.classadapter;

/** 适配器类
 * @author Administrator
 * @description
 */
public class VoltageAdapter extends  Voltage220V implements Voltage5V {
    @Override
    public int output5V() {
        //获取220v电压
        int srcV = output220();
        //转为5v(降压处理)
        return srcV/44;
    }
}

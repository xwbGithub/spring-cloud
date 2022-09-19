package org.xwb.springcloud.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * @description
 */
@Slf4j
public class Client {
    public static void main(String[] args) {
        //折叠手机（样式+品牌）
        Phone phone =new FoldedPhone(new XiaoMi());
        phone.open();
        phone.call();
        phone.close();
        log.info("=====================");
        Phone phone1=new FoldedPhone(new ViVo());
        phone1.open();
        phone1.call();
        phone1.close();
        log.info("=====================");
        //折叠手机（样式+品牌）
        Phone phone2 =new UpRightPhone(new XiaoMi());
        phone2.open();
        phone2.call();
        phone2.close();
        log.info("=====================");
        Phone phone3=new UpRightPhone(new ViVo());
        phone3.open();
        phone3.call();
        phone3.close();
    }
}

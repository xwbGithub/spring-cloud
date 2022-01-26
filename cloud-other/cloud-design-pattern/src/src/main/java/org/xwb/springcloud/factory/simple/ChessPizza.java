package org.xwb.springcloud.factory.simple;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description chess披萨实现类
 */
@Slf4j
public class ChessPizza extends  Pizza{
    @Override
    public void prepare() {
      log.info(" 给制作奶酪披萨 准备原材料");
    }
}

package org.xwb.springcloud.flyweight;

import lombok.extern.slf4j.Slf4j;

/**
 * 具体的网站
 *
 * @author Administrator
 * @description
 */
@Slf4j
public class ConcreteWebSite extends WebSite {


    //共享的部分，内部状态
    //网站发布的形式(类型)
    private String type = "";

    public ConcreteWebSite(String type) {
        this.type = type;
    }


    @Override
    public void use(User user) {
        log.info("正在使用的网站:{},使用者是：{}",type,user.getName());
    }
}

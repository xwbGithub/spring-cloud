package org.xwb.springcloud.proxy.cglibproxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class TeacherDao {
    public void teacher(){
        log.info(" 老师授课中， 我是cglib代理，不需要实现接口 ");
    }
}

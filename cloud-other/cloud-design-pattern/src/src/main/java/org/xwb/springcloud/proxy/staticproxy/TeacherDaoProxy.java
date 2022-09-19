package org.xwb.springcloud.proxy.staticproxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @description
 */
@Slf4j
public class TeacherDaoProxy implements  ITeacherDao{

    //目标对象 通过接口来聚合
    private ITeacherDao target;

    public TeacherDaoProxy(ITeacherDao target) {
        this.target = target;
    }

    @Override
    public void teacher() {
        log.info("开始代理 完成某些操作......");
        target.teacher();
        log.info("提交......");
    }
}

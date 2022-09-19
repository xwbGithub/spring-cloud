package org.xwb.springcloud.proxy.staticproxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class TeacherDao implements ITeacherDao {
    @Override
    public void teacher() {
        log.info("老师授课中...");
    }
}

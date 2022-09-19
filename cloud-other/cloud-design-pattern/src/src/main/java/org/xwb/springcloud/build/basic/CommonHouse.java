package org.xwb.springcloud.build.basic;

import lombok.extern.slf4j.Slf4j;

/**
 * 普通房子
 *
 * @author Administrator
 * @description
 */
@Slf4j
public class CommonHouse extends AbstractHouse {
    @Override
    public void buildBasic() {
        log.info("给普通房子打地基");
    }

    @Override
    public void buildWalls() {
        log.info("给普通房子砌墙");

    }

    @Override
    public void roofed() {
        log.info("给普通房子封顶");
    }
}

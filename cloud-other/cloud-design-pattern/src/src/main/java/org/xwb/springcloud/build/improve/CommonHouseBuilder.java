package org.xwb.springcloud.build.improve;

import lombok.extern.slf4j.Slf4j;

/** 普通房子
 * @author Administrator
 * @description
 */
@Slf4j
public class CommonHouseBuilder extends HouseBuilder{
    @Override
    public void buildBaise() {
        log.info("给普通房子打地基5m");
    }

    @Override
    public void buildWalls() {
        log.info("给普通房子砌墙10m");
    }

    @Override
    public void buildRoofed() {
        log.info("给普通房子封顶");
    }
}

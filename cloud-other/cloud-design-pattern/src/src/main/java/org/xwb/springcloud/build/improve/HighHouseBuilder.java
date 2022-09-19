package org.xwb.springcloud.build.improve;

import lombok.extern.slf4j.Slf4j;

/**
 * 高层房子
 *
 * @author Administrator
 * @description
 */
@Slf4j
public class HighHouseBuilder extends HouseBuilder {
    @Override
    public void buildBaise() {
        log.info("给高层房子打地基50m");
    }

    @Override
    public void buildWalls() {
        log.info("给高层房子砌墙20cm");
    }

    @Override
    public void buildRoofed() {
        log.info("给高层房子透明封顶");
    }
}

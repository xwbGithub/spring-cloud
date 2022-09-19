package org.xwb.springcloud.build.basic;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description 高层建筑房子
 */
@Slf4j
public class HighBuilding extends AbstractHouse {
    @Override
    public void buildBasic() {
        log.info("给高层房子打地基");
    }

    @Override
    public void buildWalls() {
        log.info("给高层房子砌墙");
    }

    @Override
    public void roofed() {
        log.info("给高层房子封顶");
    }
}

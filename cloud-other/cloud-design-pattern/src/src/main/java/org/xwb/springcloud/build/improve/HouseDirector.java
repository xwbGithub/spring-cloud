package org.xwb.springcloud.build.improve;

/**
 * @author Administrator
 * @description
 */
//指挥者，这里去指定流程，返回铲平
public class HouseDirector {
    HouseBuilder houseBuilder = null;

    //构造器方式传入houseBuilder
    public HouseDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    //通过setter 方式传入houseBuilder
    public void setHouseBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    //如何处理建造房子的流程，交给指挥者
    public House constructHouse() {
        houseBuilder.buildBaise();
        houseBuilder.buildWalls();
        houseBuilder.buildRoofed();
        return houseBuilder.buildHouse();
    }
}

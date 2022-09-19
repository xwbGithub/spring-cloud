package org.xwb.springcloud.build.improve;

/**
 * 抽象建造者
 *
 * @author Administrator
 * @description 抽象建造者
 */
public abstract class HouseBuilder {
    protected House house = new House();

    //将建造的流程写好，抽象的方法
    public abstract void buildBaise();

    public abstract void buildWalls();

    public abstract void buildRoofed();

    //建房 (将房子返回)
    public House buildHouse() {
        return house;
    }
}

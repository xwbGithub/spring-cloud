package org.xwb.springcloud.build.improve;

import lombok.extern.slf4j.Slf4j;

/**
 * @description
 */
@Slf4j
public class Client {
    public static void main(String[] args) {
        //建普通房子
        CommonHouseBuilder commonHouse = new CommonHouseBuilder();
        //准备创建房子的指挥者
        HouseDirector houseDirector = new HouseDirector(commonHouse);
        House house = houseDirector.constructHouse();
        log.info("普通房子：{}", house.toString());
        log.info("==========================================");
        //建高层房子
        HighHouseBuilder highHouse = new HighHouseBuilder();
        HouseDirector houseDirector1 = new HouseDirector(highHouse);
        House house1 = houseDirector1.constructHouse();
        log.info("高层房子：{}", house1.toString());
    }
}

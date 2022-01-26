package org.xwb.springcloud.factory.method.pizza;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description 工厂方法模式
 */
@Slf4j
public abstract class Pizza {
    //名称
    protected String name;

    /**
     * 准备原材料，不同的披萨不一样，因此。做成抽象类
     */

    public abstract void prepare();

    public void bake() {
        log.info(name + " baking;");
    }

    public void cut() {
        log.info(name + " cutting;");
    }

    public void box() {
        log.info(name + " boxing;");
    }

    public void setName(String name) {
        this.name = name;
    }
}


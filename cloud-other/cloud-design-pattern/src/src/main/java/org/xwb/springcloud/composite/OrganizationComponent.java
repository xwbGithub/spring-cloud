package org.xwb.springcloud.composite;

import lombok.Data;

/**
 * @author Administrator
 * @description
 */
@Data
public abstract  class OrganizationComponent {
    //名字
    private String name;
    //说明
    private String description;

    protected void add(OrganizationComponent organizationComponent) {
        //默认实现
        throw new UnsupportedOperationException();
    }
    protected void remove(OrganizationComponent organizationComponent) {
        //默认实现
        throw new UnsupportedOperationException();
    }
    //构造器
    public OrganizationComponent(String name, String description) {
        this.name = name;
        this.description = description;
    }
    //方法打印 做成抽象的
    protected abstract void print();
}

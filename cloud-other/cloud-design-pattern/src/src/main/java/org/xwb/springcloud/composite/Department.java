package org.xwb.springcloud.composite;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class Department extends OrganizationComponent {

    public Department(String name, String description) {
        super(name, description);
    }

    //重写【add,remove都不用再写了，因为他是叶子结点】
    @Override
    protected void print() {
        log.info("专业：{}", getName());
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
}

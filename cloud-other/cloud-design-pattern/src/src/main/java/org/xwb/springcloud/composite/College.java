package org.xwb.springcloud.composite;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 */
@Slf4j
public class College extends OrganizationComponent {

    //List中存放的是department
    List<OrganizationComponent> organizationComponents = new ArrayList<OrganizationComponent>();

    public College(String name, String description) {
        super(name, description);
    }

    //重写
    @Override
    protected void add(OrganizationComponent organizationComponent) {
        //将来实际业务中，College的add方法和University的add方法不一定完全相同
        organizationComponents.add(organizationComponent);
    }

    //重写
    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponents.remove(organizationComponent);
    }

    //重写
    @Override
    protected void print() {
        log.info("============学院：{}=============", getName());
        for (OrganizationComponent organizationComponent : organizationComponents) {
            organizationComponent.print();
        }
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

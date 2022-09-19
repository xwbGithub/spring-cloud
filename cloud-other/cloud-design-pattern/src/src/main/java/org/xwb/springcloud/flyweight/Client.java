package org.xwb.springcloud.flyweight;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class Client {
    public static void main(String[] args) {
        //创建一个工厂类
        WebSiteFactory factory=new WebSiteFactory();
        //创建一个以新闻形式发布的网站
        WebSite webSite=factory.getWebSiteCategory("新闻");
        webSite.use(new User("张三"));
        WebSite webSite1=factory.getWebSiteCategory("博客");
        webSite1.use(new User("张三1"));
        WebSite webSite2=factory.getWebSiteCategory("博客");
        webSite2.use(new User("张三2"));
        WebSite webSite3=factory.getWebSiteCategory("博客");
        webSite3.use(new User("张三3"));
        WebSite webSite4=factory.getWebSiteCategory("博客");
        webSite4.use(new User("张三4"));
        int webSiteCount = factory.getWebSiteCount();
        log.info("当前池中有{}个网站分类",webSiteCount);
    }
}

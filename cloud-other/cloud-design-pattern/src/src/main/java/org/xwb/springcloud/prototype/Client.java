package org.xwb.springcloud.prototype;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description
 */
@Slf4j
public class Client {
    public static void main(String[] args) {
        log.info("用原型模式完成对象的创建");
        Sheep sheep=new Sheep("tom",1,"白色");
        Sheep sheep1=(Sheep)sheep.clone();
        Sheep sheep2=(Sheep)sheep.clone();
        Sheep sheep3=(Sheep)sheep.clone();
        log.info("克隆羊1：{}",sheep1);
        log.info("克隆羊2：{}",sheep2);
        log.info("克隆羊3：{}",sheep3);
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ctx.getBean("sd");
    }
}

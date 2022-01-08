package com.atguigu.es;

import com.cloud.elasticsearch.entity.User;
import com.cloud.elasticsearch.service.ElasticsearchUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserEsTest {
    @Resource
    private ElasticsearchUserService service;

    @Test
    public void insert() {
        User user = new User();
        user.setId("1");
        user.setAge(23);
        user.setName("张三");
        user.setSex("男");
        Object insert = service.insert(user);
        log.info("信息：{}", insert);
    }
    @Test
    public void delete() {
        User user = new User();
        user.setId("1");
        user.setAge(23);
        user.setName("张三");
        user.setSex("男");
        Object insert = service.delete(user);
        log.info("信息：{}", insert);
    }

}

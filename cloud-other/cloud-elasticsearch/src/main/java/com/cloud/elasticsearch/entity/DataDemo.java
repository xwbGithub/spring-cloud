package com.cloud.elasticsearch.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @description
 */
public class DataDemo {
    /**
     * @param start 开始大小
     * @param size  多少个
     * @return 集合
     */
    public static List<User> getUser(int start, int size) {
        int length = start + size;
        List<User> list = new ArrayList<>();
        for (int i = start; i < length; i++) {
            User user = new User();
            user.setSex(i % 2 == 0 ? "男" : "女");
            user.setName("张三" + i);
            user.setAge(i);
            user.setId(i + "");
            list.add(user);
        }
        return list;
    }
}

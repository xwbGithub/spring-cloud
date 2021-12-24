package com.xwb.springcloud.test;

import com.xwb.springcloud.annotation.json.JSON;
import com.xwb.springcloud.domain.User;

import java.util.Date;

public class TestJSON {
    public static void main(String[] args) {
        User user = new User(1, "张三", "宁夏银川", new Date());
        String s = JSON.toJSONString(user);
        System.out.println(s);
    }
}

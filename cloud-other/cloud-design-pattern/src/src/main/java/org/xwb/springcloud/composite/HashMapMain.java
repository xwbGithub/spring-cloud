package org.xwb.springcloud.composite;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @description
 */
@Slf4j
public class HashMapMain {
    public static void main(String[] args) {
        Map<Integer, String> hashMap = new HashMap<Integer, String>();
        hashMap.put(0, "东游记");

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "西游记");
        map.put(2, "红楼梦");
        hashMap.putAll(map);
        log.info(String.valueOf(hashMap));
        System.out.println(hashMap);
    }
}

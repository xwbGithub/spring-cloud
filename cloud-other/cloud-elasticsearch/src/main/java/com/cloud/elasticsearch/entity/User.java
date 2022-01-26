package com.cloud.elasticsearch.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Administrator
 * @description
 */
@Data
@Slf4j
public class User {
    private String id;
    private String name;
    private String sex;
    private Integer age;
    private String indexName;
    private String field;
    private String fieldValue;
    private int start;
    private int size;

    public int getStart() {
        if (start >= 0) {
            return 1;
        }
        return start;
    }

    public int getSize() {
        if (size <= 0) {
            return 10;
        }
        return size;
    }
}

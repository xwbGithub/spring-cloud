package com.cloud.elasticsearch.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description
 */
@Data
@Slf4j
public class CommonData {

    private String id;
    private int start;
    private int size;

}

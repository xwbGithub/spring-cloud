package com.cloud.elasticsearch.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description 公共数据类
 */
@Data
@Slf4j
public class CommonData {

    /**
     * id
     */
    private String id;
    /**
     * 开始计数
     */
    private int start;
    /**
     * 数据条数
     */
    private int size;
    /**
     * 数据
     */
    private String data;

}

package com.cloud.elasticsearch.core;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @description 枚举类表名
 */
@Slf4j
public enum EsIndexName {

    /**
     * 用户
     */
    USER("user", "table_user"),
    /**
     * 订单
     */
    ORDER("order", "table_order");

    /**
     * 名称
     */
    private String name;
    /**
     * 表名
     */
    private String tableName;


    EsIndexName(String name, String tableName) {
        this.name = name;
        this.tableName = tableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public static void main(String[] args) {
        EsIndexName[] values = EsIndexName.values();
        for (EsIndexName value : values) {
            log.info("name:{},table:{}",value.name(),value.getTableName());
        }
    }
}

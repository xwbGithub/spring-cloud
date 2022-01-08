package com.cloud.elasticsearch.service;

/**
 * @author Administrator
 * @description
 */
public interface ElasticsearchService {

    Object insert(Object o);

    Object delete(Object o);

    Object delete(String id);

    Object delete(int id);

    Object update(Object o);

    Object query(Object o);
}

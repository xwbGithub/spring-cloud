package com.cloud.elasticsearch.service;

import com.cloud.elasticsearch.entity.User;

/**
 * @author Administrator
 * @description
 */
public interface ElasticsearchUserService {

    Object insert(User o);

    Object batchInsert(User o);

    Object delete(User o);

    Object delete(String id);

    Object delete(int id);

    Object update(User o);

    Object deleteBatch(User o);



    Object query(User o);

    Object queryTerm(User o);

    Object queryTermOrder(User o);
}

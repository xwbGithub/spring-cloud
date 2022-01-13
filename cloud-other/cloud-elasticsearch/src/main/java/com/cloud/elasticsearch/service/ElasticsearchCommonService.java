package com.cloud.elasticsearch.service;

import com.cloud.elasticsearch.entity.CommonData;

/**
 * @description
 */
public interface ElasticsearchCommonService {
    Object insert(CommonData o);

    Object batchInsert(CommonData o);

    Object delete(CommonData o);

    Object delete(String id);

    Object delete(int id);

    Object update(CommonData o);

    Object deleteBatch(CommonData o);

    Object query(CommonData o);

    Object queryTerm(CommonData o);

    Object queryTermOrder(CommonData o);
}

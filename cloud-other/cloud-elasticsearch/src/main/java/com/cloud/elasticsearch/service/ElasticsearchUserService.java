package com.cloud.elasticsearch.service;

import com.cloud.elasticsearch.entity.User;

/**
 * @author Administrator
 * @description
 */
public interface ElasticsearchUserService {

    Object insert(User o);

    Object batchInsert(User o);

    Object batchInsertCity(User o);

    Object delete(User o);

    Object delete(String id);

    Object delete(int id);

    Object update(User o);

    Object deleteBatch(User o);


    /**
     * 全量查询
     *
     * @Description:
     * @Date: 2022/1/13 16:48
     * @return: null
     **/
    Object query(User o);

    /**
     * 分页查询
     *
     * @Description:
     * @Date: 2022/1/13 16:48
     * @return: null
     **/
    Object queryTerm(User o);

    /**
     * 模糊查询
     *
     * @Description:
     * @Date: 2022/1/13 16:48
     * @return: null
     **/
    Object queryFuzzyQuery(User o);

    /**
     * 包活字段查询
     *
     * @Description:
     * @Date: 2022/1/13 16:48
     * @return: null
     **/

    Object queryFieldIncludeExclude(User o);

    /**
     * 字段排序查询
     *
     * @Description:
     * @Date: 2022/1/13 16:49
     * @return: null
     **/
    Object queryTermOrder(User o);

    /**
     * 组合查询
     *
     * @Description:
     * @Date: 2022/1/13 16:49
     * @return: null
     **/
    Object queryTermCompose(User o);

    /**
     * 范围查询
     *
     * @Description:
     * @Date: 2022/1/13 16:49
     * @return: null
     **/
    Object queryTermRange(User o);

    /**
     * 模糊匹配
     *
     * @param o
     * @return
     */
    Object queryTermFuzzy(User o);

    Object queryTermHighLight(User o);

    Object queryParticipleHighLight(User o);

    Object queryAggregationBuilder(User o);

    Object queryTermsField(User o);
}

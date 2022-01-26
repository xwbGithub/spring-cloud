package com.cloud.elasticsearch.core.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import java.io.IOException;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class EsUtils {

    public static String getIndexName(String key) {
        return StrUtil.isEmpty(key) ? EsIndexNames.USER : key;
    }

    /**
     * 关闭es
     *
     * @Description:
     * @Date: 2021/12/28 16:23
     * @return: null
     **/
    public static void closeEs(RestHighLevelClient esClient) {
        try {
            esClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 创建es工厂
     * @Date: 2021/12/31 14:03
     * @return: null
     **/
    public static RestHighLevelClient createEsClient() {
        try {
            log.info("Utils初始化elasticsearch开始.....");
            return new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost", 9200, "http"))
            );
        } catch (Exception e) {
            log.error("ES创建错误", e);
            throw new RuntimeException("创建es异常:", e);
        } finally {
            log.info("Utils初始化elasticsearch完成.....");
        }
    }

    /**
     * 请求体初始化
     *
     * @param indexName: 索引名
     * @param builder:   查询体
     * @Description:
     * @Date: 2022/1/15 15:20
     * @return: null
     **/
    public static SearchRequest initSearchRequest(String indexName, SearchSourceBuilder builder) {
        SearchRequest request = new SearchRequest();
        //对那个索引进行查询
        request.indices(indexName);
        //查询对象
        request.source(builder);
        return request;
    }

    /**
     * @param start:      开始位置
     * @param size:       每页大小
     * @param build:      查询体
     * @param indexNames: 查询的那个索引
     * @Description:
     * @Date: 2022/1/15 10:02
     * @return: 返回组装的request
     **/

    public static SearchRequest packageSearchRequest(int start, int size, QueryBuilder build,
                                                     String indexNames) {
        SearchSourceBuilder query = new SearchSourceBuilder();
        if (0 != start && 0 != size) {
            //开始位置
            query.from((start - 1) * size);
            //每一页多少条
            query.size(size);
        }
        query.query(build);
//        query.sort()

        return initSearchRequest(indexNames, query);
    }

    /**
     * 设置高亮显示的字段
     *
     * @param field: 进行高亮的字段
     * @Description: 对查询的结果设置高亮显示
     * @Date: 2022/1/15 15:10
     * @return: org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder
     **/
    public static HighlightBuilder setHighlighter(String field) {
        HighlightBuilder highlighter = new HighlightBuilder();
        highlighter.preTags("<font color='red'>");
        highlighter.postTags("</font>");
        highlighter.field(field);
        return highlighter;
    }

    /**
     * @param esClient: es客户端
     * @param request:  请求组装的筛查条件
     * @Description:
     * @Date: 2022/1/15 9:36
     * @return: 查询的结果集
     **/
    public static String search(RestHighLevelClient esClient, SearchRequest request) {
        try {
            SearchResponse search = esClient.search(request, RequestOptions.DEFAULT);
            SearchHits hits = search.getHits();
            log.info("TotalHits:{}", hits.getTotalHits().value);

            StringBuffer sb = new StringBuffer();
            hits.forEach(h -> {
                sb.append("\n").append(h.getSourceAsString());
            });
            log.info("查询到的数据{},{}", hits.getHits(), sb);
            return sb.toString();
        } catch (Exception e) {
            log.error("异常{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Object searchHighlight(RestHighLevelClient esClient, SearchRequest request) {
        try {

            SearchResponse search = esClient.search(request, RequestOptions.DEFAULT);
            SearchHits hits = search.getHits();
            log.info("TotalHits:{}", hits.getTotalHits().value);

            StringBuffer sb = new StringBuffer();
            hits.forEach(h -> {
                sb.append("\n").append(h.getSourceAsString());
            });
            log.info("查询到的数据{},{}", hits.getHits(), sb);
            return sb.toString();
        } catch (Exception e) {
            log.error("异常{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

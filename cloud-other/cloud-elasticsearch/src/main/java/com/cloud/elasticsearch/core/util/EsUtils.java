package com.cloud.elasticsearch.core.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class EsUtils {
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
            log.info("初始化elasticsearch开始.....");
            return new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost", 9200, "http"))
            );
        } catch (Exception e) {
            log.error("ES创建错误", e);
            throw new RuntimeException("创建es异常:", e);
        } finally {
            log.info("初始化elasticsearch完成.....");
        }
    }
}

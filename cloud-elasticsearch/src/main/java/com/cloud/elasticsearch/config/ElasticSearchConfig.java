package com.cloud.elasticsearch.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Administrator
 * @description
 */
@Configuration
@Slf4j
//@ConfigurationProperties(prefix = "spring.data.elasticsearch")
public class ElasticSearchConfig {

    @Bean
    @ConditionalOnMissingBean
    public TransportClient transportClient() {
        log.info("初始化elasticsearch开始.....");
        try {
            //获取client
            Settings settings =
                    Settings.builder().put("cluster.name", "my-application").put("node.name", "node-1").build();
            TransportClient transportClient = new PreBuiltTransportClient(settings);
            transportClient.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9200));
            log.info("初始化elasticsearch完成.....");
            return transportClient;
        } catch (UnknownHostException e) {
            log.error("ES创建错误", e);
            throw new RuntimeException("创建es异常:", e);
        }
    }
}

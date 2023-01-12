package org.xwb.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {
    /**
     * 配置了一个Id为route-name的路由规则，方访问地址http://localhost:9527/guonei是会自动转发到地址http://news.baidu.com/guonei
     *
     * @param routeLocatorBuilder 获取到的路由信息
     * @return 返回路由信息
     */
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_xwb", r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
}

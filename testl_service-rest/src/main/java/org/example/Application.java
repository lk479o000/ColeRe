package org.example;

import com.weibi.core.feignproxy.EnableFeignProxies;
import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author huopengbo
 * @version 1.0
 * @date 2020-04-26 10:48
 */
@EnableFeignClients(basePackages = {
        // 通过 Feign 调用的服务, 需要在此处加入扫描范围
})
@EnableDiscoveryClient
@MapperScan(basePackages = { "com.weibi.**.mapper" })
@EnableFeignProxies(basePackages = { "org.example" })
@ConfigurationPropertiesScan(basePackages = {"com.weibi"})
@SpringBootApplication(
        exclude = { DataSourceAutoConfiguration.class, SpringBootConfiguration.class},
        scanBasePackages = "com.weibi"
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

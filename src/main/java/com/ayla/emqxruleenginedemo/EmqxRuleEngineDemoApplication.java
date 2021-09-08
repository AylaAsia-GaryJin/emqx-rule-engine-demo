package com.ayla.emqxruleenginedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients
@EnableJpaRepositories
@EnableDiscoveryClient
@SpringBootApplication
public class EmqxRuleEngineDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmqxRuleEngineDemoApplication.class, args);
    }

}

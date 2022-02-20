package com.velocia.accountapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class AccountApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApiApplication.class, args);
    }

    //Eureka 가 적용된 RestTemplate (Ribbon 은 non-blocking 기반의 WebClient 를 지원하지 않는다.)
    @LoadBalanced @Bean
    public RestTemplate commonRestTemplate() {
        return new RestTemplate();
    }
}

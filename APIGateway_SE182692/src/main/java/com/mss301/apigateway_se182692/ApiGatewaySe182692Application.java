package com.mss301.apigateway_se182692;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ApiGatewaySe182692Application {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewaySe182692Application.class, args);
    }

}

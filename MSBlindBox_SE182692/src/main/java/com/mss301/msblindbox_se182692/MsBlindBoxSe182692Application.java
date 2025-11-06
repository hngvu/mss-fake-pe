package com.mss301.msblindbox_se182692;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsBlindBoxSe182692Application {

    public static void main(String[] args) {
        SpringApplication.run(MsBlindBoxSe182692Application.class, args);
    }

}

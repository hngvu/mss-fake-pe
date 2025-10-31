package com.mss301.msaccount_se18262;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {
SecurityAutoConfiguration.class,
UserDetailsServiceAutoConfiguration.class
})
public class MsAccountSe18262Application {

    public static void main(String[] args) {
        SpringApplication.run(MsAccountSe18262Application.class, args);
    }

}

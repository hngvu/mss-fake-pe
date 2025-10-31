package com.mss301.msaccount_se18262.repo;

import com.mss301.msaccount_se18262.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {
    private final AccountRepo accountRepo;

    @Override
    public void run(String... args) throws Exception {
        if (accountRepo.count() == 0) {
            accountRepo.save(
                    Account.builder()
                            .id(1)
                            .username("admin")
                            .email("ad@mss.com")
                            .password("123")
                            .role(1)
                            .isActive(true)
                            .build()
            );

            accountRepo.save(
                    Account.builder()
                            .id(2)
                            .username("member1")
                            .email("mem1@mss.com")
                            .password("123")
                            .role(4)
                            .isActive(true)
                            .build()
            );

            accountRepo.save(
                    Account.builder()
                            .id(3)
                            .username("member2")
                            .email("mem2@mss.com")
                            .password("123")
                            .role(4)
                            .isActive(false)
                            .build()
            );
        }
    }
}

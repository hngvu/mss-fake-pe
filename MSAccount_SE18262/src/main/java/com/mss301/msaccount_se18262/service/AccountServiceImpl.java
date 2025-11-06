package com.mss301.msaccount_se18262.service;

import com.mss301.msaccount_se18262.dto.LoginRequest;
import com.mss301.msaccount_se18262.repo.AccountRepo;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithms;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;
    private final JwtEncoder jwtEncoder;

    @Override
    public String login(LoginRequest loginRequest) {
            var account = accountRepo.findByEmail(loginRequest.email())
                    .orElseThrow(() -> new BadCredentialsException("Incorrect email or password"));

            if (account.getPassword().equals(loginRequest.password()) && account.isActive() ) {
                return jwtEncoder.encode(
                        JwtEncoderParameters.from(
                                JwsHeader.with(() -> JwsAlgorithms.HS256)
                                        .type("JWT")
                                        .build(),
                                JwtClaimsSet.builder()
                                        .subject(Integer.toString(account.getId()))
                                        .claim("role", String.valueOf(account.getRole()))
                                        .claim("isActive", true)
                                        .issuedAt(Instant.now())
                                        .expiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
                                        .build()
                        )
                ).getTokenValue();

            }

            throw new BadCredentialsException("Incorrect email or password");
    }


}

package com.mss301.apigateway_se18262;

import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.*;

@Component
public class GatewayFilter implements Filter {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    @Value("${jwt.secret}") // Sửa thành ${jwt.secret}
    private String jwtSecret;

    // PUBLIC APIs: METHOD + PATTERN
    private final List<ApiRule> publicApis = Arrays.asList(
            new ApiRule("GET", "/**/v3/api-docs/**", "PUBLIC"),
            new ApiRule("GET", "/swagger-ui/**", "PUBLIC"),
            new ApiRule("POST", "/account/login", "PUBLIC"),
            new ApiRule("GET", "/blindbox/blindboxes/**", "PUBLIC") // GET blindboxes là public
    );

    // PRIVATE APIs: METHOD + PATTERN + ROLE
    private final List<ApiRule> privateRules = Arrays.asList(
            new ApiRule("POST", "/blindbox/blindboxes/**", "1"),
            new ApiRule("PUT", "/blindbox/blindboxes/**", "1"),
            new ApiRule("DELETE", "/blindbox/blindboxes/**", "1")
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();

        // Check PUBLIC API
        if (isPublicApi(method, path)) {
            System.out.println(method + " " + path + " is public API");
            chain.doFilter(request, response);
            return;
        }

        // Check token
        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            httpResponse.setStatus(401);
            httpResponse.getWriter().write("Missing token");
            return;
        }

        System.out.println(method + " " + path + " is private API");

        // Decode JWT
        String token = authHeader.substring(7);
        String userRole = decodeJWT(token);

        if (userRole == null) {
            httpResponse.setStatus(401);
            httpResponse.getWriter().write("Invalid token");
            return;
        }

        // Check PRIVATE API permission
        ApiRule rule = findPrivateRule(method, path);
        if (rule == null || !hasPermission(rule.role, userRole)) {
            httpResponse.setStatus(403);
            httpResponse.getWriter().write("No permission");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isPublicApi(String method, String path) {
        return publicApis.stream()
                .anyMatch(rule -> method.equals(rule.method) && pathMatcher.match(rule.pattern, path));
    }

    private ApiRule findPrivateRule(String method, String path) {
        return privateRules.stream()
                .filter(rule -> method.equals(rule.method) && pathMatcher.match(rule.pattern, path))
                .findFirst()
                .orElse(null);
    }

    private String decodeJWT(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(jwtSecret);

            if (!signedJWT.verify(verifier)) {
                return null;
            }

            return signedJWT.getJWTClaimsSet().getStringClaim("role");
        } catch (Exception e) {
            return null;
        }
    }

    private boolean hasPermission(String requiredRole, String userRole) {
        return "*".equals(requiredRole) || requiredRole.equals(userRole);
    }

    private static class ApiRule {
        String method;
        String pattern;
        String role;

        ApiRule(String method, String pattern, String role) {
            this.method = method;
            this.pattern = pattern;
            this.role = role;
        }
    }
}
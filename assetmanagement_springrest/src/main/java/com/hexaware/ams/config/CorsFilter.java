package com.hexaware.ams.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class CorsFilter extends OncePerRequestFilter {

    private final CorsConfigurationSource configSource;

    public CorsFilter(CorsConfigurationSource configSource) {
        this.configSource = configSource;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        CorsConfiguration config = configSource.getCorsConfiguration(request);
        if (config != null) {
            response.setHeader("Access-Control-Allow-Origin", String.join(", ", config.getAllowedOrigins()));
            response.setHeader("Access-Control-Allow-Methods", String.join(", ", config.getAllowedMethods()));
            response.setHeader("Access-Control-Allow-Headers", String.join(", ", config.getAllowedHeaders()));
            if (config.getAllowCredentials() != null) {
                response.setHeader("Access-Control-Allow-Credentials", config.getAllowCredentials().toString());
            }
        }

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
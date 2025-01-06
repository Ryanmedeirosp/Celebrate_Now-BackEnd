package com.celebrate.backend.client.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.celebrate.backend.client.CustomErrorDecoder;

@Configuration
public class FeignConfig {

    @Bean
    public CustomErrorDecoder customErrorDecoder() {
        return new CustomErrorDecoder();
    }
}

package com.gym.crm.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.gym.crm")
public class Config {

    @Bean(name = "storageMap")
    public Map<String, Map<Long, Object>> storageMap() {
        return new java.util.concurrent.ConcurrentHashMap<>();
    }
}

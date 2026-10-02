package com.gym.crm.config;

import com.gym.crm.domain.Trainee;
import com.gym.crm.domain.Trainer;
import com.gym.crm.domain.Training;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@ComponentScan(basePackages = "com.gym.crm")
@PropertySource("classpath:application.properties")
public class Config {

    @Bean(name = "traineeMap")
    public Map<Long, Trainee> traineeMap() {
        return new ConcurrentHashMap<>();
    }

    @Bean(name = "trainerMap")
    public Map<Long, Trainer> trainerMap() {
        return new ConcurrentHashMap<>();
    }

    @Bean(name = "trainingMap")
    public Map<Long, Training> trainingMap() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
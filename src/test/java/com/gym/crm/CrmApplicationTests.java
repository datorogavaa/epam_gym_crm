package com.gym.crm;

import com.gym.crm.config.Config;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CrmApplicationTests {

    @Test
    void contextLoads() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class)) {
            assertNotNull(context.getBean("storageMap"));
            assertNotNull(context.getBean("trainerDaoImpl"));
            assertNotNull(context.getBean("traineeDaoImpl"));
            assertNotNull(context.getBean("trainingDaoImpl"));
        }
    }
}

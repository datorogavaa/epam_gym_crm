package com.gym.crm;

import com.gym.crm.config.Config;
import com.gym.crm.storage.Storage;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CrmApplicationTests {

    @Test
    void contextLoads() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        Storage storage = context.getBean(Storage.class);
        assertNotNull(storage);

        assertNotNull(context.getBean("traineeMap"));
        assertNotNull(context.getBean("trainerMap"));
        assertNotNull(context.getBean("trainingMap"));
    }
}
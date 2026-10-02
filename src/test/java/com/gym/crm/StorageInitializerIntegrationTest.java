package com.gym.crm;

import com.gym.crm.config.Config;
import com.gym.crm.storage.Storage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
class StorageInitializerIntegrationTest {

    @Autowired
    private Storage storage;

    @Test
    void storageIsInitializedWithCsvDataOnStartup() {
        assertNotNull(storage);
        assertFalse(storage.getTrainers().isEmpty(), "Trainers map should contain CSV records");
        assertFalse(storage.getTrainees().isEmpty(), "Trainees map should contain CSV records");
        assertFalse(storage.getTrainings().isEmpty(), "Trainings map should contain CSV records");

        assertTrue(storage.getTrainers().containsKey(1L));
        assertEquals("Alex", storage.getTrainers().get(1L).getFirstName());

        assertTrue(storage.getTrainees().containsKey(101L));
        assertEquals("John", storage.getTrainees().get(101L).getFirstName());
    }
}
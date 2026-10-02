package com.gym.crm;

import com.gym.crm.domain.Trainee;
import com.gym.crm.domain.Trainer;
import com.gym.crm.domain.Training;
import com.gym.crm.storage.Storage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    void saveAndFindById() {
        Storage storage = new Storage();
        Trainee trainee = new Trainee();
        trainee.setUserId(1L);
        trainee.setFirstName("Alice");

        storage.save("Trainee", 1L, trainee);

        Object found = storage.findById("Trainee", 1L);
        assertNotNull(found, "Saved trainee should be found");
        assertTrue(found instanceof Trainee, "Found object should be a Trainee");
        assertEquals(1L, ((Trainee) found).getUserId());
        assertEquals("Alice", ((Trainee) found).getFirstName());
    }

    @Test
    void deleteRemovesEntry() {
        Storage storage = new Storage();
        Trainee trainee = new Trainee();
        trainee.setUserId(2L);

        storage.save("Trainee", 2L, trainee);
        assertNotNull(storage.findById("Trainee", 2L));

        storage.delete("Trainee", 2L);
        assertNull(storage.findById("Trainee", 2L), "Deleted trainee should not be found");
    }

    @Test
    void updateReplacesValue() {
        Storage storage = new Storage();
        Trainee original = new Trainee();
        original.setUserId(3L);
        original.setFirstName("Original");

        storage.save("Trainee", 3L, original);

        Trainee updated = new Trainee();
        updated.setUserId(3L);
        updated.setFirstName("Updated");

        storage.update("Trainee", 3L, updated);

        Object found = storage.findById("Trainee", 3L);
        assertNotNull(found);
        assertTrue(found instanceof Trainee);
        assertEquals("Updated", ((Trainee) found).getFirstName());
    }

    @Test
    void initPopulatesSampleData() {
        Storage storage = new Storage();
        storage.init();

        assertNotNull(storage.getTrainers(), "Trainers map should not be null");
        Trainer trainer = storage.getTrainers().get(1L);
        assertNotNull(trainer, "Trainer with ID 1 should be present after init");
        assertEquals("Alex", trainer.getFirstName());

        assertNotNull(storage.getTrainees(), "Trainees map should not be null");
        Trainee trainee = storage.getTrainees().get(101L);
        assertNotNull(trainee, "Trainee with ID 101 should be present after init");
        assertEquals("John", trainee.getFirstName());

        assertNotNull(storage.getTrainings(), "Trainings map should not be null");
        Training training = storage.getTrainings().get(1001L);
        assertNotNull(training, "Training with ID 1001 should be present after init");
        assertEquals(101L, training.getTraineeId());
        assertEquals(1L, training.getTrainerId());
    }

    @Test
    void saveCreatesNewKeyIfMissing() {
        Storage storage = new Storage();
        Object obj = new Object();
        storage.save("CustomKey", 500L, obj);
        assertEquals(obj, storage.findById("CustomKey", 500L));
    }
}

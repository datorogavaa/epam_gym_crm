package com.gym.crm.storage;

import com.gym.crm.domain.Trainee;
import com.gym.crm.domain.Trainer;
import com.gym.crm.domain.Training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StorageTest {

    @Spy
    private Map<Long, Trainee> trainees = new ConcurrentHashMap<>();

    @Spy
    private Map<Long, Trainer> trainers = new ConcurrentHashMap<>();

    @Spy
    private Map<Long, Training> trainings = new ConcurrentHashMap<>();

    @InjectMocks
    private Storage storage;

    @BeforeEach
    void setUp() {
        storage.getTrainees().clear();
        storage.getTrainers().clear();
        storage.getTrainings().clear();
    }


    @Test
    void directMapOperationsReflectInStorageMaps() {

        Trainee trainee = new Trainee();
        trainee.setUserId(202L);
        trainee.setFirstName("Sarah");
        storage.getTrainees().put(202L, trainee);

        assertTrue(storage.getTrainees().containsKey(202L));
        assertEquals("Sarah", storage.getTrainees().get(202L).getFirstName());

        Trainer trainer = new Trainer();
        trainer.setUserId(2L);
        trainer.setFirstName("Bob");
        storage.getTrainers().put(2L, trainer);

        assertTrue(storage.getTrainers().containsKey(2L));
        assertEquals("Bob", storage.getTrainers().get(2L).getFirstName());

        Training training = new Training();
        training.setTrainingName("Evening Yoga");
        storage.getTrainings().put(5001L, training);

        assertTrue(storage.getTrainings().containsKey(5001L));
        assertEquals("Evening Yoga", storage.getTrainings().get(5001L).getTrainingName());
    }

    @Test
    void removeOperationsWorkAcrossDistinctMaps() {
        Trainee trainee = new Trainee();
        trainee.setUserId(303L);
        storage.getTrainees().put(303L, trainee);

        assertNotNull(storage.getTrainees().remove(303L));
        assertNull(storage.getTrainees().get(303L));
        assertTrue(storage.getTrainees().isEmpty());
    }
}
package com.gym.crm.dao;

import com.gym.crm.domain.Trainer;
import com.gym.crm.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainerDaoImplTest {

    @Mock
    private Storage storage;

    @InjectMocks
    private TrainerDaoImpl trainerDao;

    private Map<Long, Trainer> mockTrainersMap;

    @BeforeEach
    void setUp() {
        mockTrainersMap = new ConcurrentHashMap<>();
        lenient().when(storage.getTrainers()).thenReturn(mockTrainersMap);
    }

    @Test
    void saveAndFindById() {
        Trainer trainer = new Trainer();
        trainer.setUserId(1L);
        trainer.setFirstName("Alex");

        trainerDao.save(trainer);
        Trainer found = trainerDao.findById(1L);

        assertNotNull(found);
        assertEquals("Alex", found.getFirstName());
    }

    @Test
    void updateModifiesExistingTrainer() {
        Trainer trainer = new Trainer();
        trainer.setUserId(1L);
        trainer.setFirstName("Alex");
        trainerDao.save(trainer);

        Trainer updated = new Trainer();
        updated.setUserId(1L);
        updated.setFirstName("Alexander");
        trainerDao.update(updated);

        Trainer result = trainerDao.findById(1L);
        assertNotNull(result);
        assertEquals("Alexander", result.getFirstName());
    }

    @Test
    void findByIdReturnsNullForMissingId() {
        assertNull(trainerDao.findById(999L));
        assertNull(trainerDao.findById(null));
    }

    @Test
    void findAllReturnsAllTrainers() {
        Trainer trainer = new Trainer();
        trainer.setUserId(1L);
        trainerDao.save(trainer);

        Map<Long, Trainer> result = trainerDao.findAll();
        assertEquals(1, result.size());
        assertTrue(result.containsKey(1L));
    }

    @Test
    void saveShouldIgnoreNullOrMissingId() {
        trainerDao.save(null);
        Trainer withoutId = new Trainer();
        trainerDao.save(withoutId);

        assertTrue(trainerDao.findAll().isEmpty());
    }

    @Test
    void updateShouldIgnoreNullOrMissingId() {
        Trainer valid = new Trainer();
        valid.setUserId(1L);
        valid.setFirstName("Alex");
        trainerDao.save(valid);

        trainerDao.update(null);
        Trainer missingId = new Trainer();
        missingId.setFirstName("Alexander");
        trainerDao.update(missingId);

        assertEquals("Alex", trainerDao.findById(1L).getFirstName());
    }
}
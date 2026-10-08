package com.gym.crm.service;

import com.gym.crm.dao.TrainerDao;
import com.gym.crm.domain.Trainer;
import com.gym.crm.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TrainerServiceTest {

    private TrainerService service;
    private TrainerDao trainerDao;
    private Storage storage;

    @BeforeEach
    void setUp() {
        service = new TrainerService();
        trainerDao = mock(TrainerDao.class);
        storage = mock(Storage.class);

        when(storage.getTrainers()).thenReturn(new HashMap<>());
        when(storage.getTrainees()).thenReturn(new HashMap<>());

        service.setTrainerDao(trainerDao);
        service.setStorage(storage);
    }

    @Test
    void save_shouldDelegateToDao() {
        Trainer trainer = new Trainer();

        service.save(trainer);

        verify(trainerDao).save(trainer);
    }

    @Test
    void update_shouldDelegateToDao() {
        Trainer trainer = new Trainer();

        service.update(trainer);

        verify(trainerDao).update(trainer);
    }

    @Test
    void findById_shouldReturnTrainerFromDao() {
        Trainer trainer = new Trainer();
        when(trainerDao.findById(7L)).thenReturn(trainer);

        Trainer result = service.findById(7L);

        assertSame(trainer, result);
    }

    @Test
    void findAll_shouldReturnAllTrainersFromDao() {
        Map<Long, Trainer> trainers = Map.of(1L, new Trainer(), 2L, new Trainer());
        when(trainerDao.findAll()).thenReturn(trainers);

        Map<Long, Trainer> result = service.findAll();

        assertEquals(trainers, result);
    }

    @Test
    void findById_shouldReturnNullWhenDaoReturnsNull() {
        when(trainerDao.findById(404L)).thenReturn(null);

        Trainer result = service.findById(404L);

        assertEquals(null, result);
    }

    @Test
    void findAll_shouldReturnEmptyMapWhenNoTrainersExist() {
        when(trainerDao.findAll()).thenReturn(Map.of());

        Map<Long, Trainer> result = service.findAll();

        assertEquals(Map.of(), result);
    }
}

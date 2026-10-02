package com.gym.crm.dao;

import com.gym.crm.domain.Trainer;
import com.gym.crm.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainerDaoImplTest {

    @Mock
    private Storage storage;

    private TrainerDaoImpl trainerDao;

    @BeforeEach
    void setUp() {
        trainerDao = new TrainerDaoImpl();
        trainerDao.setStorage(storage);
    }

    @Test
    void save_shouldDelegateToStorage() {
        Trainer trainer = new Trainer();
        trainer.setUserId(10L);

        trainerDao.save(trainer);

        verify(storage).save("Trainer", 10L, trainer);
    }

    @Test
    void findById_shouldReturnStoredTrainer() {
        Trainer trainer = new Trainer();
        trainer.setUserId(22L);
        when(storage.findById("Trainer", 22L)).thenReturn(trainer);

        Trainer result = trainerDao.findById(22L);

        assertSame(trainer, result);
    }

    @Test
    void update_shouldDelegateToStorage() {
        Trainer trainer = new Trainer();
        trainer.setUserId(33L);

        trainerDao.update(trainer);

        verify(storage).update("Trainer", 33L, trainer);
    }

    @Test
    void findAll_shouldReturnAllTrainers() {
        Trainer trainer = new Trainer();
        Map<Long, Trainer> expected = Map.of(1L, trainer);
        when(storage.getTrainers()).thenReturn(expected);

        Map<Long, Trainer> result = trainerDao.findAll();

        assertEquals(expected, result);
    }
}

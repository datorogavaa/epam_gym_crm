package com.gym.crm.dao;

import com.gym.crm.domain.Trainee;
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
class TraineeDaoImplTest {

    @Mock
    private Storage storage;

    private TraineeDaoImpl traineeDao;

    @BeforeEach
    void setUp() {
        traineeDao = new TraineeDaoImpl();
        traineeDao.setStorage(storage);
    }

    @Test
    void save_shouldDelegateToStorage() {
        Trainee trainee = new Trainee();
        trainee.setUserId(10L);

        traineeDao.save(trainee);

        verify(storage).save("Trainee", 10L, trainee);
    }

    @Test
    void findById_shouldReturnStoredTrainee() {
        Trainee trainee = new Trainee();
        trainee.setUserId(22L);
        when(storage.findById("Trainee", 22L)).thenReturn(trainee);

        Trainee result = traineeDao.findById(22L);

        assertSame(trainee, result);
    }

    @Test
    void update_shouldDelegateToStorage() {
        Trainee trainee = new Trainee();
        trainee.setUserId(33L);

        traineeDao.update(trainee);

        verify(storage).update("Trainee", 33L, trainee);
    }

    @Test
    void delete_shouldDelegateToStorage() {
        traineeDao.delete(44L);

        verify(storage).delete("Trainee", 44L);
    }

    @Test
    void findAll_shouldReturnAllTrainees() {
        Trainee trainee = new Trainee();
        Map<Long, Trainee> expected = Map.of(1L, trainee);
        when(storage.getTrainees()).thenReturn(expected);

        Map<Long, Trainee> result = traineeDao.findAll();

        assertEquals(expected, result);
    }

    @Test
    void findById_whenNotFound_shouldReturnNull() {
        when(storage.findById("Trainee", 999L)).thenReturn(null);

        Trainee result = traineeDao.findById(999L);

        assertSame(null, result);
    }
}

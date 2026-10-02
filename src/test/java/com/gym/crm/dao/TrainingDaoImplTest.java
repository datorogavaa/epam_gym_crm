package com.gym.crm.dao;

import com.gym.crm.domain.Training;
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
class TrainingDaoImplTest {

    @Mock
    private Storage storage;

    private TrainingDaoImpl trainingDao;

    @BeforeEach
    void setUp() {
        trainingDao = new TrainingDaoImpl();
        trainingDao.setStorage(storage);
    }

    @Test
    void save_shouldAssignIdAndStoreTraining() {
        Training training = new Training();
        training.setTrainingName("Cardio");

        trainingDao.save(training);

        verify(storage).save("Training", 1L, training);
    }

    @Test
    void findById_shouldReturnStoredTraining() {
        Training training = new Training();
        training.setTrainingName("Strength");
        when(storage.findById("Training", 7L)).thenReturn(training);

        Training result = trainingDao.findById(7L);

        assertSame(training, result);
    }

    @Test
    void findAll_shouldReturnAllTrainings() {
        Training training = new Training();
        Map<Long, Training> expected = Map.of(1L, training);
        when(storage.getTrainings()).thenReturn(expected);

        Map<Long, Training> result = trainingDao.findAll();

        assertEquals(expected, result);
    }
}

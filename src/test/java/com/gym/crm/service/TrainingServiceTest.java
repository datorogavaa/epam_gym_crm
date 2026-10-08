package com.gym.crm.service;

import com.gym.crm.dao.TrainingDao;
import com.gym.crm.domain.Training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TrainingServiceTest {

    private TrainingService service;
    private TrainingDao trainingDao;

    @BeforeEach
    void setUp() {
        service = new TrainingService();
        trainingDao = mock(TrainingDao.class);

        service.setTrainingDao(trainingDao);
    }

    @Test
    void save_shouldDelegateToDao() {
        Training training = new Training();

        service.save(training);

        verify(trainingDao).save(training);
    }

    @Test
    void findById_shouldReturnTrainingFromDao() {
        Training training = new Training();
        when(trainingDao.findById(99L)).thenReturn(training);

        Training result = service.findById(99L);

        assertSame(training, result);
    }

    @Test
    void findAll_shouldReturnAllTrainingsFromDao() {
        Map<Long, Training> trainings = Map.of(1L, new Training(), 2L, new Training());
        when(trainingDao.findAll()).thenReturn(trainings);

        Map<Long, Training> result = service.findAll();

        assertEquals(trainings, result);
    }

    @Test
    void findById_shouldReturnNullWhenDaoReturnsNull() {
        when(trainingDao.findById(404L)).thenReturn(null);

        Training result = service.findById(404L);

        assertEquals(null, result);
    }

    @Test
    void findAll_shouldReturnEmptyMapWhenNoTrainingsExist() {
        when(trainingDao.findAll()).thenReturn(Map.of());

        Map<Long, Training> result = service.findAll();

        assertEquals(Map.of(), result);
    }
}

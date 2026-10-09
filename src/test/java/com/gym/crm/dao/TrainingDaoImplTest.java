package com.gym.crm.dao;

import com.gym.crm.domain.Training;
import com.gym.crm.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;


@ExtendWith(MockitoExtension.class)
class TrainingDaoImplTest {

    @Mock
    private Storage storage;

    @InjectMocks
    private TrainingDaoImpl trainingDao;

    private Map<Long, Training> mockTrainingsMap;

    @BeforeEach
    void setUp() {
        mockTrainingsMap = new ConcurrentHashMap<>();
        lenient().when(storage.getTrainings()).thenReturn(mockTrainingsMap);
    }

    @Test
    void saveStoresTrainingWithGeneratedKey() {
        Training training = new Training();
        training.setTraineeId(101L);
        training.setTrainerId(1L);
        training.setTrainingName("Morning Cardio Kick");
        training.setTrainingDate(LocalDate.of(2026, 9, 25));
        training.setTrainingDuration(Duration.ofMinutes(60));

        trainingDao.save(training);
        assertEquals(1, mockTrainingsMap.size());

        Long generatedKey = mockTrainingsMap.keySet().iterator().next();
        Training retrieved = trainingDao.findById(generatedKey);

        assertNotNull(retrieved);
        assertEquals("Morning Cardio Kick", retrieved.getTrainingName());
        assertEquals(101L, retrieved.getTraineeId());
        assertEquals(1L, retrieved.getTrainerId());
    }

    @Test
    void findByIdReturnsNullWhenNotFoundOrNull() {
        assertNull(trainingDao.findById(9999L));
        assertNull(trainingDao.findById(null));
    }

    @Test
    void findAllReturnsCompleteMap() {
        Training training = new Training();
        training.setTrainingName("Yoga Flow");
        trainingDao.save(training);

        Map<Long, Training> result = trainingDao.findAll();

        assertEquals(1, result.size());
        assertTrue(result.containsValue(training));
    }

    @Test
    void saveShouldIgnoreNullTraining() {
        trainingDao.save(null);

        assertTrue(trainingDao.findAll().isEmpty());
    }

    @Test
    void findByIdReturnsNullForNullKey() {
        assertNull(trainingDao.findById(null));
    }
}

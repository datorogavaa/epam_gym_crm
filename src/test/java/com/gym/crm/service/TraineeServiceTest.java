package com.gym.crm.service;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.domain.Trainee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TraineeServiceTest {

    private TraineeService service;
    private TraineeDao traineeDao;

    @BeforeEach
    void setUp() throws Exception {
        service = new TraineeService();
        traineeDao = mock(TraineeDao.class);

        Method setter = TraineeService.class.getDeclaredMethod("setTraineeDao", TraineeDao.class);
        setter.setAccessible(true);
        setter.invoke(service, traineeDao);
    }

    @Test
    void save_shouldDelegateToDao() {
        Trainee trainee = new Trainee();

        service.save(trainee);

        verify(traineeDao).save(trainee);
    }

    @Test
    void update_shouldDelegateToDao() {
        Trainee trainee = new Trainee();

        service.update(trainee);

        verify(traineeDao).update(trainee);
    }

    @Test
    void findById_shouldReturnTraineeFromDao() {
        Trainee trainee = new Trainee();
        when(traineeDao.findById(8L)).thenReturn(trainee);

        Trainee result = service.findById(8L);

        assertSame(trainee, result);
    }

    @Test
    void deleteById_shouldDelegateToDao() {
        service.deleteById(8L);

        verify(traineeDao).delete(8L);
    }

    @Test
    void findAll_shouldReturnAllTraineesFromDao() {
        Map<Long, Trainee> trainees = Map.of(1L, new Trainee(), 2L, new Trainee());
        when(traineeDao.findAll()).thenReturn(trainees);

        Map<Long, Trainee> result = service.findAll();

        assertEquals(trainees, result);
    }

    @Test
    void findById_shouldReturnNullWhenDaoReturnsNull() {
        when(traineeDao.findById(404L)).thenReturn(null);

        Trainee result = service.findById(404L);

        assertEquals(null, result);
    }

    @Test
    void findAll_shouldReturnEmptyMapWhenNoTraineesExist() {
        when(traineeDao.findAll()).thenReturn(Map.of());

        Map<Long, Trainee> result = service.findAll();

        assertEquals(Map.of(), result);
    }
}

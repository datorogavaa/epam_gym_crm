package com.gym.crm.service;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.domain.Trainee;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TraineeServiceTest {

    @Test
    void testSaveTraineeGeneratesCredentialsAndSaves() {
        TraineeDao traineeDao = Mockito.mock(TraineeDao.class);
        TraineeService traineeService = new TraineeService();
        traineeService.setTraineeDao(traineeDao);

        Trainee trainee = new Trainee();
        trainee.setFirstName("John");
        trainee.setLastName("Doe");

        when(traineeDao.findAll()).thenReturn(new HashMap<>());

        traineeService.save(trainee);

        assertNotNull(trainee.getUsername());
        assertEquals("John.Doe", trainee.getUsername());
        assertNotNull(trainee.getPassword());
        assertEquals(10, trainee.getPassword().length());

        verify(traineeDao, times(1)).save(trainee);
    }

    @Test
    void testFindById() {
        TraineeDao traineeDao = Mockito.mock(TraineeDao.class);
        TraineeService traineeService = new TraineeService();
        traineeService.setTraineeDao(traineeDao);

        Trainee trainee = new Trainee();
        trainee.setUserId(1L);
        trainee.setFirstName("John");
        trainee.setLastName("Doe");

        when(traineeDao.findById(1L)).thenReturn(trainee);

        Trainee found = traineeService.findById(1L);

        assertNotNull(found);
        assertEquals(1L, found.getUserId());
        assertEquals("John", found.getFirstName());
        verify(traineeDao, times(1)).findById(1L);
    }

    @Test
    void testUpdate() {
        TraineeDao traineeDao = Mockito.mock(TraineeDao.class);
        TraineeService traineeService = new TraineeService();
        traineeService.setTraineeDao(traineeDao);

        Trainee trainee = new Trainee();
        trainee.setUserId(1L);

        traineeService.update(trainee);
        verify(traineeDao, times(1)).update(trainee);
    }

    @Test
    void testDeleteById() {
        TraineeDao traineeDao = Mockito.mock(TraineeDao.class);
        TraineeService traineeService = new TraineeService();
        traineeService.setTraineeDao(traineeDao);

        traineeService.deleteById(1L);
        verify(traineeDao, times(1)).delete(1L);
    }

    @Test
    void testFindAll() {
        TraineeDao traineeDao = Mockito.mock(TraineeDao.class);
        TraineeService traineeService = new TraineeService();
        traineeService.setTraineeDao(traineeDao);

        Trainee trainee = new Trainee();
        Map<Long, Trainee> mockMap = new HashMap<>();
        mockMap.put(1L, trainee);
        when(traineeDao.findAll()).thenReturn(mockMap);

        Map<Long, Trainee> result = traineeService.findAll();

        assertEquals(1, result.size());
        verify(traineeDao, times(1)).findAll();
    }
}
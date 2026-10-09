package com.gym.crm.service;

import com.gym.crm.dao.TrainerDao;
import com.gym.crm.domain.Trainer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainerServiceTest {

    @Test
    void testSaveTrainerGeneratesCredentialsAndSaves() {
        TrainerDao trainerDao = Mockito.mock(TrainerDao.class);
        TrainerService trainerService = new TrainerService();
        trainerService.setTrainerDao(trainerDao);

        Trainer trainer = new Trainer();
        trainer.setFirstName("Jane");
        trainer.setLastName("Smith");

        when(trainerDao.findAll()).thenReturn(new HashMap<>());

        trainerService.save(trainer);

        assertNotNull(trainer.getUsername());
        assertEquals("Jane.Smith", trainer.getUsername());
        assertNotNull(trainer.getPassword());
        assertEquals(10, trainer.getPassword().length());

        verify(trainerDao, times(1)).save(trainer);
    }

    @Test
    void testFindById() {
        TrainerDao trainerDao = Mockito.mock(TrainerDao.class);
        TrainerService trainerService = new TrainerService();
        trainerService.setTrainerDao(trainerDao);

        Trainer trainer = new Trainer();
        trainer.setUserId(2L);
        trainer.setFirstName("Jane");
        trainer.setLastName("Smith");

        when(trainerDao.findById(2L)).thenReturn(trainer);

        Trainer found = trainerService.findById(2L);

        assertNotNull(found);
        assertEquals(2L, found.getUserId());
        assertEquals("Jane", found.getFirstName());
        verify(trainerDao, times(1)).findById(2L);
    }

    @Test
    void testUpdate() {
        TrainerDao trainerDao = Mockito.mock(TrainerDao.class);
        TrainerService trainerService = new TrainerService();
        trainerService.setTrainerDao(trainerDao);

        Trainer trainer = new Trainer();
        trainer.setUserId(2L);

        trainerService.update(trainer);
        verify(trainerDao, times(1)).update(trainer);
    }

    @Test
    void testFindAll() {
        TrainerDao trainerDao = Mockito.mock(TrainerDao.class);
        TrainerService trainerService = new TrainerService();
        trainerService.setTrainerDao(trainerDao);

        Trainer trainer = new Trainer();
        Map<Long, Trainer> mockMap = new HashMap<>();
        mockMap.put(2L, trainer);
        when(trainerDao.findAll()).thenReturn(mockMap);

        Map<Long, Trainer> result = trainerService.findAll();

        assertEquals(1, result.size());
        verify(trainerDao, times(1)).findAll();
    }
}
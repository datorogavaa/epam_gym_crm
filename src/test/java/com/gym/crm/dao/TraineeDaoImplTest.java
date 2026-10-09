package com.gym.crm.dao;

import com.gym.crm.domain.Trainee;
import com.gym.crm.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;


@ExtendWith(MockitoExtension.class)
class TraineeDaoImplTest {

    @Mock
    private Storage storage;

    @InjectMocks
    private TraineeDaoImpl traineeDao;

    private Map<Long, Trainee> mockTraineesMap;

    @BeforeEach
    void setUp() {
        mockTraineesMap = new ConcurrentHashMap<>();
        lenient().when(storage.getTrainees()).thenReturn(mockTraineesMap);
    }

    @Test
    void saveAndFindById() {
        Trainee trainee = new Trainee();
        trainee.setUserId(101L);
        trainee.setFirstName("John");

        traineeDao.save(trainee);
        Trainee found = traineeDao.findById(101L);

        assertNotNull(found);
        assertEquals("John", found.getFirstName());
    }

    @Test
    void updateModifiesExistingTrainee() {
        Trainee trainee = new Trainee();
        trainee.setUserId(101L);
        trainee.setFirstName("John");
        traineeDao.save(trainee);

        Trainee updated = new Trainee();
        updated.setUserId(101L);
        updated.setFirstName("Johnny");
        traineeDao.update(updated);

        Trainee result = traineeDao.findById(101L);
        assertEquals("Johnny", result.getFirstName());
    }

    @Test
    void deleteRemovesTrainee() {
        Trainee trainee = new Trainee();
        trainee.setUserId(101L);
        traineeDao.save(trainee);

        traineeDao.delete(101L);

        assertNull(traineeDao.findById(101L));
    }


    @Test
    void saveShouldIgnoreNullOrMissingId() {
        traineeDao.save(null);
        Trainee withoutId = new Trainee();
        traineeDao.save(withoutId);

        assertTrue(traineeDao.findAll().isEmpty());
    }

    @Test
    void updateShouldIgnoreNullOrMissingId() {
        Trainee valid = new Trainee();
        valid.setUserId(101L);
        valid.setFirstName("John");
        traineeDao.save(valid);

        traineeDao.update(null);
        Trainee missingId = new Trainee();
        missingId.setFirstName("Jane");
        traineeDao.update(missingId);

        assertEquals("John", traineeDao.findById(101L).getFirstName());
    }

    @Test
    void deleteShouldIgnoreNullId() {
        Trainee trainee = new Trainee();
        trainee.setUserId(101L);
        traineeDao.save(trainee);

        traineeDao.delete(null);

        assertNotNull(traineeDao.findById(101L));
    }
}
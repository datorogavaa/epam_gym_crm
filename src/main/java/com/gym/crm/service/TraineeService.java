package com.gym.crm.service;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.domain.Trainee;
import com.gym.crm.storage.Storage;
import com.gym.crm.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

@Service
public class TraineeService {
    private TraineeDao traineeDao;
    private Storage storage;

    Logger logger = Logger.getLogger(TraineeService.class.getName());

    @Autowired
    public void setTraineeDao(TraineeDao traineeDao) {
        this.traineeDao = traineeDao;
    }

    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void save(Trainee trainee) {
        logger.info("Saving trainee: " + trainee.getUserId());
        String baseUsername = Util.usernameGenerator(trainee.getFirstName(), trainee.getLastName());
        trainee.setUsername(Util.generateUniqueUsername(baseUsername, storage.getTrainees().values(), storage.getTrainers().values()));
        trainee.setPassword(Util.passwordGenerator(10));
        traineeDao.save(trainee);
    }

    public void update(Trainee updatedTrainee) {
        logger.info("Updating trainee: " + updatedTrainee.getUserId());
        traineeDao.update(updatedTrainee);
    }

    public Trainee findById(Long id) {
        logger.info("Finding trainee by ID: " + id);
        return traineeDao.findById(id);
    }

    public void deleteById(Long id) {
        logger.info("Deleting trainee by ID: " + id);
        traineeDao.delete(id);
    }


    public Map<Long, Trainee> findAll() {
        logger.info("Retrieving all trainees");
        return traineeDao.findAll();
    }


}

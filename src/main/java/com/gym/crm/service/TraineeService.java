package com.gym.crm.service;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.domain.Trainee;
import com.gym.crm.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

@Service
public class TraineeService {

    private TraineeDao traineeDao;
    private final Logger logger = Logger.getLogger(TraineeService.class.getName());

    public TraineeService() {
    }

    @Autowired
    public void setTraineeDao(TraineeDao traineeDao) {
        this.traineeDao = traineeDao;
    }

    public void save(Trainee trainee) {
        if (trainee == null) {
            logger.warning("Attempted to save a null trainee object.");
            return;
        }

        String baseUsername = Util.usernameGenerator(trainee.getFirstName(), trainee.getLastName());
        String uniqueUsername = Util.generateUniqueUsername(
                baseUsername,
                traineeDao.findAll().values()
        );

        trainee.setUsername(uniqueUsername);
        trainee.setPassword(Util.passwordGenerator(10));

        traineeDao.save(trainee);
        logger.info("Successfully saved trainee profile with username: " + trainee.getUsername());
    }

    public void update(Trainee updatedTrainee) {
        if (updatedTrainee == null) {
            logger.warning("Attempted to update a null trainee object.");
            return;
        }
        traineeDao.update(updatedTrainee);
        logger.info("Successfully updated trainee profile with ID: " + updatedTrainee.getUserId());
    }

    public Trainee findById(Long userId) {
        if (userId == null) {
            return null;
        }
        logger.info("Finding trainee by ID: " + userId);
        return traineeDao.findById(userId);
    }

    public void deleteById(Long userId) {
        if (userId == null) {
            return;
        }
        traineeDao.delete(userId);
        logger.info("Deleted trainee profile with ID: " + userId);
    }

    public Map<Long, Trainee> findAll() {
        logger.info("Retrieving all trainee profiles.");
        return traineeDao.findAll();
    }
}
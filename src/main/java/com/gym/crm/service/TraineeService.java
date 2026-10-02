package com.gym.crm.service;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.domain.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

@Service
public class TraineeService {
    private TraineeDao traineeDao;

    Logger logger = Logger.getLogger(TraineeService.class.getName());

    @Autowired
    public void setTraineeDao(TraineeDao traineeDao) {
        this.traineeDao = traineeDao;
    }

    public void save(Trainee trainee) {
        logger.info("Saving trainee: " + trainee.getUserId());
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

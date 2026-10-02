package com.gym.crm.service;

import com.gym.crm.dao.TrainingDao;
import com.gym.crm.dao.TrainingDaoImpl;
import com.gym.crm.domain.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

@Service
public class TrainingService {

    private TrainingDao trainingDao;
    Logger logger = Logger.getLogger(TrainingService.class.getName());
    @Autowired
    public void setTrainingDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    public void save(Training training) {
        logger.info("Saving training with name : " + training.getTrainingName());
        trainingDao.save(training);
    }
    public Training findById(Long id) {
        logger.info("Finding training with ID: " + id);
        return trainingDao.findById(id);
    }
    public Map<Long, Training> findAll() {
        logger.info("Finding all trainings");
        return trainingDao.findAll();
    }

}

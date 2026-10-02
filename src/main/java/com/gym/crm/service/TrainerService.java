package com.gym.crm.service;

import com.gym.crm.dao.TrainerDao;
import com.gym.crm.domain.Trainer;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

@Service
public class TrainerService {


    private TrainerDao trainerDao;
    Logger logger = Logger.getLogger(TrainerService.class.getName());
    @Autowired
    public void setTrainerDao(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    public void save(com.gym.crm.domain.Trainer trainer) {
        logger.info("Saving trainer with ID: " + trainer.getUserId());
        trainerDao.save(trainer);
    }
    public void update(com.gym.crm.domain.Trainer updatedTrainer) {
        logger.info("Updating trainer with ID: " + updatedTrainer.getUserId());
        trainerDao.update(updatedTrainer);
    }

    public Trainer findById(Long id) {
        logger.info("Finding trainer with ID: " + id);
        return trainerDao.findById(id);
    }

    public Map<Long, Trainer> findAll() {
        logger.info("Finding all trainers");
        return trainerDao.findAll();
    }

}

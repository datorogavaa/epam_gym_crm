package com.gym.crm.service;

import com.gym.crm.dao.TrainerDao;
import com.gym.crm.domain.Trainer;
import com.gym.crm.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

@Service
public class TrainerService {

    private TrainerDao trainerDao;
    private final Logger logger = Logger.getLogger(TrainerService.class.getName());

    public TrainerService() {
    }


    @Autowired
    public void setTrainerDao(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    public void save(Trainer trainer) {
        if (trainer == null) {
            logger.warning("Attempted to save a null trainer object.");
            return;
        }

        String baseUsername = Util.usernameGenerator(trainer.getFirstName(), trainer.getLastName());
        String uniqueUsername = Util.generateUniqueUsername(
                baseUsername,
                trainerDao.findAll().values()
        );

        trainer.setUsername(uniqueUsername);
        trainer.setPassword(Util.passwordGenerator(10));

        trainerDao.save(trainer);
        logger.info("Successfully saved trainer profile with username: " + trainer.getUsername());
    }

    public void update(Trainer updatedTrainer) {
        if (updatedTrainer == null) {
            logger.warning("Attempted to update a null trainer object.");
            return;
        }
        trainerDao.update(updatedTrainer);
        logger.info("Successfully updated trainer profile with ID: " + updatedTrainer.getUserId());
    }

    public Trainer findById(Long userId) {
        if (userId == null) {
            return null;
        }
        logger.info("Finding trainer by ID: " + userId);
        return trainerDao.findById(userId);
    }

    public Map<Long, Trainer> findAll() {
        logger.info("Retrieving all trainer profiles.");
        return trainerDao.findAll();
    }
}
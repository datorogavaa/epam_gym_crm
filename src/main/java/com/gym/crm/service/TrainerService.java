package com.gym.crm.service;

import com.gym.crm.dao.TrainerDao;
import com.gym.crm.domain.Trainer;
import com.gym.crm.storage.Storage;
import com.gym.crm.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

@Service
public class TrainerService {


    private TrainerDao trainerDao;
    private Storage storage;
    Logger logger = Logger.getLogger(TrainerService.class.getName());
    @Autowired
    public void setTrainerDao(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void save(com.gym.crm.domain.Trainer trainer) {
        logger.info("Saving trainer with ID: " + trainer.getUserId());
        String baseUsername = Util.usernameGenerator(trainer.getFirstName(), trainer.getLastName());
        trainer.setUsername(generateUniqueUsername(baseUsername));
        trainer.setPassword(Util.passwordGenerator(10));
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

    private String generateUniqueUsername(String baseUsername) {
        String candidate = baseUsername;
        int suffix = 0;
        while (usernameExists(candidate)) {
            suffix++;
            candidate = baseUsername + suffix;
        }
        return candidate;
    }

    private boolean usernameExists(String username) {
        for (Trainer existingTrainer : storage.getTrainers().values()) {
            if (username.equals(existingTrainer.getUsername())) {
                return true;
            }
        }
        return storage.getTrainees().values().stream()
                .anyMatch(existingTrainee -> username.equals(existingTrainee.getUsername()));
    }

}

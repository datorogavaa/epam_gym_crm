package com.gym.crm.dao;

import com.gym.crm.domain.Trainer;
import com.gym.crm.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.logging.Logger;

@Repository
public class TrainerDaoImpl implements TrainerDao {

    private final Logger logger = Logger.getLogger(TrainerDaoImpl.class.getName());
    private Storage storage;

    public TrainerDaoImpl() {
    }

    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void save(Trainer trainer) {
        if (trainer == null || trainer.getUserId() == null) {
            logger.warning("Attempted to save null trainer or trainer with null ID");
            return;
        }
        storage.getTrainers().put(trainer.getUserId(), trainer);
        logger.info("Saved trainer with ID: " + trainer.getUserId());
    }

    @Override
    public Trainer findById(Long userId) {
        if (userId == null) {
            return null;
        }
        return storage.getTrainers().get(userId);
    }

    @Override
    public void update(Trainer updatedTrainer) {
        if (updatedTrainer == null || updatedTrainer.getUserId() == null) {
            logger.warning("Attempted to update null trainer or trainer with null ID");
            return;
        }
        storage.getTrainers().replace(updatedTrainer.getUserId(), updatedTrainer);
        logger.info("Updated trainer with ID: " + updatedTrainer.getUserId());
    }

    @Override
    public Map<Long, Trainer> findAll() {
        return storage.getTrainers();
    }
}
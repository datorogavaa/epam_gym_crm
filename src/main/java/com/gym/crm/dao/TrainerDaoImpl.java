package com.gym.crm.dao;

import com.gym.crm.domain.Trainer;
import com.gym.crm.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.logging.Logger;

@Repository
public class TrainerDaoImpl implements TrainerDao {
    private Storage storage;


    private Logger logger = Logger.getLogger(TrainerDaoImpl.class.getName());

    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void save(Trainer trainer) {
        storage.save(Trainer.class.getSimpleName(), trainer.getUserId(), trainer);
        logger.info("Saved trainer with ID: " + trainer.getUserId());
    }

    @Override
    public void update(Trainer updatedTrainer) {
        storage.update(Trainer.class.getSimpleName(), updatedTrainer.getUserId(), updatedTrainer);
        logger.info("Updated trainer with ID: " + updatedTrainer.getUserId());
    }

    @Override
    public Trainer findById(Long userId) {
        return (Trainer) storage.findById(Trainer.class.getSimpleName(), userId);
    }


    @Override
    public Map<Long, Trainer> findAll() {
        return storage.getTrainers();
    }
}

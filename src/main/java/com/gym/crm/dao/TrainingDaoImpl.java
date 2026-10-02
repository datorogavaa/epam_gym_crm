package com.gym.crm.dao;


import com.gym.crm.domain.Training;
import com.gym.crm.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.logging.Logger;

@Repository
public class TrainingDaoImpl implements TrainingDao {
    private Storage storage;
    private Long autoIncrementId = 1L;

    private Logger logger = Logger.getLogger(TrainingDaoImpl.class.getName());
    private Long getAutoIncrementId() {
        return autoIncrementId++;
    }

    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void save(Training training) {
        storage.save(Training.class.getSimpleName(),getAutoIncrementId(),training);
        logger.info("Saved training with Name: " + training.getTrainingName());
    }

    @Override
    public Training findById(Long id) {
        return (Training) storage.findById(Training.class.getSimpleName(), id);
    }

    @Override
    public Map<Long, Training> findAll() {
        return storage.getTrainings();
    }
}

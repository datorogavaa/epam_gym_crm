package com.gym.crm.dao;

import com.gym.crm.domain.Training;
import com.gym.crm.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Repository
public class TrainingDaoImpl implements TrainingDao {

    private final Logger logger = Logger.getLogger(TrainingDaoImpl.class.getName());
    private final AtomicLong idGenerator = new AtomicLong(1000L);
    private Storage storage;

    public TrainingDaoImpl() {
    }

    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void save(Training training) {
        if (training == null) {
            logger.warning("Attempted to save null training");
            return;
        }

        if (training.getId() == null) {
            training.setId(idGenerator.incrementAndGet());
        }
        storage.getTrainings().put(training.getId(), training);
        logger.info("Saved training with ID: " + training.getId());
    }

    @Override
    public Training findById(Long id) {
        if (id == null) {
            return null;
        }
        return storage.getTrainings().get(id);
    }

    @Override
    public Map<Long, Training> findAll() {
        logger.info("Retrieving all trainings");
        return storage.getTrainings();
    }
}
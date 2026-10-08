package com.gym.crm.dao;

import com.gym.crm.domain.Trainee;
import com.gym.crm.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.logging.Logger;

@Repository
public class TraineeDaoImpl implements TraineeDao {

    private final Logger logger = Logger.getLogger(TraineeDaoImpl.class.getName());
    private Storage storage;

    public TraineeDaoImpl() {
    }

    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void save(Trainee trainee) {
        if (trainee == null || trainee.getUserId() == null) {
            logger.warning("Attempted to save null trainee or trainee with null ID");
            return;
        }
        storage.getTrainees().put(trainee.getUserId(), trainee);
        logger.info("Saved trainee with ID: " + trainee.getUserId());
    }

    @Override
    public Trainee findById(Long userId) {
        if (userId == null) {
            return null;
        }
        return storage.getTrainees().get(userId);
    }

    @Override
    public void update(Trainee updatedTrainee) {
        if (updatedTrainee == null || updatedTrainee.getUserId() == null) {
            logger.warning("Attempted to update null trainee or trainee with null ID");
            return;
        }
        storage.getTrainees().replace(updatedTrainee.getUserId(), updatedTrainee);
        logger.info("Updated trainee with ID: " + updatedTrainee.getUserId());
    }

    @Override
    public void delete(Long userId) {
        if (userId == null) {
            return;
        }
        storage.getTrainees().remove(userId);
        logger.info("Deleted trainee with ID: " + userId);
    }

    @Override
    public Map<Long, Trainee> findAll() {
        return storage.getTrainees();
    }

}
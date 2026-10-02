package com.gym.crm.dao;

import com.gym.crm.domain.Trainee;
import com.gym.crm.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.logging.Logger;


@Repository
public class TraineeDaoImpl implements TraineeDao {

    private Storage storage;


    private final Logger logger = Logger.getLogger(TraineeDaoImpl.class.getName());

    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void save(Trainee trainee) {
        storage.save(Trainee.class.getSimpleName(), trainee.getUserId(), trainee);
        logger.info("Saved trainee with ID: " + trainee.getUserId());
    }

    @Override
    public void update(Trainee updatedTrainee) {
        storage.update(Trainee.class.getSimpleName(), updatedTrainee.getUserId(), updatedTrainee);
        logger.info("Updated trainee with ID: " + updatedTrainee.getUserId());
    }

    @Override
    public Trainee findById(Long userId) {
        return (Trainee) storage.findById(Trainee.class.getSimpleName(), userId);
    }

    @Override
    public void delete(Long userId) {
        storage.delete(Trainee.class.getSimpleName(), userId);
        logger.info("Deleted trainee with ID: " + userId);
    }

    @Override
    public Map<Long, Trainee> findAll() {
        return storage.getTrainees();
    }
}
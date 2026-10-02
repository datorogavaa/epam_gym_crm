package com.gym.crm.service;

import com.gym.crm.dao.TrainerDao;
import com.gym.crm.domain.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TrainerService {


    private TrainerDao trainerDao;

    @Autowired
    private void setTrainerDao(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    public void save(com.gym.crm.domain.Trainer trainer) {
        trainerDao.save(trainer);
    }
    public void update(com.gym.crm.domain.Trainer updatedTrainer) {
        trainerDao.update(updatedTrainer);
    }

    public Trainer findById(Long id) {
        return trainerDao.findById(id);
    }

    public Map<Long, Trainer> findAll() {
        return trainerDao.findAll();
    }

}

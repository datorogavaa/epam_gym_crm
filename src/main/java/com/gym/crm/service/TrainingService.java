package com.gym.crm.service;

import com.gym.crm.dao.TrainingDao;
import com.gym.crm.dao.TrainingDaoImpl;
import com.gym.crm.domain.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TrainingService {

    private TrainingDao trainingDao;

    @Autowired
    public void setTrainingDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    public void save(Training training) {
        trainingDao.save(training);
    }
    public Training findById(Long id) {
        return trainingDao.findById(id);
    }
    public Map<Long, Training> findAll() {
        return trainingDao.findAll();
    }

}

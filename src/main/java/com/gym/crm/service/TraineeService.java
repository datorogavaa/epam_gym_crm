package com.gym.crm.service;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.domain.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TraineeService {
    private TraineeDao traineeDao;

    @Autowired
    private void setTraineeDao(TraineeDao traineeDao) {
        this.traineeDao = traineeDao;
    }

    public void save(Trainee trainee) {
        traineeDao.save(trainee);
    }

    public void update(Trainee updatedTrainee) {
        traineeDao.update(updatedTrainee);
    }

    public Trainee findById(Long id) {
        return traineeDao.findById(id);
    }

    public void deleteById(Long id) {
        traineeDao.delete(id);
    }


    public Map<Long, Trainee> findAll() {
        return traineeDao.findAll();
    }


}

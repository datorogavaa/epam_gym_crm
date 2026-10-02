package com.gym.crm.dao;

import com.gym.crm.domain.Trainee;

import java.util.Map;

public interface TraineeDao {
    void  save(Trainee trainee);
    Trainee findById(Long userId);
    void update(Trainee updatedTrainee);
    void delete(Long userId);
    Map<Long, Trainee> findAll();
}

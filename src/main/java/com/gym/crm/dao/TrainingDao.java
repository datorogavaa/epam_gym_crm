package com.gym.crm.dao;

import com.gym.crm.domain.Trainer;
import com.gym.crm.domain.Training;

import java.util.Map;

public interface TrainingDao {
    void  save(Training training);
    Training findById(Long userId);
    Map<Long, Training> findAll();
}

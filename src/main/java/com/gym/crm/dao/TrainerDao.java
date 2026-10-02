package com.gym.crm.dao;

import com.gym.crm.domain.Trainer;

import java.util.Map;

public interface TrainerDao {
    void  save(Trainer trainer);
    Trainer findById(Long userId);
    void update(Trainer updatedTrainer);
    Map<Long, Trainer> findAll();
}

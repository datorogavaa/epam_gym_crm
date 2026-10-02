package com.gym.crm.facade;

import com.gym.crm.service.TraineeService;
import com.gym.crm.service.TrainerService;
import com.gym.crm.service.TrainingService;
import org.springframework.stereotype.Component;

@Component
public class Facade {

    private final TrainerService trainerService;
    private final TraineeService traineeService;
    private final TrainingService trainingService;

    public Facade(TrainerService trainerService, TraineeService traineeService, TrainingService trainingService) {
        this.trainerService = trainerService;
        this.traineeService = traineeService;
        this.trainingService = trainingService;
    }


}
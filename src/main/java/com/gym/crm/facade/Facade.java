package com.gym.crm.facade;

import com.gym.crm.domain.Trainee;
import com.gym.crm.domain.Trainer;
import com.gym.crm.domain.Training;
import com.gym.crm.service.TraineeService;
import com.gym.crm.service.TrainerService;
import com.gym.crm.service.TrainingService;
import org.springframework.stereotype.Component;

import java.util.Map;

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

    public void createTrainee(Trainee trainee) {
        traineeService.save(trainee);
    }

    public void updateTrainee(Trainee trainee) {
        traineeService.update(trainee);
    }

    public Trainee findTraineeById(Long id) {
        return traineeService.findById(id);
    }

    public void deleteTraineeById(Long id) {
        traineeService.deleteById(id);
    }

    public Map<Long, Trainee> findAllTrainees() {
        return traineeService.findAll();
    }

    public void createTrainer(Trainer trainer) {
        trainerService.save(trainer);
    }

    public void updateTrainer(Trainer trainer) {
        trainerService.update(trainer);
    }

    public Trainer findTrainerById(Long id) {
        return trainerService.findById(id);
    }

    public Map<Long, Trainer> findAllTrainers() {
        return trainerService.findAll();
    }

    public void addTraining(Training training) {
        trainingService.save(training);
    }

    public Training findTrainingById(Long id) {
        return trainingService.findById(id);
    }

    public Map<Long, Training> findAllTrainings() {
        return trainingService.findAll();
    }

}
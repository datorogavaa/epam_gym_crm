package com.gym.crm.storage;

import com.gym.crm.domain.Trainee;
import com.gym.crm.domain.Trainer;
import com.gym.crm.domain.Training;
import com.gym.crm.domain.TrainingType;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@Component
public class Storage {

    private final Logger logger = Logger.getLogger(Storage.class.getName());

    private  Map<Long, Trainee> trainees;
    private  Map<Long, Trainer> trainers;
    private  Map<Long, Training> trainings;


    public Storage() {
    }

    @Autowired
    public void setTrainees(@Qualifier("traineeMap") Map<Long, Trainee> trainees) {
        this.trainees = trainees;
    }

    @Autowired
    public void setTrainers(@Qualifier("trainerMap") Map<Long, Trainer> trainers) {
        this.trainers = trainers;
    }

    @Autowired
    public void setTrainings(@Qualifier("trainingMap") Map<Long, Training> trainings) {
        this.trainings = trainings;
    }

    public Map<Long, Trainee> getTrainees() {
        return trainees;
    }

    public Map<Long, Trainer> getTrainers() {
        return trainers;
    }

    public Map<Long, Training> getTrainings() {
        return trainings;
    }


}
package com.gym.crm.facade;

import com.gym.crm.domain.Trainee;
import com.gym.crm.domain.Trainer;
import com.gym.crm.domain.Training;
import com.gym.crm.service.TraineeService;
import com.gym.crm.service.TrainerService;
import com.gym.crm.service.TrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FacadeTest {

    private Facade facade;
    private TraineeService traineeService;
    private TrainerService trainerService;
    private TrainingService trainingService;

    @BeforeEach
    void setUp() {
        traineeService = mock(TraineeService.class);
        trainerService = mock(TrainerService.class);
        trainingService = mock(TrainingService.class);
        facade = new Facade(trainerService, traineeService, trainingService);
    }

    @Test
    void createTrainee_shouldDelegateToService() {
        Trainee trainee = new Trainee();
        facade.createTrainee(trainee);
        verify(traineeService).save(trainee);
    }

    @Test
    void updateTrainee_shouldDelegateToService() {
        Trainee trainee = new Trainee();
        facade.updateTrainee(trainee);
        verify(traineeService).update(trainee);
    }

    @Test
    void findTraineeById_shouldReturnServiceResult() {
        Trainee trainee = new Trainee();
        when(traineeService.findById(1L)).thenReturn(trainee);
        assertSame(trainee, facade.findTraineeById(1L));
    }

    @Test
    void deleteTraineeById_shouldDelegateToService() {
        facade.deleteTraineeById(1L);
        verify(traineeService).deleteById(1L);
    }

    @Test
    void findAllTrainees_shouldReturnServiceResult() {
        Map<Long, Trainee> trainees = Map.of(1L, new Trainee());
        when(traineeService.findAll()).thenReturn(trainees);
        assertSame(trainees, facade.findAllTrainees());
    }

    @Test
    void createTrainer_shouldDelegateToService() {
        Trainer trainer = new Trainer();
        facade.createTrainer(trainer);
        verify(trainerService).save(trainer);
    }

    @Test
    void updateTrainer_shouldDelegateToService() {
        Trainer trainer = new Trainer();
        facade.updateTrainer(trainer);
        verify(trainerService).update(trainer);
    }

    @Test
    void findTrainerById_shouldReturnServiceResult() {
        Trainer trainer = new Trainer();
        when(trainerService.findById(2L)).thenReturn(trainer);
        assertSame(trainer, facade.findTrainerById(2L));
    }

    @Test
    void findAllTrainers_shouldReturnServiceResult() {
        Map<Long, Trainer> trainers = Map.of(1L, new Trainer());
        when(trainerService.findAll()).thenReturn(trainers);
        assertSame(trainers, facade.findAllTrainers());
    }

    @Test
    void addTraining_shouldDelegateToService() {
        Training training = new Training();
        facade.addTraining(training);
        verify(trainingService).save(training);
    }

    @Test
    void findTrainingById_shouldReturnServiceResult() {
        Training training = new Training();
        when(trainingService.findById(3L)).thenReturn(training);
        assertSame(training, facade.findTrainingById(3L));
    }

    @Test
    void findAllTrainings_shouldReturnServiceResult() {
        Map<Long, Training> trainings = Map.of(1L, new Training());
        when(trainingService.findAll()).thenReturn(trainings);
        assertSame(trainings, facade.findAllTrainings());
    }
}

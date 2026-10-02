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

    private Map<String, Map<Long, Object>> storage = new ConcurrentHashMap<>();

    @Autowired
    public void setStorage(@Qualifier("storageMap") Map<String, Map<Long, Object>> storage) {
        this.storage = storage;
        this.storage.putIfAbsent("Trainee", new ConcurrentHashMap<>());
        this.storage.putIfAbsent("Trainer", new ConcurrentHashMap<>());
        this.storage.putIfAbsent("Training", new ConcurrentHashMap<>());
    }


    private final Logger logger = Logger.getLogger(Storage.class.getName());


    public Storage() {
        if (storage == null) {
            storage = new ConcurrentHashMap<>();
        }
        storage.putIfAbsent("Trainee", new ConcurrentHashMap<>());
        storage.putIfAbsent("Trainer", new ConcurrentHashMap<>());
        storage.putIfAbsent("Training", new ConcurrentHashMap<>());
    }


    public void save(String key, Long id, Object value) {
        logger.info("Saving " + key + " with ID: " + id);
        Map<Long, Object> entityMap = storage.computeIfAbsent(key, ignored -> new ConcurrentHashMap<>());
        entityMap.put(id, value);
        logger.info("Saved " + key + " with ID: " + id);
    }

    public void delete(String key, Long id) {
        logger.info("Deleting " + key + " with ID: " + id);
        Map<Long, Object> entityMap = storage.computeIfAbsent(key, ignored -> new ConcurrentHashMap<>());
        entityMap.remove(id);
        logger.info("Deleted " + key + " with ID: " + id);
    }

    public void update(String key, Long id, Object newValue) {
        logger.info("Updating " + key + " with ID: " + id);
        Map<Long, Object> entityMap = storage.computeIfAbsent(key, ignored -> new ConcurrentHashMap<>());
        entityMap.replace(id, newValue);
        logger.info("Updated " + key + " with ID: " + id);
    }

    public Object findById(String key, Long id) {
        Map<Long, Object> entityMap = storage.get(key);
        return entityMap == null ? null : entityMap.get(id);
    }


    @SuppressWarnings("unchecked")
    public Map<Long, Trainee> getTrainees() {
        return (Map<Long, Trainee>) (Map<?, ?>) storage.get("Trainee");
    }
    @SuppressWarnings("unchecked")
    public Map<Long, Trainer> getTrainers() {
        return (Map<Long, Trainer>) (Map<?, ?>) storage.get("Trainer");
    }
    @SuppressWarnings("unchecked")
    public Map<Long, Training> getTrainings() {
        return (Map<Long, Training>) (Map<?, ?>) storage.get("Training");
    }


    @PostConstruct
    public void init() {

        logger.info("Initializing storage with sample data...");
        // 1. Types
        TrainingType fitness = new TrainingType("Fitness");
        TrainingType yoga = new TrainingType("Yoga");

        // 2. Trainers
        Trainer trainer1 = new Trainer();
        trainer1.setUserId(1L);
        trainer1.setFirstName("Alex");
        trainer1.setLastName("Stone");
        trainer1.setUsername("Alex.Stone");
        trainer1.setPassword("aB9#kL2mP8");
        trainer1.setActive(true);
        trainer1.setSpecialization(fitness);
        save("Trainer", 1L, trainer1);
        logger.info("Trainer saved.");

        // 3. Trainees
        Trainee trainee1 = new Trainee();
        trainee1.setUserId(101L);
        trainee1.setFirstName("John");
        trainee1.setLastName("Doe");
        trainee1.setUsername("John.Doe");
        trainee1.setPassword("xY8!mP4kL1");
        trainee1.setActive(true);
        trainee1.setDateOfBirth(LocalDate.of(1995, 4, 12));
        trainee1.setAddress("Rustaveli Ave 15");
        save("Trainee", 101L, trainee1);

        logger.info("Trainee saved.");
        // 4. Trainings
        Training training1 = new Training();
        training1.setTraineeId(101L);
        training1.setTrainerId(1L);
        training1.setTrainingName("Morning Cardio Kick");
        training1.setTrainingType(fitness);
        training1.setTrainingDate(LocalDate.of(2026, 9, 25));
        training1.setTrainingDuration(Duration.ofMinutes(60));
        save("Training", 1001L, training1);
        logger.info("Training saved.");

        logger.info("Storage initialized with sample data.");
    }


}

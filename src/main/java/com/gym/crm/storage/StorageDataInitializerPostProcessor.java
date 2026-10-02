package com.gym.crm.storage;

import com.gym.crm.domain.Trainee;
import com.gym.crm.domain.Trainer;
import com.gym.crm.domain.Training;
import com.gym.crm.domain.TrainingType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.util.logging.Logger;

@Component
public class StorageDataInitializerPostProcessor implements BeanPostProcessor {

    private final Logger logger = Logger.getLogger(StorageDataInitializerPostProcessor.class.getName());

    @Value("${storage.data.file.path}")
    private String dataFilePath;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Storage storage) {
            logger.info("Initializing Storage bean from CSV: " + dataFilePath);
            loadDataFromCsv(storage);
        }
        return bean;
    }

    private void loadDataFromCsv(Storage storage) {
        String path = (dataFilePath != null && dataFilePath.startsWith("classpath:"))
                ? dataFilePath.substring("classpath:".length())
                : dataFilePath;

        Resource resource = new ClassPathResource(path);

        if (!resource.exists()) {
            throw new IllegalStateException("CSV file not found at classpath: " + path);
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] tokens = line.split(",", -1);
                String type = tokens[0].trim().toUpperCase();

                switch (type) {
                    case "TRAINER" -> parseTrainer(tokens, storage);
                    case "TRAINEE" -> parseTrainee(tokens, storage);
                    case "TRAINING" -> parseTraining(tokens, storage);
                    default -> logger.warning("Unknown entity type in CSV: " + type);
                }
            }

            logger.info("Successfully loaded CSV data into Storage.");

        } catch (Exception e) {
            throw new RuntimeException("Failed to read CSV data file: " + path, e);
        }
    }

    private void parseTrainer(String[] tokens, Storage storage) {
        Long id = Long.parseLong(tokens[1].trim());
        Trainer trainer = new Trainer();
        trainer.setUserId(id);
        trainer.setFirstName(tokens[2].trim());
        trainer.setLastName(tokens[3].trim());
        trainer.setUsername(tokens[4].trim());
        trainer.setPassword(tokens[5].trim());
        trainer.setActive(Boolean.parseBoolean(tokens[6].trim()));
        trainer.setSpecialization(new TrainingType(tokens[7].trim()));

        storage.getTrainers().put(id, trainer);
    }

    private void parseTrainee(String[] tokens, Storage storage) {
        Long id = Long.parseLong(tokens[1].trim());
        Trainee trainee = new Trainee();
        trainee.setUserId(id);
        trainee.setFirstName(tokens[2].trim());
        trainee.setLastName(tokens[3].trim());
        trainee.setUsername(tokens[4].trim());
        trainee.setPassword(tokens[5].trim());
        trainee.setActive(Boolean.parseBoolean(tokens[6].trim()));
        trainee.setDateOfBirth(LocalDate.parse(tokens[7].trim()));
        trainee.setAddress(tokens[8].trim());

        storage.getTrainees().put(id, trainee);
    }

    private void parseTraining(String[] tokens, Storage storage) {
        Long trainingId = Long.parseLong(tokens[1].trim());
        Training training = new Training();
        training.setTrainingName(tokens[2].trim());
        training.setTrainingType(new TrainingType(tokens[3].trim()));
        training.setTraineeId(Long.parseLong(tokens[4].trim()));
        training.setTrainerId(Long.parseLong(tokens[5].trim()));
        training.setTrainingDate(LocalDate.parse(tokens[6].trim()));
        training.setTrainingDuration(Duration.ofMinutes(Long.parseLong(tokens[7].trim())));

        storage.getTrainings().put(trainingId, training);
    }
}
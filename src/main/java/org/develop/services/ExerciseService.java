package org.develop.services;

import org.develop.dto.ExerciseDTO;
import org.develop.exceptions.DifficultyLevelNotFoundException;
import org.develop.exceptions.ExerciseAutoLengthOutOfBoundException;
import org.develop.exceptions.ExerciseNotFoundException;
import org.develop.model.DifficultyLevelModel;
import org.develop.model.ExerciseModel;
import org.develop.repository.DifficultyLevelRepo;
import org.develop.repository.ExerciseRepo;
import org.develop.util.KeyboardArea;
import org.develop.util.RandomTextGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    private final ExerciseRepo exerciseRepo;
    private final DifficultyLevelRepo difficultyLevelRepo;

    @Autowired
    public ExerciseService(ExerciseRepo exerciseRepo, DifficultyLevelRepo difficultyLevelRepo) {
        this.exerciseRepo = exerciseRepo;
        this.difficultyLevelRepo = difficultyLevelRepo;
    }

    public ExerciseModel getExerciseById(Long id) throws ExerciseNotFoundException {
        Optional<ExerciseModel> model = exerciseRepo.findById(id);
        if (model.isPresent()) {
            return model.get();
        } else throw new ExerciseNotFoundException("Exercise with that ID not found");
    }

    public String autoGenerateExerciseText(ExerciseDTO entity) throws DifficultyLevelNotFoundException, ExerciseAutoLengthOutOfBoundException {
        Optional<DifficultyLevelModel> diffModel = difficultyLevelRepo.findByName(entity.getDifficultyLevelName());
        DifficultyLevelModel difficultyLevel;
        if (diffModel.isPresent()) {
            difficultyLevel = diffModel.get();
        } else throw new DifficultyLevelNotFoundException("Difficulty with that name not found");
        if (entity.getAutoLength() > difficultyLevel.getMax_len() || entity.getAutoLength() < difficultyLevel.getMin_len()) {
            throw new ExerciseAutoLengthOutOfBoundException("Exercise auto length out of bounds");
        }
        List<String> allowedSymbols = new ArrayList<>();
        List<KeyboardArea> zones = difficultyLevel.getZones();
        for (int i = 0; i < difficultyLevel.getZones().size(); i++) {
            allowedSymbols.addAll(zones.get(i).getSymbols());
        }
        RandomTextGenerator text = new RandomTextGenerator(entity.getAutoLength(), allowedSymbols.toArray(new String[0]));
        return text.generateRandomText();
    }

    public void createExercise(ExerciseDTO entity) throws DifficultyLevelNotFoundException, ExerciseAutoLengthOutOfBoundException {
        ExerciseModel model = new ExerciseModel();
        DifficultyLevelModel diffModel = null;
        Optional<DifficultyLevelModel> difficultyLevel = difficultyLevelRepo.findByName(entity.getDifficultyLevelName());
        if (difficultyLevel.isPresent()) {
            diffModel = difficultyLevel.get();
        }
        model.setExerciseName(entity.getExerciseName());
        model.setDifficultyLevelName(diffModel);
        if (entity.getAutoLength() > 0) {
            model.setExerciseText(autoGenerateExerciseText(entity));
        } else model.setExerciseText(entity.getExerciseText());
        int err = (int) Math.round(model.getExerciseText().replaceAll(" ", "").length() * model.getDifficultyLevelName().getMax_errors());
        int do_time = (int) Math.round(model.getExerciseText().length() * model.getDifficultyLevelName().getToggle_time());
        model.setDoTime(do_time);
        model.setErrors(err);
        exerciseRepo.save(model);
    }

    public void deleteExercise(Long id) throws ExerciseNotFoundException {
        Optional<ExerciseModel> model = exerciseRepo.findById(id);
        if (model.isPresent()) {
            exerciseRepo.delete(model.get());
        } else throw new ExerciseNotFoundException("Exercise with that ID not found");
    }

    public List<ExerciseModel> getAllExercises() {
        var toReturn = exerciseRepo.findAll();
        return toReturn;
    }
}

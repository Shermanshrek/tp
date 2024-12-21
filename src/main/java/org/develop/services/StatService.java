package org.develop.services;

import org.develop.dto.StatDTO;
import org.develop.model.ExerciseModel;
import org.develop.model.StatModel;
import org.develop.repository.ExerciseRepo;
import org.develop.repository.StatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatService {
    private final StatRepo statRepository;
    private final ExerciseRepo exerciseRepo;

    @Autowired
    public StatService(StatRepo statRepo, ExerciseRepo exerciseRepo) {
        this.statRepository = statRepo;
        this.exerciseRepo = exerciseRepo;
    }

    public void saveStat(StatDTO dto){
        System.out.println(dto);
        StatModel model = new StatModel();
        // Определяем формат даты
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        // Преобразуем строку в LocalDate
        LocalDate localDate = LocalDate.parse(dto.getDate(), formatter);
        model.setExerciseDate(LocalDateTime.parse(dto.getDate()));
        model.setUsername(dto.getUsername());
        model.setErrorCount(dto.getErrors());
        model.setDurationInSeconds(dto.getDoTime());
        statRepository.save(model);
    }

    public List<ExerciseModel> getStats(String username){
        List<StatModel> stats = statRepository.findByUsername(username);
        List<Long> exerciseIds = stats.stream().
                map(StatModel::getExerciseId).
                distinct().
                collect(Collectors.toList());
        return exerciseRepo.findAllById(exerciseIds);
    }
}

package org.develop.services;

import org.develop.dto.StatDTO;
import org.develop.model.ExerciseModel;
import org.develop.model.StatModel;
import org.develop.repository.ExerciseRepo;
import org.develop.repository.StatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatService {
    private final StatRepo statRepository;
    private final ExerciseRepo exerciseRepo;
    private final StatRepo statRepo;

    @Autowired
    public StatService(StatRepo statRepo, ExerciseRepo exerciseRepo) {
        this.statRepository = statRepo;
        this.exerciseRepo = exerciseRepo;
        this.statRepo = statRepo;
    }

    public void saveStat(StatDTO dto){
        StatModel model = new StatModel();
        model.setExerciseDate(new Date());
        model.setUsername(dto.getUsername());
        model.setErrorCount(dto.getErrors());
        model.setMeanTime(dto.getMeanTime());
        model.setDurationInSeconds(dto.getDoTime());
        model.setExerciseId(dto.getExerciseId());
        model.setExerciseName(dto.getExerciseName());
        statRepository.save(model);
        System.out.println();
    }

    public List<ExerciseModel> getStats(String username){
        List<StatModel> stats = statRepository.findByUsername(username);
        List<Long> exerciseIds = stats.stream().
                map(StatModel::getExerciseId).
                distinct().
                collect(Collectors.toList());
        return exerciseRepo.findAllById(exerciseIds);
    }

    public List<StatModel> getStatsForAdmin(String username){
        return statRepository.findByUsername(username);
    }

    public List<StatModel> getStatForExercise(String username, Long id){
       Optional<List<StatModel>> stat = statRepository.findByUsernameAndExerciseId(username, id);
       return stat.orElseThrow(()-> new RuntimeException("Stat not found"));
    }

    public List<StatModel> getStatByUsername(String username) {
        return  statRepo.findByUsername(username);
    }

    public List<StatModel> getAllStat() {
        return statRepo.findAll();
    }
}

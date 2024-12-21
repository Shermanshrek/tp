package org.develop.services;

import org.develop.dto.StatDTO;
import org.develop.model.ExerciseModel;
import org.develop.model.StatModel;
import org.develop.model.UserModel;
import org.develop.repository.ExerciseRepo;
import org.develop.repository.StatRepo;
import org.develop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatService {
    private final StatRepo statRepository;
    private final UserRepository userRepository;
    private final ExerciseRepo exerciseRepo;

    @Autowired
    public StatService(StatRepo statRepo, UserRepository userRepository, ExerciseRepo exerciseRepo) {
        this.statRepository = statRepo;
        this.userRepository = userRepository;
        this.exerciseRepo = exerciseRepo;
    }

    public void saveStat(StatDTO dto){
        StatModel model = new StatModel();
        model.setUsername(dto.getUsername());
        model.setExercise_id(dto.getExercise_id());
        model.setErrors(dto.getErrors());
        model.setDateTime(dto.getDate());
        statRepository.save(model);
    }

    public List<ExerciseModel> getStats(String username){
        List<StatModel> stats = statRepository.findByUsername(username);
        List<Long> exerciseIds = stats.stream().
                map(StatModel::getExercise_id).
                distinct().
                collect(Collectors.toList());
        return exerciseRepo.findAllById(exerciseIds);
    }
}

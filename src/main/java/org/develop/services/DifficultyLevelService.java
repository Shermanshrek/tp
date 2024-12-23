package org.develop.services;

import org.develop.dto.DifficultyLevelDTO;
import org.develop.exceptions.DifficultyLevelAlreadyExistsException;
import org.develop.exceptions.DifficultyLevelNotFoundException;
import org.develop.model.DifficultyLevelModel;
import org.develop.util.KeyboardArea;
import org.develop.repository.DifficultyLevelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DifficultyLevelService {

    private final DifficultyLevelRepo difficultyLevelRepo;
    private final KeyboardArea zone1 = new KeyboardArea();
    private final KeyboardArea zone2 = new KeyboardArea();
    private final KeyboardArea zone3 = new KeyboardArea();
    private final KeyboardArea zone4 = new KeyboardArea();
    private final KeyboardArea zone5 = new KeyboardArea();
    private ArrayList<KeyboardArea> _keyboardAreas = new ArrayList<>();

    @Autowired
    public DifficultyLevelService(DifficultyLevelRepo diffLevelRepo) {
        this.difficultyLevelRepo = diffLevelRepo;
    }

    public DifficultyLevelModel getDifficultyLevelByName(String name) throws DifficultyLevelNotFoundException {
        Optional<DifficultyLevelModel> model = difficultyLevelRepo.findByName(name);
        if (model.isPresent()) {
            return model.get();
        } else throw new DifficultyLevelNotFoundException("Difficulty level with than name not found");
    }

    public void createDifficultyLevel(DifficultyLevelDTO entity) throws DifficultyLevelAlreadyExistsException {
        Optional<DifficultyLevelModel> model = difficultyLevelRepo.findByName(entity.getName());
        if (model.isPresent()) {
            throw new DifficultyLevelAlreadyExistsException("Difficulty level already exists");
        }
        DifficultyLevelModel _model = new DifficultyLevelModel();
        _model.setName(entity.getName());
        _model.setMin_len(entity.getMin_len());
        _model.setMax_len(entity.getMax_len());
        _model.setToggle_time(entity.getToggle_time());
        _model.setMax_errors(entity.getMax_errors() / 100); // делим на 100 так как передаём теперь ПРОЦЕНТЫ КАК ОНА ХОЧЕТ
        List<KeyboardArea> keyboardAreas = new ArrayList<>();
        for (int i = 0; i < entity.getZones().size(); i++) {
            if (entity.getZones().get(i)) {
                keyboardAreas.add(_keyboardAreas.get(i));
            }
        }
        _model.setZones(keyboardAreas);
        difficultyLevelRepo.save(_model);
    }
    public List<DifficultyLevelModel> getAllDifficultyLevels(){
        return difficultyLevelRepo.findAll();
    }

    public void keyboardAreasInit() {
        _keyboardAreas = new ArrayList<>();
        _keyboardAreas.add(zone1);
        _keyboardAreas.add(zone2);
        _keyboardAreas.add(zone3);
        _keyboardAreas.add(zone4);
        _keyboardAreas.add(zone5);
        for (int i = 0; i < 5; i++) {
            _keyboardAreas.get(i).setZone_number(i + 1);
        }
        zone1.setSymbols(List.of("ё", "1", "2", "й", "ф", "я", "0", "-", "+", "х", "ъ", "з", "ж",
                "э", "."));
        zone2.setSymbols(List.of("3", "ц", "ы", "ч", "9", "щ", "д", "ю"));
        zone3.setSymbols(List.of("4", "у", "в", "с", "8", "ш", "л", "б"));
        zone4.setSymbols(List.of("5", "6", "к", "е", "а", "п", "м", "и", "7", "н", "г", "р", "о", "т", "ь"));
        zone5.setSymbols(List.of(" "));
    }
}

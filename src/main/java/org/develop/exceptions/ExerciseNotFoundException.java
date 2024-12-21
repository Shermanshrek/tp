package org.develop.exceptions;

import org.develop.model.ExerciseModel;

public class ExerciseNotFoundException extends Exception {
    public ExerciseNotFoundException(String message) {
        super(message);
    }
}

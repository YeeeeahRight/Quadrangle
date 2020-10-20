package com.epam.quadrangle.data.validator.impl;

import com.epam.quadrangle.data.validator.Validator;

import java.util.List;
import java.util.regex.Pattern;

public class PointValidator implements Validator<List<String>> {
    private static final String VALIDATOR_PATTERN = "^[-+]?(0|([1-9][0-9]*))(\\.\\d+)?$";
    private static final int POINT_COORDINATES_AMOUNT = 2;
    private static final int X_COORDINATE = 0;
    private static final int Y_COORDINATE = 1;

    public boolean isValid(List<String> coordinatesLines) {
        if (coordinatesLines.size() != POINT_COORDINATES_AMOUNT) {
            return false;
        }
        boolean isCorrectFirstCoordinate = Pattern.matches(VALIDATOR_PATTERN, coordinatesLines.get(X_COORDINATE));
        boolean isCorrectSecondCoordinate = Pattern.matches(VALIDATOR_PATTERN, coordinatesLines.get(Y_COORDINATE));
        return isCorrectFirstCoordinate && isCorrectSecondCoordinate;
    }
}

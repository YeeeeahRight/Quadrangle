package com.epam.quadrangle.data.validator.impl;

import com.epam.quadrangle.data.validator.Validator;
import com.epam.quadrangle.entity.Point;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuadrangleValidator implements Validator<List<Point>> {
    private static final int AMOUNT_QUADRANGLE_POINTS = 4;

    //validating on same points
    public boolean isValid(List<Point> pointsList) {
        Set<Point> pointsSet = new HashSet<>(pointsList);
        return pointsSet.size() == AMOUNT_QUADRANGLE_POINTS;
    }
}

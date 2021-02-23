package com.epam.quadrangle.logic;

import com.epam.quadrangle.entity.quadrangle.Quadrangle;
import com.epam.quadrangle.entity.quadrangle.QuadrangleParameters;
import com.epam.quadrangle.logic.calculator.angles.impl.AnglesCalculator;
import com.epam.quadrangle.logic.calculator.points.impl.PointsCalculator;
import com.epam.quadrangle.logic.calculator.quadrangle.QuadrangleCalculable;
import com.epam.quadrangle.logic.calculator.quadrangle.impl.QuadrangleCalculator;
import com.epam.quadrangle.logic.calculator.vector.impl.VectorCalculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class QuadrangleRecorder implements Observer<Quadrangle> {
    private final Map<Integer, QuadrangleParameters> quadrangleParameters = new HashMap<>();
    private final QuadrangleCalculable quadrangleCalculator =
            new QuadrangleCalculator(new PointsCalculator(), new AnglesCalculator(new VectorCalculator()));
    private static final QuadrangleRecorder INSTANCE = new QuadrangleRecorder();

    private QuadrangleRecorder() {
    }

    public static QuadrangleRecorder getInstance() {
        return INSTANCE;
    }

    public Optional<Double> getArea(int id) {
        if (!quadrangleParameters.containsKey(id)) {
            return Optional.empty();
        }
        QuadrangleParameters parameters = quadrangleParameters.get(id);
        Double area = parameters.getArea();
        return Optional.of(area);
    }

    public Optional<Double> getPerimeter(int id) {
        if (!quadrangleParameters.containsKey(id)) {
            return Optional.empty();
        }
        QuadrangleParameters parameters = quadrangleParameters.get(id);
        double perimeter = parameters.getPerimeter();
        return Optional.of(perimeter);
    }

    @Override
    public void update(Quadrangle observable) {
        double area = quadrangleCalculator.calculateArea(observable);
        double perimeter = quadrangleCalculator.calculatePerimeter(observable);
        QuadrangleParameters newParameters = new QuadrangleParameters(area, perimeter);
        Integer id = observable.getId();
        quadrangleParameters.put(id, newParameters);
    }

}

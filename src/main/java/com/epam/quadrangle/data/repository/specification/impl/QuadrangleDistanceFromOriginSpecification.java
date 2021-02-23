package com.epam.quadrangle.data.repository.specification.impl;

import com.epam.quadrangle.data.repository.specification.Specification;
import com.epam.quadrangle.entity.MathVector;
import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.entity.quadrangle.Quadrangle;
import com.epam.quadrangle.logic.calculator.vector.VectorCalculable;

public class QuadrangleDistanceFromOriginSpecification implements Specification<Quadrangle> {
    private static final int FIRST_POINT_INDEX = 0;
    private static final int SECOND_POINT_INDEX = 1;
    private static final int THIRD_POINT_INDEX = 2;
    private static final int FOURTH_POINT_INDEX = 3;
    private static final Point origin = new Point(0,0 );
    private final double maxDistance;
    private final VectorCalculable vectorCalculator;

    public QuadrangleDistanceFromOriginSpecification(double maxDistance, VectorCalculable vectorCalculator) {
        this.maxDistance = maxDistance;
        this.vectorCalculator = vectorCalculator;
    }

    @Override
    public boolean specified(Quadrangle quadrangle) {
        MathVector firstVector = new MathVector(quadrangle.getPoint(FIRST_POINT_INDEX), origin);
        MathVector secondVector = new MathVector(quadrangle.getPoint(SECOND_POINT_INDEX), origin);
        MathVector thirdVector = new MathVector(quadrangle.getPoint(THIRD_POINT_INDEX), origin);
        MathVector fourthVector = new MathVector(quadrangle.getPoint(FOURTH_POINT_INDEX), origin);
        double distanceFirstSide = vectorCalculator.calculateVectorLength(firstVector);
        double distanceSecondSide = vectorCalculator.calculateVectorLength(secondVector);
        double distanceThirdSide = vectorCalculator.calculateVectorLength(thirdVector);
        double distanceFourthSide = vectorCalculator.calculateVectorLength(fourthVector);

        return Double.compare(distanceFirstSide, maxDistance) <= 0 &&
                Double.compare(distanceSecondSide, maxDistance) <= 0 &&
                Double.compare(distanceThirdSide, maxDistance) <= 0 &&
                Double.compare(distanceFourthSide, maxDistance) <= 0;
    }
}

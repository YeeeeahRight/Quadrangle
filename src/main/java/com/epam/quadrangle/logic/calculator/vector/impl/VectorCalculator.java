package com.epam.quadrangle.logic.calculator.vector.impl;

import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.logic.calculator.vector.VectorCalculable;
import com.epam.quadrangle.entity.MathVector;

public class VectorCalculator implements VectorCalculable {
    @Override
    public double calculateAngle(MathVector firstVector, MathVector secondVector) {
        double scalarProduct = calculateScalarProduct(firstVector, secondVector);
        double firstVectorLength = calculateVectorLength(firstVector);
        double secondVectorLength = calculateVectorLength(secondVector);
        double cosAngle = scalarProduct / (firstVectorLength * secondVectorLength);
        double radians = Math.acos(cosAngle);
        return Math.toDegrees(radians);
    }

    @Override
    public double calculateScalarProduct(MathVector firstVector, MathVector secondVector) {
        double firstVectorPointOne = firstVector.getFirstPoint();
        double secondVectorPointOne = secondVector.getFirstPoint();
        double firstPointsProduct = firstVectorPointOne * secondVectorPointOne;
        double firstVectorPointTwo = firstVector.getSecondPoint();
        double secondVectorPointTwo = secondVector.getSecondPoint();
        double secondPointsProduct = firstVectorPointTwo * secondVectorPointTwo;
        return firstPointsProduct + secondPointsProduct;
    }

    @Override
    public double calculateVectorLength(MathVector vector) {
        double firstPoint = vector.getFirstPoint();
        double secondPoint = vector.getSecondPoint();
        return Math.sqrt(firstPoint * firstPoint + secondPoint * secondPoint);
    }
}

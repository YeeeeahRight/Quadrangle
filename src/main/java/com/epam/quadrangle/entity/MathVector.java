package com.epam.quadrangle.entity;

public class MathVector {
    private final double firstPoint;
    private final double secondPoint;

    public MathVector(Point firstPoint, Point secondPoint) {
        double firstX = firstPoint.getX();
        double secondX = secondPoint.getX();
        this.firstPoint =  secondX - firstX;
        double firstY = firstPoint.getY();
        double secondY = secondPoint.getY();
        this.secondPoint = secondY - firstY;
    }

    public double getFirstPoint() {
        return firstPoint;
    }

    public double getSecondPoint() {
        return secondPoint;
    }
}

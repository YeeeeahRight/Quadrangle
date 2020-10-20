package com.epam.quadrangle.logic.calculator.points.impl;

import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.logic.calculator.points.PointsCalculable;

public class PointsCalculator implements PointsCalculable {

    public double calculateDistancePoints(Point firstPoint, Point secondPoint) {
        double x1 = firstPoint.getX();
        double y1 = firstPoint.getY();
        double x2 = secondPoint.getX();
        double y2 = secondPoint.getY();
        double firstSideSquare = Math.pow(x2 - x1, 2);
        double secondSideSquare = Math.pow(y2 - y1, 2);
        return Math.sqrt(firstSideSquare + secondSideSquare);
    }

    @Override
    public boolean isCollinearPoints(Point firstPoint, Point secondPoint, Point thirdPoint) {
        double xFirst = firstPoint.getX();
        double yFirst = firstPoint.getY();
        double xSecond = secondPoint.getX();
        double ySecond = secondPoint.getY();
        double xThird = thirdPoint.getX();
        double yThird = thirdPoint.getY();
        double square = (((xFirst * ySecond) + (xSecond * yThird) + (xThird * yFirst)) -
                ((xSecond * yFirst) + (xThird * ySecond) + (xFirst * yThird))) / 2;
        return square == 0;
    }
}

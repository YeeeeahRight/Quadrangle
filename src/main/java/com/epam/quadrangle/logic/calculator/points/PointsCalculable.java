package com.epam.quadrangle.logic.calculator.points;

import com.epam.quadrangle.entity.Point;

public interface PointsCalculable {
    double calculateDistancePoints(Point firstPoint, Point secondPoint);
    boolean isCollinearPoints(Point firstPoint, Point secondPoint, Point thirdPoint);
}

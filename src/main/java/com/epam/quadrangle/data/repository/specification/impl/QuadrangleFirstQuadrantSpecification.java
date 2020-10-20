package com.epam.quadrangle.data.repository.specification.impl;

import com.epam.quadrangle.data.repository.specification.Specification;
import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.entity.quadrangle.Quadrangle;

public class QuadrangleFirstQuadrantSpecification implements Specification<Quadrangle> {
    private static final int FIRST_POINT_INDEX = 0;
    private static final int SECOND_POINT_INDEX = 1;
    private static final int THIRD_POINT_INDEX = 2;
    private static final int FOURTH_POINT_INDEX = 3;

    @Override
    public boolean specified(Quadrangle quadrangle) {
        Point firstPoint = quadrangle.getPoint(FIRST_POINT_INDEX);
        Point secondPoint = quadrangle.getPoint(SECOND_POINT_INDEX);
        Point thirdPoint = quadrangle.getPoint(THIRD_POINT_INDEX);
        Point fourthPoint = quadrangle.getPoint(FOURTH_POINT_INDEX);

        return isPointFirstQuadrant(firstPoint) &&
                isPointFirstQuadrant(secondPoint) &&
                isPointFirstQuadrant(thirdPoint) &&
                isPointFirstQuadrant(fourthPoint);
    }

    private boolean isPointFirstQuadrant(Point point) {
        double x = point.getX();
        double y = point.getY();

        return Double.compare(x, 0) > 0 &&
                Double.compare(y, 0) > 0;
    }
}

package com.epam.quadrangle.logic.calculator.quadrangle.impl;

import com.epam.quadrangle.data.director.QuadrangleDirector;
import com.epam.quadrangle.logic.calculator.angles.AnglesCalculable;
import com.epam.quadrangle.logic.calculator.quadrangle.QuadrangleCalculable;
import com.epam.quadrangle.logic.enums.QuadrangleType;
import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.entity.quadrangle.Quadrangle;
import com.epam.quadrangle.logic.calculator.points.PointsCalculable;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class QuadrangleCalculator implements QuadrangleCalculable {
    private static final Logger LOGGER = LogManager.getLogger(QuadrangleCalculator.class);
    private static final int AMOUNT_QUADRANGLE_POINTS = 4;
    private static final int FIRST_ITEM_INDEX = 0;
    private static final int SECOND_ITEM_INDEX = 1;
    private static final int THIRD_ITEM_INDEX = 2;
    private static final int FOURTH_ITEM_INDEX = 3;
    private static final int FIRST_OPPOSITE_ANGLE_INDEX = 0;
    private static final int SECOND_OPPOSITE_ANGLE_INDEX = 1;
    private final PointsCalculable pointsCalculator;
    private final AnglesCalculable anglesCalculator;

    public QuadrangleCalculator(PointsCalculable pointsCalculator, AnglesCalculable anglesCalculator) {
        this.pointsCalculator = pointsCalculator;
        this.anglesCalculator = anglesCalculator;
    }

    @Override
    public double calculatePerimeter(Quadrangle quadrangle) {
        LOGGER.info("calculating perimeter of " + quadrangle);
        double perimeter = 0;
        for (int i = 0; i < AMOUNT_QUADRANGLE_POINTS; i++) {
            Point firstPoint = quadrangle.getPoint(i);
            Point secondPoint = quadrangle.getPoint(i == AMOUNT_QUADRANGLE_POINTS - 1 ? 0 : i + 1);
            perimeter += pointsCalculator.calculateDistancePoints(firstPoint, secondPoint);
        }
        LOGGER.info("perimeter: " + perimeter);
        return perimeter;
    }

    @Override
    public double calculateArea(Quadrangle quadrangle) {
        LOGGER.info("calculating area of " + quadrangle);
        Point firstPoint = quadrangle.getPoint(FIRST_ITEM_INDEX);
        Point secondPoint = quadrangle.getPoint(SECOND_ITEM_INDEX);
        Point thirdPoint = quadrangle.getPoint(THIRD_ITEM_INDEX);
        Point fourthPoint = quadrangle.getPoint(FOURTH_ITEM_INDEX);
        List<Double> angles = anglesCalculator.calculateAngles(quadrangle);
        double firstAngle = angles.get(FIRST_OPPOSITE_ANGLE_INDEX);
        double secondAngle = angles.get(SECOND_OPPOSITE_ANGLE_INDEX);
        double sumAnglesDegrees = Math.toRadians(firstAngle + secondAngle);
        double semiPerimeter = calculatePerimeter(quadrangle) / 2;
        double firstSide = pointsCalculator.calculateDistancePoints(firstPoint, secondPoint);
        double secondSide = pointsCalculator.calculateDistancePoints(secondPoint, thirdPoint);
        double thirdSide = pointsCalculator.calculateDistancePoints(thirdPoint, fourthPoint);
        double fourthSide = pointsCalculator.calculateDistancePoints(fourthPoint, firstPoint);
        double firstPartFormula = (semiPerimeter - firstSide) * (semiPerimeter - secondSide) *
                (semiPerimeter - thirdSide) * (semiPerimeter - fourthSide);
        double secondPathFormula = firstSide * secondSide * thirdSide * fourthSide * Math.pow
                (Math.cos(sumAnglesDegrees / 2), 2);
        double result = Math.sqrt(firstPartFormula - secondPathFormula);
        LOGGER.info("area: " + result);
        return result;
    }

    @Override
    public boolean isQuadrangle(Quadrangle quadrangle) {
        LOGGER.info("find if " + quadrangle + "is really quadrangle");
        Point firstPoint;
        Point secondPoint;
        Point thirdPoint;
        for (int i = 0; i < AMOUNT_QUADRANGLE_POINTS; i++) {
            firstPoint = quadrangle.getPoint(i % AMOUNT_QUADRANGLE_POINTS);
            secondPoint = quadrangle.getPoint((i + 1) % AMOUNT_QUADRANGLE_POINTS);
            thirdPoint = quadrangle.getPoint((i + 2) % AMOUNT_QUADRANGLE_POINTS);
            if (pointsCalculator.isCollinearPoints(firstPoint, secondPoint, thirdPoint)) {
                LOGGER.info("Answer: NO");
                return false;
            }
        }
        LOGGER.info("Answer: YES");
        return true;
    }

    @Override
    public QuadrangleType findQuadrangleType(Quadrangle quadrangle) {
        LOGGER.info("Finding type of " + quadrangle);
        List<Double> angles = anglesCalculator.calculateAngles(quadrangle);
        double firstAngle = angles.get(FIRST_ITEM_INDEX);
        double secondAngle = angles.get(SECOND_ITEM_INDEX);
        double thirdAngle = angles.get(THIRD_ITEM_INDEX);
        double fourthAngle = angles.get(FOURTH_ITEM_INDEX);
        boolean isFirstAnglesParallel = Double.compare(firstAngle, secondAngle) == 0 ||
                Double.compare(firstAngle, thirdAngle) == 0;
        boolean isSecondAnglesParallel = Double.compare(secondAngle, thirdAngle) == 0 ||
                Double.compare(secondAngle, fourthAngle) == 0;
        boolean isParallelSides = isFirstAnglesParallel && isSecondAnglesParallel;
        if (isParallelSides) {
            boolean isEqualsSides = isEqualsSides(quadrangle);
            boolean isAnglesRight = anglesCalculator.isAnglesRight(angles);
            if (isAnglesRight) {
                if (isEqualsSides) {
                    LOGGER.info("Answer: SQUARE");
                    return QuadrangleType.SQUARE;
                }
                LOGGER.info("Answer: RECTANGLE");
                return QuadrangleType.RECTANGLE;
            }
            if (isEqualsSides) {
                LOGGER.info("Answer: RHOMBUS");
                return QuadrangleType.RHOMBUS;
            }
            LOGGER.info("Answer: PARALLELOGRAM");
            return QuadrangleType.PARALLELOGRAM;
        }
        boolean isHalfParallelSides = isFirstAnglesParallel || isSecondAnglesParallel;
        if (isHalfParallelSides) {
            LOGGER.info("Answer: TRAPEZOID");
            return QuadrangleType.TRAPEZOID;
        }
        LOGGER.info("Answer: NO TYPE :(");
        return QuadrangleType.NO_TYPE;
    }

    private boolean isEqualsSides(Quadrangle quadrangle) {
        Point firstPoint;
        Point secondPoint;
        Point thirdPoint;
        for (int i = 0; i < AMOUNT_QUADRANGLE_POINTS; i++) {
            firstPoint = quadrangle.getPoint(i);
            secondPoint = quadrangle.getPoint((i + 1) % AMOUNT_QUADRANGLE_POINTS);
            thirdPoint = quadrangle.getPoint((i + 2) % AMOUNT_QUADRANGLE_POINTS);
            if (pointsCalculator.calculateDistancePoints(firstPoint, secondPoint) !=
                    pointsCalculator.calculateDistancePoints(secondPoint, thirdPoint)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isConvex(Quadrangle quadrangle) {
        LOGGER.info("finding if " + quadrangle + " is convex...");
        Point firstPoint = quadrangle.getPoint(FIRST_ITEM_INDEX);
        Point secondPoint = quadrangle.getPoint(SECOND_ITEM_INDEX);
        Point thirdPoint = quadrangle.getPoint(THIRD_ITEM_INDEX);
        Point fourthPoint = quadrangle.getPoint(FOURTH_ITEM_INDEX);
        boolean result = ((doubledAreaTriangle(firstPoint, secondPoint, thirdPoint) *
                doubledAreaTriangle(thirdPoint, fourthPoint, firstPoint)) > 0 &&
                (doubledAreaTriangle(secondPoint, thirdPoint, fourthPoint) *
                        doubledAreaTriangle(fourthPoint, firstPoint, secondPoint)) > 0);
        LOGGER.info("result : " + result);
        return result;
    }

    private double doubledAreaTriangle(Point firstPoint, Point secondPoint, Point thirdPoint) {
        double xFirst = firstPoint.getX();
        double yFirst = firstPoint.getY();
        double xSecond = secondPoint.getX();
        double ySecond = secondPoint.getY();
        double xThird = thirdPoint.getX();
        double yThird = thirdPoint.getY();
        return xFirst * (ySecond - yThird) + xSecond * (yThird - yFirst) + xThird * (yFirst - ySecond);
    }
}

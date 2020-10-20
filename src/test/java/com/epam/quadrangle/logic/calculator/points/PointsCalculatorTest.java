package com.epam.quadrangle.logic.calculator.points;

import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.logic.calculator.points.impl.PointsCalculator;
import org.junit.Assert;
import org.junit.Test;

public class PointsCalculatorTest {
    private static final Point POINT = new Point(2,2);
    private static final Point FIRST_COLLINEAR_POINT = new Point(0,0);
    private static final Point SECOND_COLLINEAR_POINT = new Point(0,2);
    private static final Point THIRD_COLLINEAR_POINT = new Point(0,6);
    private static final double FIRST_SECOND_POINT_DISTANCE = 2;
    private static final double DELTA = 0.0001;
    private final PointsCalculable pointsCalculator = new PointsCalculator();

    @Test
    public void testCalculateDistancePointsShouldReturnZeroWhenPointsAreEquals() {
        //given
        double distance;
        //when
        distance = pointsCalculator.calculateDistancePoints(POINT, POINT);
        //then
        Assert.assertEquals(0.0, distance, DELTA);
    }

    @Test
    public void testCalculateDistancePointsShouldReturnPositiveDistanceWhenPointsAreNotEquals() {
        //given
        double distance;
        //when
        distance = pointsCalculator.calculateDistancePoints(FIRST_COLLINEAR_POINT, SECOND_COLLINEAR_POINT);
        //then
        Assert.assertEquals(FIRST_SECOND_POINT_DISTANCE, distance, DELTA);
    }

    @Test
    public void testIsCollinearPointsShouldReturnTrueWhenPointsAreCollinear() {
        //given
        boolean isCollinear;
        //when
        isCollinear = pointsCalculator.isCollinearPoints(FIRST_COLLINEAR_POINT, SECOND_COLLINEAR_POINT,
                THIRD_COLLINEAR_POINT);
        //then
        Assert.assertTrue(isCollinear);
    }

    @Test
    public void testIsCollinearPointsShouldReturnFalseWhenPointsAreNotCollinear() {
        //given
        boolean isCollinear;
        //when
        isCollinear = pointsCalculator.isCollinearPoints(FIRST_COLLINEAR_POINT, SECOND_COLLINEAR_POINT,
                POINT);
        //then
        Assert.assertFalse(isCollinear);
    }
}

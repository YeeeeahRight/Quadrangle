package com.epam.quadrangle.logic.calculator.quadrangle;

import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.entity.quadrangle.Quadrangle;
import com.epam.quadrangle.logic.enums.QuadrangleType;
import com.epam.quadrangle.logic.calculator.angles.AnglesCalculable;
import com.epam.quadrangle.logic.calculator.angles.impl.AnglesCalculator;
import com.epam.quadrangle.logic.calculator.points.PointsCalculable;
import com.epam.quadrangle.logic.calculator.points.impl.PointsCalculator;
import com.epam.quadrangle.logic.calculator.quadrangle.impl.QuadrangleCalculator;
import com.epam.quadrangle.logic.calculator.vector.impl.VectorCalculator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class QuadrangleCalculatorTest {
    private static final double RIGHT_ANGLE = 90.0;
    private static final Point FIRST_SQUARE_POINT = new Point(0,0);
    private static final Point SECOND_SQUARE_POINT = new Point(0,2);
    private static final Point THIRD_SQUARE_POINT = new Point(2,2);
    private static final Point FOURTH_SQUARE_POINT = new Point(2,0);
    private static final Quadrangle SQUARE = new Quadrangle(Arrays.asList
            (FIRST_SQUARE_POINT, SECOND_SQUARE_POINT, THIRD_SQUARE_POINT, FOURTH_SQUARE_POINT), 0);
    private static final Point THIRD_RECTANGLE_POINT = new Point(4, 2);
    private static final Point FOURTH_RECTANGLE_POINT = new Point(4, 0);
    private static final Quadrangle RECTANGLE = new Quadrangle(Arrays.asList
            (FIRST_SQUARE_POINT, SECOND_SQUARE_POINT, THIRD_RECTANGLE_POINT, FOURTH_RECTANGLE_POINT), 0);
    private static final double RECTANGLE_SECOND_SIDE = 4;
    private static final Point INCORRECT_POINT = new Point(1, 1);
    private static final Quadrangle INCORRECT_QUADRANGLE = new Quadrangle(Arrays.asList
            (FIRST_SQUARE_POINT, SECOND_SQUARE_POINT, THIRD_SQUARE_POINT, INCORRECT_POINT), 0);
    private static final double SQUARE_SIDE = 2;
    private static final double SQUARE_AREA = 4;
    private static final double SQUARE_PERIMETER = 8;
    private static final double DELTA = 0.0001;

    @Test
    public void testCalculatePerimeterShouldCalculateWhenQuadrangleIsArea() {
        //given
        double perimeter;
        PointsCalculable pointsCalculator = Mockito.mock(PointsCalculable.class);
        when(pointsCalculator.calculateDistancePoints(any(Point.class), any(Point.class))).
                thenReturn(SQUARE_SIDE);
        AnglesCalculable anglesCalculator = new AnglesCalculator(new VectorCalculator());
        QuadrangleCalculable quadrangleCalculator = new QuadrangleCalculator(pointsCalculator, anglesCalculator);
        //when
        perimeter = quadrangleCalculator.calculatePerimeter(SQUARE);
        //then
        Assert.assertEquals(SQUARE_PERIMETER, perimeter, DELTA);
    }

    @Test
    public void testCalculateAreaShouldCalculateWhenQuadrangleIsArea() {
        //given
        double area;
        PointsCalculable pointsCalculator = Mockito.mock(PointsCalculable.class);
        when(pointsCalculator.calculateDistancePoints(any(Point.class), any(Point.class))).
                thenReturn(SQUARE_SIDE);
        AnglesCalculable anglesCalculator = Mockito.mock(AnglesCalculable.class);
        when(anglesCalculator.calculateAngles(any(Quadrangle.class))).
                thenReturn(Arrays.asList(RIGHT_ANGLE, RIGHT_ANGLE, RIGHT_ANGLE, RIGHT_ANGLE));
        QuadrangleCalculable quadrangleCalculator = new QuadrangleCalculator(pointsCalculator, anglesCalculator);
        //when
        area = quadrangleCalculator.calculateArea(SQUARE);
        //then
        Assert.assertEquals(SQUARE_AREA, area, DELTA);
    }

    @Test
    public void testIsQuadrangleShouldReturnTrueWhenQuadrangleIsCorrect() {
        //given
        boolean isQuadrangle;
        PointsCalculable pointsCalculator = Mockito.mock(PointsCalculable.class);
        when(pointsCalculator.isCollinearPoints(any(Point.class), any(Point.class), any(Point.class))).
                thenReturn(false);
        AnglesCalculable anglesCalculator = new AnglesCalculator(new VectorCalculator());
        QuadrangleCalculable quadrangleCalculator = new QuadrangleCalculator(pointsCalculator, anglesCalculator);
        //when
        isQuadrangle = quadrangleCalculator.isQuadrangle(SQUARE);
        //then
        Assert.assertTrue(isQuadrangle);
    }

    @Test
    public void testIsQuadrangleShouldReturnFalseWhenQuadrangleIsIncorrect() {
        //given
        boolean isQuadrangle;
        PointsCalculable pointsCalculator = Mockito.mock(PointsCalculable.class);
        when(pointsCalculator.isCollinearPoints(any(Point.class), any(Point.class), any(Point.class))).
                thenReturn(true);
        AnglesCalculable anglesCalculator = new AnglesCalculator(new VectorCalculator());
        QuadrangleCalculable quadrangleCalculator = new QuadrangleCalculator(pointsCalculator, anglesCalculator);
        //when
        isQuadrangle = quadrangleCalculator.isQuadrangle(INCORRECT_QUADRANGLE);
        //then
        Assert.assertFalse(isQuadrangle);
    }

    @Test
    public void testIsConvexShouldReturnTrueWhenQuadrangleIsConvex() {
        //given
        boolean isConvex;
        PointsCalculable pointsCalculator = new PointsCalculator();
        AnglesCalculable anglesCalculator = new AnglesCalculator(new VectorCalculator());
        QuadrangleCalculable quadrangleCalculator = new QuadrangleCalculator(pointsCalculator, anglesCalculator);
        //when
        isConvex = quadrangleCalculator.isConvex(SQUARE);
        //then
        Assert.assertTrue(isConvex);
    }

    @Test
    public void testIsConvexShouldReturnFalseWhenQuadrangleIsNotConvex() {
        //given
        boolean isConvex;
        PointsCalculable pointsCalculator = new PointsCalculator();
        AnglesCalculable anglesCalculator = new AnglesCalculator(new VectorCalculator());
        QuadrangleCalculable quadrangleCalculator = new QuadrangleCalculator(pointsCalculator, anglesCalculator);
        //when
        isConvex = quadrangleCalculator.isConvex(INCORRECT_QUADRANGLE);
        //then
        Assert.assertFalse(isConvex);
    }

    @Test
    public void testFindQuadrangleTypeShouldReturnSquareWhenQuadrangleIsSquare() {
        //given
        QuadrangleType type;
        PointsCalculable pointsCalculator = Mockito.mock(PointsCalculable.class);
        when(pointsCalculator.calculateDistancePoints(any(Point.class), any(Point.class)))
                .thenReturn(SQUARE_SIDE, SQUARE_SIDE, SQUARE_SIDE, SQUARE_SIDE);
        AnglesCalculable anglesCalculator = Mockito.mock(AnglesCalculable.class);
        when(anglesCalculator.calculateAngles(any(Quadrangle.class))).
                thenReturn(Arrays.asList(RIGHT_ANGLE, RIGHT_ANGLE, RIGHT_ANGLE, RIGHT_ANGLE));
        when(anglesCalculator.isAnglesRight(any(List.class))).thenReturn(true);
        QuadrangleCalculable quadrangleCalculator = new QuadrangleCalculator(pointsCalculator, anglesCalculator);
        //when
        type = quadrangleCalculator.findQuadrangleType(SQUARE);
        //then
        Assert.assertEquals(QuadrangleType.SQUARE, type);
    }

    @Test
    public void testFindQuadrangleTypeShouldReturnRectangleWhenQuadrangleIsRectangle() throws NoSuchElementException {
        QuadrangleType type;
        PointsCalculable pointsCalculator = Mockito.mock(PointsCalculable.class);
        when(pointsCalculator.calculateDistancePoints(any(Point.class), any(Point.class)))
                .thenReturn(SQUARE_SIDE, RECTANGLE_SECOND_SIDE, SQUARE_SIDE, RECTANGLE_SECOND_SIDE);
        AnglesCalculable anglesCalculator = Mockito.mock(AnglesCalculable.class);
        when(anglesCalculator.calculateAngles(any(Quadrangle.class))).
                thenReturn(Arrays.asList(RIGHT_ANGLE, RIGHT_ANGLE, RIGHT_ANGLE, RIGHT_ANGLE));
        when(anglesCalculator.isAnglesRight(any(List.class))).thenReturn(true);
        QuadrangleCalculable quadrangleCalculator = new QuadrangleCalculator(pointsCalculator, anglesCalculator);
        //when
        type = quadrangleCalculator.findQuadrangleType(RECTANGLE);
        //then
        Assert.assertEquals(QuadrangleType.RECTANGLE, type);
    }
}

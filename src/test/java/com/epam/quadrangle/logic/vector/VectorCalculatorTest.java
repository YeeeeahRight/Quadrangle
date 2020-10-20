package com.epam.quadrangle.logic.vector;

import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.logic.calculator.vector.VectorCalculable;
import com.epam.quadrangle.logic.calculator.vector.impl.VectorCalculator;
import com.epam.quadrangle.entity.MathVector;
import org.junit.Assert;
import org.junit.Test;

public class VectorCalculatorTest {
    private static final Point FIRST_POINT = new Point(0,0);
    private static final Point SECOND_POINT = new Point(0,2);
    private static final Point THIRD_POINT = new Point(2,2);
    private static final Point FOURTH_POINT = new Point(2,0);
    private static final MathVector FIRST_VECTOR = new MathVector(FIRST_POINT, SECOND_POINT);
    private static final MathVector SECOND_VECTOR = new MathVector(SECOND_POINT, THIRD_POINT);
    private static final MathVector THIRD_VECTOR = new MathVector(THIRD_POINT, FOURTH_POINT);
    private static final double FIRST_VECTOR_LENGTH = 2.0;
    private static final double FIRST_THIRD_VECTOR_PRODUCT = -4.0;
    private static final double RIGHT_ANGLE = 90.0;
    private static final double STRAIGHT_ANGLE = 180.0;
    private static final double DELTA = 0.0001;
    private final VectorCalculable vectorCalculator = new VectorCalculator();

    @Test
    public void testCalculateAngleShouldReturnRightAngleWhenVectorsArePerpendicular() {
        //given
        double angle;
        //when
        angle = vectorCalculator.calculateAngle(FIRST_VECTOR, SECOND_VECTOR);
        //then
        Assert.assertEquals(RIGHT_ANGLE, angle, DELTA);
    }

    @Test
    public void testCalculateAngleShouldReturnStraightAngleWhenVectorsAreParallel() {
        //given
        double angle;
        //when
        angle = vectorCalculator.calculateAngle(FIRST_VECTOR, THIRD_VECTOR);
        //then
        Assert.assertEquals(STRAIGHT_ANGLE, angle, DELTA);
    }


    @Test
    public void testCalculateAngleShouldReturnZeroWhenVectorsAreEquals() {
        //given
        double angle;
        //when
        angle = vectorCalculator.calculateAngle(FIRST_VECTOR, FIRST_VECTOR);
        //then
        Assert.assertEquals(0, angle, DELTA);
    }

    @Test
    public void testCalculateVectorLengthShouldCalculateWhenVectorIsAny() {
        //given
        double length;
        //when
        length = vectorCalculator.calculateVectorLength(FIRST_VECTOR);
        //then
        Assert.assertEquals(FIRST_VECTOR_LENGTH, length, DELTA);
    }

    @Test
    public void testCalculateScalarProductWhenVectorsAreEquals() {
        //given
        double scalarProduct;
        //when
        scalarProduct = vectorCalculator.calculateScalarProduct(FIRST_VECTOR, THIRD_VECTOR);
        //then
        Assert.assertEquals(FIRST_THIRD_VECTOR_PRODUCT, scalarProduct, DELTA);
    }
}

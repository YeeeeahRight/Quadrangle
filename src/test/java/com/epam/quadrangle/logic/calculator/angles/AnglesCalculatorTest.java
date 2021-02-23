package com.epam.quadrangle.logic.calculator.angles;

import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.entity.quadrangle.Quadrangle;
import com.epam.quadrangle.logic.calculator.angles.impl.AnglesCalculator;
import com.epam.quadrangle.logic.calculator.vector.VectorCalculable;
import com.epam.quadrangle.logic.calculator.vector.impl.VectorCalculator;
import com.epam.quadrangle.entity.MathVector;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class AnglesCalculatorTest {
    private static final double RIGHT_ANGLE = 90;
    private static final double NOT_RIGHT_ANGLE = 85;
    private static final Point FIRST_POINT = new Point(0,0);
    private static final Point SECOND_POINT = new Point(0,2);
    private static final Point THIRD_POINT = new Point(2,2);
    private static final Point FOURTH_POINT = new Point(2,0);
    private static final Quadrangle SQUARE = new Quadrangle(Arrays.asList
            (FIRST_POINT, SECOND_POINT, THIRD_POINT, FOURTH_POINT),0);

    @Test
    public void testCalculateAnglesShouldReturnRightAnglesWhenQuadrangleIsSquare() {
        //given
        VectorCalculable vectorCalculator = Mockito.mock(VectorCalculator.class);
        when(vectorCalculator.calculateAngle(any(MathVector.class), any(MathVector.class))).thenReturn(RIGHT_ANGLE);
        AnglesCalculable anglesCalculator = new AnglesCalculator(vectorCalculator);
        List<Double> angles;
        //when
        angles = anglesCalculator.calculateAngles(SQUARE);
        //then
        Assert.assertEquals(Arrays.asList(RIGHT_ANGLE, RIGHT_ANGLE, RIGHT_ANGLE, RIGHT_ANGLE), angles);
    }

    @Test
    public void testIsAnglesRightShouldReturnTrueWhenAllAnglesRight() {
        //given
        List<Double> angles = new ArrayList<>();
        angles.add(RIGHT_ANGLE);
        angles.add(RIGHT_ANGLE);
        AnglesCalculable anglesCalculator = new AnglesCalculator(new VectorCalculator());
        boolean isRightAngles;
        //when
        isRightAngles = anglesCalculator.isAnglesRight(angles);
        //then
        Assert.assertTrue(isRightAngles);
    }

    @Test
    public void testIsAnglesRightShouldReturnFalseWhenNotAllAnglesRight() {
        //given
        List<Double> angles = new ArrayList<>();
        angles.add(RIGHT_ANGLE);
        angles.add(NOT_RIGHT_ANGLE);
        AnglesCalculable anglesCalculator = new AnglesCalculator(new VectorCalculator());
        boolean isRightAngles;
        //when
        isRightAngles = anglesCalculator.isAnglesRight(angles);
        //then
        Assert.assertFalse(isRightAngles);
    }
}

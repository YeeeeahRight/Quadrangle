package com.epam.quadrangle.data.repository.specification;

import com.epam.quadrangle.data.repository.specification.impl.QuadrangleAreaRangeSpecification;
import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.entity.quadrangle.Quadrangle;
import com.epam.quadrangle.logic.calculator.quadrangle.QuadrangleCalculable;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.Arrays;

public class QuadrangleAreaRangeSpecificationTest {
    private static final Point FIRST_SQUARE_POINT = new Point(0,0);
    private static final Point SECOND_SQUARE_POINT = new Point(0,2);
    private static final Point THIRD_SQUARE_POINT = new Point(2,2);
    private static final Point FOURTH_SQUARE_POINT = new Point(2,0);
    private static final Point THIRD_RECTANGLE_POINT = new Point(40, 2);
    private static final Point FOURTH_RECTANGLE_POINT = new Point(40, 0);
    private static final Quadrangle SQUARE_IN_RANGE = new Quadrangle(Arrays.asList
            (FIRST_SQUARE_POINT, SECOND_SQUARE_POINT, THIRD_SQUARE_POINT, FOURTH_SQUARE_POINT), 0);
    private static final Quadrangle RECTANGLE_OUT_RANGE = new Quadrangle(Arrays.asList
            (FIRST_SQUARE_POINT, SECOND_SQUARE_POINT, THIRD_RECTANGLE_POINT, FOURTH_RECTANGLE_POINT), 0);
    private static final double MIN_AREA = 1.0;
    private static final double MAX_AREA = 10.0;
    private static final double IN_RANGE_AREA = 5.0;
    private static final double OUT_RANGE_AREA = 50.0;

    @Test
    public void testSpecifiedShouldReturnTrueWhenAreaIsInRange() {
        //given
        QuadrangleCalculable calculator = Mockito.mock(QuadrangleCalculable.class);
        Mockito.when(calculator.calculateArea(Mockito.any())).thenReturn(IN_RANGE_AREA);
        QuadrangleAreaRangeSpecification specification
                = new QuadrangleAreaRangeSpecification(MIN_AREA, MAX_AREA, calculator);
        boolean isSpecified;
        //when
        isSpecified = specification.specified(SQUARE_IN_RANGE);
        //then
        Assert.assertTrue(isSpecified);
    }

    @Test
    public void testSpecifiedShouldReturnFalseWhenAreaIsNotInRange() {
        //given
        QuadrangleCalculable calculator = Mockito.mock(QuadrangleCalculable.class);
        Mockito.when(calculator.calculateArea(Mockito.any())).thenReturn(OUT_RANGE_AREA);
        QuadrangleAreaRangeSpecification specification
                = new QuadrangleAreaRangeSpecification(MIN_AREA, MAX_AREA, calculator);
        boolean isSpecified;
        //when
        isSpecified = specification.specified(RECTANGLE_OUT_RANGE);
        //then
        Assert.assertFalse(isSpecified);
    }
}
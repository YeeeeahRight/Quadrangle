package com.epam.quadrangle.data.repository.specification;

import com.epam.quadrangle.data.repository.specification.impl.QuadrangleDistanceFromOriginSpecification;
import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.entity.quadrangle.Quadrangle;
import com.epam.quadrangle.logic.calculator.vector.VectorCalculable;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.Arrays;

public class QuadrangleDistanceFromOriginSpecificationTest {
    private static final double MAX_DISTANCE = 6;
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

    @Test
    public void testSpecifiedShouldReturnTrueWhenQuadrangleCloserOrEqualsToDistance() {
        //given
        VectorCalculable calculator = Mockito.mock(VectorCalculable.class);
        Mockito.when(calculator.calculateVectorLength(Mockito.any())).thenReturn(MAX_DISTANCE);
        QuadrangleDistanceFromOriginSpecification specification =
                new QuadrangleDistanceFromOriginSpecification(MAX_DISTANCE, calculator);
        boolean isSpecified;
        //when
        isSpecified = specification.specified(SQUARE_IN_RANGE);
        //then
        Assert.assertTrue(isSpecified);
    }

    @Test
    public void testSpecifiedShouldReturnFalseWhenQuadrangleFartherThanDistance() {
        //given
        VectorCalculable calculator = Mockito.mock(VectorCalculable.class);
        Mockito.when(calculator.calculateVectorLength(Mockito.any())).thenReturn(MAX_DISTANCE);
        QuadrangleDistanceFromOriginSpecification specification =
                new QuadrangleDistanceFromOriginSpecification(MAX_DISTANCE, calculator);
        boolean isSpecified;
        //when
        isSpecified = specification.specified(RECTANGLE_OUT_RANGE);
        //then
        Assert.assertTrue(isSpecified);
    }
}
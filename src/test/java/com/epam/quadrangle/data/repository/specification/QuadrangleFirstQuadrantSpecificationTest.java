package com.epam.quadrangle.data.repository.specification;

import com.epam.quadrangle.data.repository.specification.impl.QuadrangleFirstQuadrantSpecification;
import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.entity.quadrangle.Quadrangle;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class QuadrangleFirstQuadrantSpecificationTest {
    private static final Quadrangle SQUARE_IN_FIRST_QUADRANT = new Quadrangle(Arrays.asList
            (new Point(1,1), new Point(1, 2), new Point(3, 2), new Point(3, 1)), 0);
    private static final Quadrangle SQUARE_OUT_FIRST_QUADRANT = new Quadrangle(Arrays.asList
            (new Point(-2,-2), new Point(-2,  -4), new Point(-4, -4), new Point(-4, -2)), 0);

    @Test
    public void testSpecifiedShouldReturnTrueWhenQuadrangleIsInFirstQuadrant() {
        //given
        QuadrangleFirstQuadrantSpecification specification = new QuadrangleFirstQuadrantSpecification();
        boolean isSpecified;
        //when
        isSpecified = specification.specified(SQUARE_IN_FIRST_QUADRANT);
        //then
        Assert.assertTrue(isSpecified);
    }

    @Test
    public void testSpecifiedShouldReturnFalseWhenQuadrangleIsNotInFirstQuadrant() {
        //given
        QuadrangleFirstQuadrantSpecification specification = new QuadrangleFirstQuadrantSpecification();
        boolean isSpecified;
        //when
        isSpecified = specification.specified(SQUARE_OUT_FIRST_QUADRANT);
        //then
        Assert.assertFalse(isSpecified);
    }
}
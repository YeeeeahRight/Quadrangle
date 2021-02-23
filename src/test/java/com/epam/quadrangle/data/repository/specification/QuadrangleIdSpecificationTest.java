package com.epam.quadrangle.data.repository.specification;

import com.epam.quadrangle.data.repository.specification.impl.QuadrangleIdSpecification;
import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.entity.quadrangle.Quadrangle;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class QuadrangleIdSpecificationTest {
    private static final Point FIRST_SQUARE_POINT = new Point(0,0);
    private static final Point SECOND_SQUARE_POINT = new Point(0,2);
    private static final Point THIRD_SQUARE_POINT = new Point(2,2);
    private static final Point FOURTH_SQUARE_POINT = new Point(2,0);
    private static final int ID = 0;
    private static final Quadrangle SQUARE = new Quadrangle(Arrays.asList
            (FIRST_SQUARE_POINT, SECOND_SQUARE_POINT, THIRD_SQUARE_POINT, FOURTH_SQUARE_POINT), ID);


    @Test
    public void testSpecifiedShouldReturnTrueWhenQuadrangleIdMatches() {
        //given
        QuadrangleIdSpecification specification = new QuadrangleIdSpecification(ID);
        boolean isSpecified;
        //when
        isSpecified = specification.specified(SQUARE);
        //then
        Assert.assertTrue(isSpecified);
    }

    @Test
    public void testSpecifiedShouldReturnFalseWhenQuadrangleNotMatches() {
        //given
        QuadrangleIdSpecification specification = new QuadrangleIdSpecification(ID + 1);
        boolean isSpecified;
        //when
        isSpecified = specification.specified(SQUARE);
        //then
        Assert.assertFalse(isSpecified);
    }

}
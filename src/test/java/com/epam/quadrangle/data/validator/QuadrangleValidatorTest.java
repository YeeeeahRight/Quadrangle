package com.epam.quadrangle.data.validator;

import com.epam.quadrangle.data.validator.impl.QuadrangleValidator;
import com.epam.quadrangle.entity.Point;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class QuadrangleValidatorTest {
    private static final List<Point> CORRECT_POINTS = new ArrayList<>();
    private static final List<Point> INCORRECT_POINTS = new ArrayList<>();
    private static final List<Point> SINGLE_POINT = new ArrayList<>();

    static {
        CORRECT_POINTS.add(new Point(0, 0));
        CORRECT_POINTS.add(new Point(0, 2));
        CORRECT_POINTS.add(new Point(2, 2));
        CORRECT_POINTS.add(new Point(2, 0));
        INCORRECT_POINTS.add(new Point(0,0));
        INCORRECT_POINTS.add(new Point(0,0));
        INCORRECT_POINTS.add(new Point(3,5));
        INCORRECT_POINTS.add(new Point(1,1));
        SINGLE_POINT.add(new Point(2,2));
    }

    private final QuadrangleValidator quadrangleValidator = new QuadrangleValidator();

    @Test
    public void testIsValidShouldReturnTrueWhenPointsAreCorrect() {
        //given
        boolean isValidQuadrangle;
        //when
        isValidQuadrangle = quadrangleValidator.isValid(CORRECT_POINTS);
        //then
        Assert.assertTrue(isValidQuadrangle);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenPointsAreNotDifferent() {
        //given
        boolean isValidQuadrangle;
        //when
        isValidQuadrangle = quadrangleValidator.isValid(INCORRECT_POINTS);
        //then
        Assert.assertFalse(isValidQuadrangle);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenPointIsSingle() {
        //given
        boolean isValidQuadrangle;
        //when
        isValidQuadrangle = quadrangleValidator.isValid(SINGLE_POINT);
        //then
        Assert.assertFalse(isValidQuadrangle);
    }
}

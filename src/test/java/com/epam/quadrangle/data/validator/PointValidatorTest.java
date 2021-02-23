package com.epam.quadrangle.data.validator;

import com.epam.quadrangle.data.validator.impl.PointValidator;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PointValidatorTest {
    private static final List<String> CORRECT_LINE = new ArrayList<>(Arrays.asList("3", "5"));
    private static final List<String> CORRECT_FLOATING_POINT_COORDINATES_LINE = new ArrayList<>(Arrays.asList("3.5", "5.113"));
    private static final List<String> INCORRECT_FLOATING_POINT_COORDINATES_LINE = new ArrayList<>(Arrays.asList("3.qwet", "0005,113"));
    private static final List<String> INCORRECT_ONE_COORDINATE_LINE = new ArrayList<>(Arrays.asList("abc", "5"));
    private static final List<String> INCORRECT_POINT_LINE = new ArrayList<>(Arrays.asList("abc", "7328rwgdfg5-"));
    private static final List<String> ONE_COORDINATE_LINE = new ArrayList<>(Collections.singletonList("4.5"));
    private final PointValidator pointValidator = new PointValidator();

    @Test
    public void testIsValidShouldReturnTrueWhenLineIsCorrect() {
        //given
        boolean isCorrectPoint;
        //when
        isCorrectPoint = pointValidator.isValid(CORRECT_LINE);
        //then
        Assert.assertTrue(isCorrectPoint);
    }


    @Test
    public void testIsValidShouldReturnTrueWhenFloatingPointCoordinatesAreCorrect() {
        //given
        boolean isCorrectPoint;
        //when
        isCorrectPoint = pointValidator.isValid(CORRECT_FLOATING_POINT_COORDINATES_LINE);
        //then
        Assert.assertTrue(isCorrectPoint);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenFloatingPointCoordinatesAreIncorrect() {
        //given
        boolean isCorrectPoint;
        //when
        isCorrectPoint = pointValidator.isValid(INCORRECT_FLOATING_POINT_COORDINATES_LINE);
        //then
        Assert.assertFalse(isCorrectPoint);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenOneCoordinateIsIncorrect() {
        //given
        boolean isCorrectPoint;
        //when
        isCorrectPoint = pointValidator.isValid(INCORRECT_ONE_COORDINATE_LINE);
        //then
        Assert.assertFalse(isCorrectPoint);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenLineIsIncorrect() {
        //given
        boolean isCorrectPoint;
        //when
        isCorrectPoint = pointValidator.isValid(INCORRECT_POINT_LINE);
        //then
        Assert.assertFalse(isCorrectPoint);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenCoordinateIsOne() {
        //given
        boolean isCorrectPoint;
        //when
        isCorrectPoint = pointValidator.isValid(ONE_COORDINATE_LINE);
        //then
        Assert.assertFalse(isCorrectPoint);
    }
}

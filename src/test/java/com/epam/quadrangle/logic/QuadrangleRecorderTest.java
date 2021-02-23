package com.epam.quadrangle.logic;

import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.entity.quadrangle.Quadrangle;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

public class QuadrangleRecorderTest {
    private static final Point FIRST_SQUARE_POINT = new Point(0,0);
    private static final Point SECOND_SQUARE_POINT = new Point(0,2);
    private static final Point THIRD_SQUARE_POINT = new Point(2,2);
    private static final Point FOURTH_SQUARE_POINT = new Point(2,0);
    private static final int ID = 0;
    private static final Quadrangle SQUARE = new Quadrangle(Arrays.asList
            (FIRST_SQUARE_POINT, SECOND_SQUARE_POINT, THIRD_SQUARE_POINT, FOURTH_SQUARE_POINT), ID);
    private static final Point THIRD_RECTANGLE_POINT = new Point(4, 2);
    private static final Point FOURTH_RECTANGLE_POINT = new Point(4, 0);
    private static final Quadrangle RECTANGLE = new Quadrangle(Arrays.asList
            (FIRST_SQUARE_POINT, SECOND_SQUARE_POINT, THIRD_RECTANGLE_POINT, FOURTH_RECTANGLE_POINT), ID + 1);
    private static final double DELTA = 0.0001;

    static {
        QuadrangleRecorder recorder = QuadrangleRecorder.getInstance();
        recorder.update(SQUARE);
    }

    @Test
    public void testGetAreaShouldReturnAreaWhenContains() {
        //given
        QuadrangleRecorder recorder = QuadrangleRecorder.getInstance();
        //when
        Optional<Double> optional = recorder.getArea(ID);
        //then
        Assert.assertTrue(optional.isPresent());
    }

    @Test
    public void testGetAreaShouldReturnEmptyOptionalWhenNotContains() {
        //given
        QuadrangleRecorder recorder = QuadrangleRecorder.getInstance();
        //when
        Optional<Double> optional = recorder.getArea(ID - 1);
        //then
        Assert.assertFalse(optional.isPresent());
    }

    @Test
    public void testGetPerimeterShouldReturnPerimeterWhenContains() {
        //given
        QuadrangleRecorder recorder = QuadrangleRecorder.getInstance();
        //when
        Optional<Double> optional = recorder.getPerimeter(ID);
        //then
        Assert.assertTrue(optional.isPresent());
    }

    @Test
    public void testGetPerimeterShouldReturnEmptyOptionalWhenNotContains() {
        //given
        QuadrangleRecorder recorder = QuadrangleRecorder.getInstance();
        //when
        Optional<Double> optional = recorder.getPerimeter(ID - 1);
        //then
        Assert.assertFalse(optional.isPresent());
    }

    @Test
    public void testUpdateShouldAddParametersForQuadrangleIfNotContains() {
        //given
        QuadrangleRecorder recorder = QuadrangleRecorder.getInstance();
        recorder.update(RECTANGLE);
        //when
        Optional<Double> optional = recorder.getPerimeter(ID + 1);
        //then
        Assert.assertTrue(optional.isPresent());
    }

    @Test
    public void testUpdateShouldUpdateAreaForQuadrangleIfContains() {
        //given
        QuadrangleRecorder recorder = QuadrangleRecorder.getInstance();
        RECTANGLE.setPoints(Arrays.asList(FIRST_SQUARE_POINT, SECOND_SQUARE_POINT,
                new Point(5,2), new Point(5, 0)));
        recorder.update(RECTANGLE);
        double expected = 10;
        //when
        Optional<Double> optional = recorder.getArea(ID + 1);
        double actual = optional.get();
        //then
        Assert.assertEquals(expected, actual, DELTA);
    }
}
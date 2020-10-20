package com.epam.quadrangle.data.parser;

import com.epam.quadrangle.data.validator.impl.PointValidator;
import com.epam.quadrangle.entity.Point;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyListOf;

public class PointParserTest {
    private static final double TEST_X_COORDINATE = 2;
    private static final double TEST_Y_COORDINATE = 4;
    private static final String TEST_LINE = "2 4";

    @Test
    public void testParserPointShouldParseWhenLineIsCorrect() {
        //given
        PointValidator validator = Mockito.mock(PointValidator.class);
        when(validator.isValid(anyListOf(String.class))).thenReturn(true);
        PointParser pointParser = new PointParser(validator);
        Point expectedPoint = new Point(TEST_X_COORDINATE, TEST_Y_COORDINATE);
        //when
        Point actualPoint = pointParser.parsePoint(TEST_LINE);
        //then
        Assert.assertEquals(expectedPoint, actualPoint);
    }

    @Test
    public void testParserPointShouldNotParseWhenLineIsIncorrect() {
        //when
        PointValidator validator = Mockito.mock(PointValidator.class);
        when(validator.isValid(anyListOf(String.class))).thenReturn(false);
        PointParser pointParser = new PointParser(validator);
        Point actualPoint = pointParser.parsePoint(TEST_LINE);
        //then
        Assert.assertNull(actualPoint);
    }
}

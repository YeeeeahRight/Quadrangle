package com.epam.quadrangle.data.director;

import com.epam.quadrangle.data.parser.PointParser;
import com.epam.quadrangle.data.reader.impl.FileDataReader;
import com.epam.quadrangle.data.validator.impl.QuadrangleValidator;
import com.epam.quadrangle.exceptions.InputDataException;
import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.entity.quadrangle.Quadrangle;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class QuadrangleDirectorTest {
    private static final Point PARSED_TEST_POINT_FIRST = new Point(0, 0);
    private static final Point PARSED_TEST_POINT_SECOND = new Point(0, 2);
    private static final Point PARSED_TEST_POINT_THIRD = new Point(2, 2);
    private static final Point PARSED_TEST_POINT_FOURTH = new Point(2, 0);
    private static final List<String> TEST_ONE_QUADRANGLE_DATA = new ArrayList<>(
            Arrays.asList(PARSED_TEST_POINT_FIRST.toString(), PARSED_TEST_POINT_SECOND.toString(),
                    PARSED_TEST_POINT_THIRD.toString(), PARSED_TEST_POINT_FOURTH.toString()));
    private static final List<String> TEST_THREE_QUADRANGLES_DATA = new ArrayList<>();
    static {
        TEST_THREE_QUADRANGLES_DATA.addAll(TEST_ONE_QUADRANGLE_DATA);
        TEST_THREE_QUADRANGLES_DATA.addAll(TEST_ONE_QUADRANGLE_DATA);
        TEST_THREE_QUADRANGLES_DATA.addAll(TEST_ONE_QUADRANGLE_DATA);
    }
    private static final Quadrangle QUADRANGLE = new Quadrangle(Arrays.asList(PARSED_TEST_POINT_FIRST,
            PARSED_TEST_POINT_SECOND, PARSED_TEST_POINT_THIRD, PARSED_TEST_POINT_FOURTH), 0);
    private static final List<Quadrangle> TEST_LIST_QUADRANGLE_SINGLE =
            new ArrayList<>(Collections.singletonList(QUADRANGLE));
    private static final List<Quadrangle> TEST_LIST_QUADRANGLES_THREE =
            new ArrayList<>(Arrays.asList(QUADRANGLE, QUADRANGLE, QUADRANGLE));

    @Test
    public void testCreateQuadranglesShouldCreateWhenSuccessfullyReadParsedValidatedOneQuadrangle() throws InputDataException {
        //given
        FileDataReader dataReader = Mockito.mock(FileDataReader.class);
        PointParser pointParser = Mockito.mock(PointParser.class);
        QuadrangleValidator quadrangleValidator = Mockito.mock(QuadrangleValidator.class);
        when(dataReader.readData()).thenReturn(TEST_ONE_QUADRANGLE_DATA);
        when(pointParser.parsePoint(anyString())).thenReturn(PARSED_TEST_POINT_FIRST, PARSED_TEST_POINT_SECOND,
                PARSED_TEST_POINT_THIRD, PARSED_TEST_POINT_FOURTH);
        when(quadrangleValidator.isValid(anyListOf(Point.class))).thenReturn(true);
        QuadrangleDirector quadrangleDirector = new QuadrangleDirector(dataReader, pointParser, quadrangleValidator);
        //when
        List<Quadrangle> quadrangle = quadrangleDirector.createQuadrangles();
        //then
        Assert.assertEquals(TEST_LIST_QUADRANGLE_SINGLE, quadrangle);
    }

    @Test
    public void testCreateQuadranglesShouldCreateWhenSuccessfullyReadParsedValidatedThreeQuadrangles() throws InputDataException {
        //given
        FileDataReader dataReader = Mockito.mock(FileDataReader.class);
        PointParser pointParser = Mockito.mock(PointParser.class);
        QuadrangleValidator quadrangleValidator = Mockito.mock(QuadrangleValidator.class);
        when(dataReader.readData()).thenReturn(TEST_THREE_QUADRANGLES_DATA);
        when(pointParser.parsePoint(anyString())).thenReturn(
                PARSED_TEST_POINT_FIRST, PARSED_TEST_POINT_SECOND, PARSED_TEST_POINT_THIRD, PARSED_TEST_POINT_FOURTH,
                PARSED_TEST_POINT_FIRST, PARSED_TEST_POINT_SECOND, PARSED_TEST_POINT_THIRD, PARSED_TEST_POINT_FOURTH,
                PARSED_TEST_POINT_FIRST, PARSED_TEST_POINT_SECOND, PARSED_TEST_POINT_THIRD, PARSED_TEST_POINT_FOURTH);
        when(quadrangleValidator.isValid(anyListOf(Point.class))).thenReturn(true);
        QuadrangleDirector quadrangleDirector = new QuadrangleDirector(dataReader, pointParser, quadrangleValidator);
        //when
        List<Quadrangle> quadrangles = quadrangleDirector.createQuadrangles();
        //then
        Assert.assertEquals(TEST_LIST_QUADRANGLES_THREE, quadrangles);
    }

    @Test
    public void testCreateQuadranglesShouldNotCreateWhenParserIsNotParsed() throws InputDataException {
        //given
        FileDataReader dataReader = Mockito.mock(FileDataReader.class);
        PointParser pointParser = Mockito.mock(PointParser.class);
        QuadrangleValidator quadrangleValidator = Mockito.mock(QuadrangleValidator.class);
        when(dataReader.readData()).thenReturn(TEST_ONE_QUADRANGLE_DATA);
        when(pointParser.parsePoint(anyString())).thenReturn(null);
        when(quadrangleValidator.isValid(anyListOf(Point.class))).thenReturn(true);
        QuadrangleDirector quadrangleDirector = new QuadrangleDirector(dataReader, pointParser, quadrangleValidator);
        //when
        List<Quadrangle> quadrangle = quadrangleDirector.createQuadrangles();
        //then
        Assert.assertNull(quadrangle);
    }

    @Test
    public void testCreateQuadranglesShouldNotCreateWhenValidatorIsNotValidated() throws InputDataException {
        //given
        FileDataReader dataReader = Mockito.mock(FileDataReader.class);
        PointParser pointParser = Mockito.mock(PointParser.class);
        QuadrangleValidator quadrangleValidator = Mockito.mock(QuadrangleValidator.class);
        when(dataReader.readData()).thenReturn(TEST_ONE_QUADRANGLE_DATA);
        when(pointParser.parsePoint(anyString())).thenReturn(PARSED_TEST_POINT_FIRST, PARSED_TEST_POINT_SECOND,
                PARSED_TEST_POINT_THIRD, PARSED_TEST_POINT_FOURTH);
        when(quadrangleValidator.isValid(anyListOf(Point.class))).thenReturn(false);
        QuadrangleDirector quadrangleDirector = new QuadrangleDirector(dataReader, pointParser, quadrangleValidator);
        //when
        List<Quadrangle> quadrangle = quadrangleDirector.createQuadrangles();
        //then
        Assert.assertNull(quadrangle);
    }

    @Test(expected = InputDataException.class)//then
    public void testCreateQuadranglesShouldThrowExceptionWhenReaderThrowException() throws InputDataException {
        //given
        FileDataReader dataReader = Mockito.mock(FileDataReader.class);
        PointParser pointParser = Mockito.mock(PointParser.class);
        QuadrangleValidator quadrangleValidator = Mockito.mock(QuadrangleValidator.class);
        when(dataReader.readData()).thenThrow(InputDataException.class);
        when(pointParser.parsePoint(anyString())).thenReturn(PARSED_TEST_POINT_FIRST, PARSED_TEST_POINT_SECOND,
                PARSED_TEST_POINT_THIRD, PARSED_TEST_POINT_FOURTH);
        when(quadrangleValidator.isValid(anyListOf(Point.class))).thenReturn(true);
        QuadrangleDirector quadrangleDirector = new QuadrangleDirector(dataReader, pointParser, quadrangleValidator);
        //when
        quadrangleDirector.createQuadrangles();
    }
}

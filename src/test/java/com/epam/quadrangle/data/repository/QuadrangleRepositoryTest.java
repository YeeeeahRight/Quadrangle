package com.epam.quadrangle.data.repository;

import com.epam.quadrangle.data.repository.impl.QuadrangleRepository;
import com.epam.quadrangle.data.repository.specification.Specification;
import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.entity.quadrangle.Quadrangle;
import com.epam.quadrangle.exceptions.RepositoryDataException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

public class QuadrangleRepositoryTest {
    private static final Point FIRST_SQUARE_POINT = new Point(0,0);
    private static final Point SECOND_SQUARE_POINT = new Point(0,2);
    private static final Point THIRD_SQUARE_POINT = new Point(2,2);
    private static final Point FOURTH_SQUARE_POINT = new Point(2,0);
    private static final Point THIRD_RECTANGLE_POINT = new Point(4, 2);
    private static final Point FOURTH_RECTANGLE_POINT = new Point(4, 0);
    private static final Quadrangle SQUARE = new Quadrangle(Arrays.asList
            (FIRST_SQUARE_POINT, SECOND_SQUARE_POINT, THIRD_SQUARE_POINT, FOURTH_SQUARE_POINT), 0);
    private static final Quadrangle RECTANGLE = new Quadrangle(Arrays.asList
            (FIRST_SQUARE_POINT, SECOND_SQUARE_POINT, THIRD_RECTANGLE_POINT, FOURTH_RECTANGLE_POINT), 0);

    @Test
    public void testAddShouldAddQuadrangleToRepositoryWhenNotContainsSuch() throws RepositoryDataException {
        //given
        List<Quadrangle> quadrangles = new ArrayList<>();
        QuadrangleRepository repository = new QuadrangleRepository(quadrangles);
        //when
        repository.add(SQUARE);
        //then
        Assert.assertTrue(quadrangles.contains(SQUARE));
    }

    @Test(expected = RepositoryDataException.class)//then
    public void testAddShouldThrowExceptionWhenContainsAddedQuadrangle() throws RepositoryDataException {
        //given
        List<Quadrangle> quadrangles = Collections.singletonList(SQUARE);
        QuadrangleRepository repository = new QuadrangleRepository(quadrangles);
        //when
        repository.add(SQUARE);
    }

    @Test
    public void testRemoveShouldRemoveQuadrangleFromRepositoryWhenContainsSuch() throws RepositoryDataException {
        //given
        List<Quadrangle> quadrangles = new ArrayList<>();
        quadrangles.add(SQUARE);
        QuadrangleRepository repository = new QuadrangleRepository(quadrangles);
        //when
        repository.remove(SQUARE);
        //then
        Assert.assertFalse(quadrangles.contains(SQUARE));
    }

    @Test(expected = RepositoryDataException.class)//then
    public void testRemoveShouldThrowExceptionWhenNotContainsSuchQuadrangle() throws RepositoryDataException {
        //given
        List<Quadrangle> quadrangles = new ArrayList<>();
        QuadrangleRepository repository = new QuadrangleRepository(quadrangles);
        //when
        repository.remove(SQUARE);
    }

    @Test
    public void testUpdateShouldUpdateQuadrangleWhenContainsSuch() {
        //given
        List<Quadrangle> quadrangles = new ArrayList<>();
        quadrangles.add(SQUARE);
        QuadrangleRepository repository = new QuadrangleRepository(quadrangles);
        //when
        repository.update(RECTANGLE);
        Quadrangle actual = quadrangles.get(0);
        //then
        Assert.assertEquals(RECTANGLE, actual);
    }

    @Test
    public void testQueryShouldReturnListOfSpecifiedQuadrangles() {
        //given
        List<Quadrangle> quadrangles = Arrays.asList(SQUARE, RECTANGLE);
        QuadrangleRepository repository = new QuadrangleRepository(quadrangles);
        Specification specification = Mockito.mock(Specification.class);
        Mockito.when(specification.specified(Mockito.any())).thenReturn(true);
        //when
        List<Quadrangle> actual = repository.query(specification);
        //then
        Assert.assertEquals(quadrangles, actual);
    }
}
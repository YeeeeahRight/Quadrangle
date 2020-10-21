package com.epam.quadrangle.data.repository.impl;

import com.epam.quadrangle.data.director.QuadrangleDirector;
import com.epam.quadrangle.data.repository.Repository;
import com.epam.quadrangle.data.repository.specification.Specification;
import com.epam.quadrangle.entity.quadrangle.Quadrangle;
import com.epam.quadrangle.exceptions.RepositoryDataException;
import com.epam.quadrangle.exceptions.UnknownQuadrangleSortType;
import com.epam.quadrangle.logic.comparator.QuadrangleComparatorFactory;
import com.epam.quadrangle.logic.enums.QuadrangleSortType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;

public final class QuadrangleRepository implements Repository<Quadrangle> {
    private static final String ALREADY_CONTAINS_MESSAGE = "Repository already contains quadrangle: ";
    private static final String NOT_CONTAINS_MESSAGE = "Repository doesn't contain quadrangle: ";
    private static final Logger LOGGER = LogManager.getLogger(QuadrangleDirector.class);
    private final QuadrangleComparatorFactory comparatorFactory = new QuadrangleComparatorFactory();
    private final List<Quadrangle> quadrangles;

    public QuadrangleRepository() {
        quadrangles = new ArrayList<>();
    }

    public QuadrangleRepository(List<Quadrangle> source) {
        quadrangles = source;
    }

    @Override
    public void add(Quadrangle quadrangle) throws RepositoryDataException {
        LOGGER.info("trying to add " + quadrangle + " to repository");
        if (quadrangles.contains(quadrangle)) {
            LOGGER.info("Throwing repository data exception");
            throw new RepositoryDataException(ALREADY_CONTAINS_MESSAGE + quadrangle);
        }
        LOGGER.info("added");
        quadrangles.add(quadrangle);
    }

    @Override
    public void remove(Quadrangle quadrangle) throws RepositoryDataException {
        LOGGER.info("trying to remove " + quadrangle + " from repository");
        if (!quadrangles.contains(quadrangle)) {
            LOGGER.info("Throwing repository data exception");
            throw new RepositoryDataException(NOT_CONTAINS_MESSAGE + quadrangle);
        }
        LOGGER.info("removed");
        quadrangles.remove(quadrangle);
    }

    @Override
    public void update(Quadrangle quadrangle) {
        LOGGER.info("trying to update " + quadrangle + " in repository");
        int id = quadrangle.getId();
        Iterator<Quadrangle> iterator = quadrangles.iterator();
        Quadrangle thatQuadrangle;
        while (iterator.hasNext()) {
            thatQuadrangle = iterator.next();
            if (thatQuadrangle.getId() == id) {
                quadrangles.remove(thatQuadrangle);
                quadrangles.add(quadrangle);
                LOGGER.info("updated");
                break;
            }
        }
        LOGGER.info("end of updating");
    }

    @Override
    public void sortByTag(QuadrangleSortType sortType) throws UnknownQuadrangleSortType {
        LOGGER.info("trying to sort by " + sortType + " tag in repository");
        Comparator<Quadrangle> comparator = comparatorFactory.createComparator(sortType);
        quadrangles.sort(comparator);
        LOGGER.info("successfully sorted");
    }

    @Override
    public List<Quadrangle> query(Specification<Quadrangle> specification) {
        LOGGER.info("trying to query in repository");
        List<Quadrangle> result = new LinkedList<>();
        for (Quadrangle quadrangle : quadrangles) {
            if (specification.specified(quadrangle)) {
                LOGGER.info("added");
                result.add(quadrangle);
            }
        }
        LOGGER.info("end of querying");
        return result;
    }
}

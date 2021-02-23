package com.epam.quadrangle.data.repository;

import com.epam.quadrangle.data.repository.specification.Specification;
import com.epam.quadrangle.exceptions.RepositoryDataException;
import com.epam.quadrangle.exceptions.UnknownQuadrangleSortType;
import com.epam.quadrangle.logic.enums.QuadrangleSortType;

import java.util.List;

public interface Repository<T> {
    void add(T quadrangle) throws RepositoryDataException;
    void remove(T quadrangle) throws RepositoryDataException;
    void update(T quadrangle);
    void sortByTag(QuadrangleSortType sortType) throws UnknownQuadrangleSortType;

    List<T> query(Specification<T> specification);
}

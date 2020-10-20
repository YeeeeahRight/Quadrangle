package com.epam.quadrangle.data.repository;

import com.epam.quadrangle.data.repository.specification.Specification;
import com.epam.quadrangle.exceptions.RepositoryDataException;
import java.util.List;

public interface Repository<T> {
    void add(T quadrangle) throws RepositoryDataException;
    void remove(T quadrangle) throws RepositoryDataException;
    void update(T quadrangle);

    List<T> query(Specification<T> specification);
}

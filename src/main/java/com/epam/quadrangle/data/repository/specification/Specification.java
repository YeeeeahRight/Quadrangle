package com.epam.quadrangle.data.repository.specification;


public interface Specification<T> {
    boolean specified(T object);
}

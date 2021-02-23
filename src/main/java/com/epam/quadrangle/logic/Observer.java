package com.epam.quadrangle.logic;

public interface Observer<T> {
    void update(T observable);
}

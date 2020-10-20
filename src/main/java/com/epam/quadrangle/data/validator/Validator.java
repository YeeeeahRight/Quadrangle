package com.epam.quadrangle.data.validator;

public interface Validator<T> {
    boolean isValid(T data);
}

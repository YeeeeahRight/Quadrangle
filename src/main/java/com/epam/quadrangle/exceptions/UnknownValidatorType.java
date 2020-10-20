package com.epam.quadrangle.exceptions;

public class UnknownValidatorType extends IllegalArgumentException {
    public UnknownValidatorType(String s) {
        super(s);
    }
}

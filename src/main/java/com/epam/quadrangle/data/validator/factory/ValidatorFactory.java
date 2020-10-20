package com.epam.quadrangle.data.validator.factory;

import com.epam.quadrangle.data.validator.Validator;
import com.epam.quadrangle.data.validator.enums.ValidatorType;
import com.epam.quadrangle.data.validator.impl.PointValidator;
import com.epam.quadrangle.data.validator.impl.QuadrangleValidator;
import com.epam.quadrangle.exceptions.UnknownValidatorType;

public class ValidatorFactory {
    private static final String UNKNOWN_TYPE_MESSAGE = "Unknown validator type.";

    public Validator createValidator(ValidatorType type) {
        switch (type) {
            case POINT:
                return new PointValidator();
            case QUADRANGLE:
                return new QuadrangleValidator();
            default:
                throw new UnknownValidatorType(UNKNOWN_TYPE_MESSAGE);
        }
    }
}

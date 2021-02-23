package com.epam.quadrangle.data.parser;

import com.epam.quadrangle.data.validator.impl.QuadrangleValidator;
import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.entity.quadrangle.Quadrangle;
import com.epam.quadrangle.logic.id.IdGenerator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class QuadrangleParser {
    private static final Logger LOGGER = LogManager.getLogger(QuadrangleParser.class);
    private static final IdGenerator idGenerator = new IdGenerator();
    private final QuadrangleValidator quadrangleValidator;

    public QuadrangleParser(QuadrangleValidator quadrangleValidator) {
        this.quadrangleValidator = quadrangleValidator;
    }

    public Quadrangle parseQuadrangle(List<Point> pointList) {
        Quadrangle quadrangle = null;
        if (quadrangleValidator.isValid(pointList)) {
            int id = idGenerator.getId();
            quadrangle = new Quadrangle(pointList, id);
        } else {
            LOGGER.warn(pointList + " points are not valid for quadrangle. Not parsed.");
        }
        return quadrangle;
    }
}

package com.epam.quadrangle.data.parser;

import com.epam.quadrangle.data.director.QuadrangleDirector;
import com.epam.quadrangle.data.validator.Validator;
import com.epam.quadrangle.entity.Point;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class PointParser {
    private static final Logger LOGGER = LogManager.getLogger(PointParser.class);
    private static final String SPLITERATOR = "\\s+";
    private final Validator pointValidator;
    private static final int X_COORDINATE = 0;
    private static final int Y_COORDINATE = 1;

    public PointParser(Validator pointValidator) {
        this.pointValidator = pointValidator;
    }

    public Point parsePoint(String linePoints) {
        String[] pointsStringArray = linePoints.split(SPLITERATOR);
        List<String> pointsStringList = Arrays.asList(pointsStringArray);
        Point point = null;
        if (pointValidator.isValid(pointsStringList)) {
            double xCoordinate = Double.parseDouble(pointsStringList.get(X_COORDINATE));
            double yCoordinate = Double.parseDouble(pointsStringList.get(Y_COORDINATE));
            point = new Point(xCoordinate, yCoordinate);
        } else {
            LOGGER.warn(String.format("%s line is not parsed as point", linePoints));
        }
        return point;
    }


}

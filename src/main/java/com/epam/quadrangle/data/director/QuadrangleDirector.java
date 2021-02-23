package com.epam.quadrangle.data.director;

import com.epam.quadrangle.data.parser.PointParser;
import com.epam.quadrangle.data.parser.QuadrangleParser;
import com.epam.quadrangle.data.reader.DataReader;
import com.epam.quadrangle.data.validator.Validator;
import com.epam.quadrangle.exceptions.InputDataException;
import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.entity.quadrangle.Quadrangle;
import com.epam.quadrangle.logic.id.IdGenerator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class QuadrangleDirector {
    private static final Logger LOGGER = LogManager.getLogger(QuadrangleDirector.class);
    private static final int QUADRANGLE_POINTS_AMOUNT = 4;
    private static final int POINT_COORDINATES_AMOUNT = 2;
    private final DataReader dataReader;
    private final PointParser pointParser;
    private final QuadrangleParser quadrangleParser;

    public QuadrangleDirector(DataReader dataReader, PointParser pointParser, QuadrangleParser quadrangleParser) {
        this.dataReader = dataReader;
        this.pointParser = pointParser;
        this.quadrangleParser = quadrangleParser;
    }

    public List<Quadrangle> createQuadrangles() throws InputDataException {
        LOGGER.info("Preparing to create quadrangles");
        List<String> dataLines = dataReader.readData();
        List<Point> points = new ArrayList<>(dataLines.size() / POINT_COORDINATES_AMOUNT);
        LOGGER.info("Start of point parsing");
        for (String line : dataLines) {
            Point point = pointParser.parsePoint(line);
            if (point != null) {
                points.add(point);
            }
        }
        LOGGER.info("End of point parsing");
        int quadranglesAmount = points.size() / QUADRANGLE_POINTS_AMOUNT;
        List<Quadrangle> quadrangles = null;
        if (quadranglesAmount > 0) {
            quadrangles = new ArrayList<>(quadranglesAmount);
            LOGGER.info("Start of parsing quadrangles");
            for (int i = 0; i < quadranglesAmount; i++) {
                int startPos = i * QUADRANGLE_POINTS_AMOUNT;
                List<Point> quadranglePoints = points.subList(startPos, startPos + QUADRANGLE_POINTS_AMOUNT);
                Quadrangle quadrangle = quadrangleParser.parseQuadrangle(quadranglePoints);
                if (quadrangle != null) {
                    quadrangles.add(quadrangle);
                }
            }
            if (quadrangles.size() == 0) {
                quadrangles = null;
                LOGGER.warn("Zero quadrangles are passed");
            }
        } else {
            LOGGER.warn("You have no quadrangles");
        }
        LOGGER.info("End of creating quadrangles");
        return quadrangles;
    }
}

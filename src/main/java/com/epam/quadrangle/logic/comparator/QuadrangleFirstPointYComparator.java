package com.epam.quadrangle.logic.comparator;

import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.entity.quadrangle.Quadrangle;

import java.util.Comparator;


public class QuadrangleFirstPointYComparator implements Comparator<Quadrangle> {
    @Override
    public int compare(Quadrangle firstQuadrangle, Quadrangle secondQuadrangle) {
        Point firstQuadranglePointA = firstQuadrangle.getPoint(0);
        Point secondQuadranglePointA = secondQuadrangle.getPoint(0);
        double firstQuadrangleFirstPointX = firstQuadranglePointA.getY();
        double secondQuadrangleFirstPointX = secondQuadranglePointA.getY();
        return Double.compare(firstQuadrangleFirstPointX, secondQuadrangleFirstPointX);
    }
}

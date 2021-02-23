package com.epam.quadrangle.logic.calculator.quadrangle;

import com.epam.quadrangle.logic.enums.QuadrangleType;
import com.epam.quadrangle.entity.quadrangle.Quadrangle;

public interface QuadrangleCalculable {
    double calculatePerimeter(Quadrangle quadrangle);

    double calculateArea(Quadrangle quadrangle);

    boolean isQuadrangle(Quadrangle quadrangle);

    boolean isConvex(Quadrangle quadrangle);

    QuadrangleType findQuadrangleType(Quadrangle quadrangle);
}

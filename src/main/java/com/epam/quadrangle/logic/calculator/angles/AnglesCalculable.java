package com.epam.quadrangle.logic.calculator.angles;

import com.epam.quadrangle.entity.quadrangle.Quadrangle;

import java.util.List;

public interface AnglesCalculable {
    boolean isAnglesRight(List<Double> angles);
    List<Double> calculateAngles(Quadrangle quadrangle);
}

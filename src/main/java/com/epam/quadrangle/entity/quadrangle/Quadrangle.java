package com.epam.quadrangle.entity.quadrangle;

import com.epam.quadrangle.entity.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quadrangle {
    private final int id;
    private List<Point> points;

    public Quadrangle(List<Point> points, int id) {
        this.points = points;
        this.id = id;
    }

    public Quadrangle(Quadrangle quadrangle) {
        id = quadrangle.id;
        Point firstPoint = new Point(quadrangle.getPoint(0));
        Point secondPoint = new Point(quadrangle.getPoint(1));
        Point thirdPoint = new Point(quadrangle.getPoint(2));
        Point fourthPoint = new Point(quadrangle.getPoint(3));
        points = new ArrayList<>(Arrays.asList(firstPoint,secondPoint,thirdPoint,fourthPoint));
    }

    public Point getPoint(int index) {
        return points.get(index);
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Quadrangle)) {
            return false;
        }

        Quadrangle that = (Quadrangle) o;

        return points.equals(that.points);
    }

    @Override
    public int hashCode() {
        return points.hashCode();
    }

    @Override
    public String toString() {
        return "Quadrangle{" +
                "points=" + points +
                '}';
    }
}

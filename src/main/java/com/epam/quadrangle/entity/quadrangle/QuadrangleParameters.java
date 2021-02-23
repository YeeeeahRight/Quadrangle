package com.epam.quadrangle.entity.quadrangle;

public class QuadrangleParameters {
    private final double area;
    private final double perimeter;

    public QuadrangleParameters(double area, double perimeter) {
        this.area = area;
        this.perimeter = perimeter;
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuadrangleParameters)) return false;

        QuadrangleParameters that = (QuadrangleParameters) o;

        if (Double.compare(that.getArea(), getArea()) != 0) return false;
        return Double.compare(that.getPerimeter(), getPerimeter()) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getArea());
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getPerimeter());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

package com.epam.quadrangle.entity.quadrangle;

import com.epam.quadrangle.entity.Point;
import com.epam.quadrangle.logic.Observable;
import com.epam.quadrangle.logic.Observer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuadrangleObservable extends Quadrangle implements Observable<Quadrangle> {
    private final Set<Observer<Quadrangle>> observers = new HashSet<>();

    public QuadrangleObservable(Quadrangle quadrangle) {
        super(quadrangle);
    }

    @Override
    public void addObserver(Observer<Quadrangle> observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<Quadrangle> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer<Quadrangle> observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public void setPoints(List<Point> points) {
        super.setPoints(points);
        notifyObservers();
    }
}

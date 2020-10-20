package com.epam.quadrangle.logic.id;

public class IdGenerator {
    private int id = 0;

    public int getId() {
        return id++;
    }
}

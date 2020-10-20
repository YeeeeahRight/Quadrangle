package com.epam.quadrangle.data.reader;

import com.epam.quadrangle.exceptions.InputDataException;

import java.util.List;

public interface DataReader {
    List<String> readData() throws InputDataException;
}

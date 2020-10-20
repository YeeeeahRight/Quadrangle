package com.epam.quadrangle.data.reader.factory.impl;

import com.epam.quadrangle.data.reader.DataReader;
import com.epam.quadrangle.data.reader.factory.DataReaderFactory;
import com.epam.quadrangle.data.reader.impl.FileDataReader;

public class FileDataReaderFactory implements DataReaderFactory {
    private static final String FILE_PATH = "data.txt";

    public DataReader createReader() {
        return new FileDataReader(FILE_PATH);
    }
}

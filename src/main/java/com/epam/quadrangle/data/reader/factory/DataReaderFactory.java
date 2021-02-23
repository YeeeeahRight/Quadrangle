package com.epam.quadrangle.data.reader.factory;

import com.epam.quadrangle.data.reader.DataReader;

public interface DataReaderFactory {
    DataReader createReader();
}

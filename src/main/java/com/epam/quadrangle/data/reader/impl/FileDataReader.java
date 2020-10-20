package com.epam.quadrangle.data.reader.impl;

import com.epam.quadrangle.data.reader.DataReader;
import com.epam.quadrangle.exceptions.InputDataException;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileDataReader implements DataReader {
    private static final String INPUT_ERROR_MESSAGE = "Something wrong with file input.";
    private static final Logger LOGGER = Logger.getLogger(FileDataReader.class);
    private final String fileName;

    public FileDataReader(String fileName) {
        this.fileName = fileName;
    }

    public List<String> readData() throws InputDataException {
        LOGGER.info("Reading quadrangle data from the file");
        BufferedReader fileReader = null;
        List<String> booksLines = new ArrayList<>();
        try {
            fileReader = new BufferedReader(new FileReader(fileName));
            String line;
            line = fileReader.readLine();
            while (line != null) {
                booksLines.add(line);
                line = fileReader.readLine();
            }
        } catch (IOException e) {
            LOGGER.info("Exception: " + e.toString());
            throw new InputDataException(INPUT_ERROR_MESSAGE, e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        LOGGER.info("End of reading data");
        return booksLines;
    }
}

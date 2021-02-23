package com.epam.quadrangle.data.reader;

import com.epam.quadrangle.data.reader.impl.FileDataReader;
import com.epam.quadrangle.exceptions.InputDataException;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDataReaderTest {
    private static final String DATA_FILE_EXIST = "src/test/resources/data.txt";
    private static final String DATA_FILE_NOT_EXIST = "src/test/resources/qwerty12345.txt";

    @Test
    public void testGetDataShouldGetFileWhenFileIsExist() throws InputDataException, IOException {
        //given
        FileDataReader dataAcquirer = new FileDataReader(DATA_FILE_EXIST);
        List<String> expectedLines = readFileLines();
        //when
        List<String> actual = dataAcquirer.readData();
        //then
        Assert.assertEquals(expectedLines, actual);
    }

    private List<String> readFileLines() throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader fileReader;
        fileReader = new BufferedReader(new FileReader(DATA_FILE_EXIST));
        String line = fileReader.readLine();
        while (line != null) {
            lines.add(line);
            line = fileReader.readLine();
        }
        return lines;
    }

    @Test(expected = InputDataException.class)//then
    public void testGetDataShouldThrowExceptionWhenFileIsNotFound() throws InputDataException {
        //when
        (new FileDataReader(DATA_FILE_NOT_EXIST)).readData();
    }

}
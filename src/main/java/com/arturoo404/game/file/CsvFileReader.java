package com.arturoo404.game.file;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.List;

public class CsvFileReader {

    public List<String[]> read() throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader("src/main/resources/level/level_1.csv"));
        return reader.readAll();
    }
}

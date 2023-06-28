package com.arturoo404.game.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.List;

public class FileReader {

    /**
     * Read csv file
     * @return
     * @throws IOException
     * @throws CsvException
     */
    public List<String[]> read() throws IOException, CsvException {
        CSVReader reader = new CSVReader(new java.io.FileReader("src/main/resources/level/level_1.csv"));
        return reader.readAll();
    }

    public EntityModelFile entityModelFilesRead() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/entity/entity_location.json");
        return objectMapper.readValue(file, EntityModelFile.class);
    }
}

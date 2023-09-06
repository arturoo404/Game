package com.arturoo404.game.file;

import com.arturoo404.game.item.Item;
import com.arturoo404.game.item.ItemName;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.*;

public class CustomFileReader {
    private GameOptions gameOptions;

    /**
     * Read csv file
     * @return
     * @throws IOException
     * @throws CsvException
     */
    public List<String[]> read() throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader("src/main/resources/level/level_1.csv"));
        return reader.readAll();
    }

    public static EntityMainModel entityModelFilesRead() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/entity/entity_location.json");
        return objectMapper.readValue(file, EntityMainModel.class);
    }

    public static Map<ItemName, Item> readItems(String... urls) throws IOException {
        Map<ItemName, Item> map = new HashMap<>();
        for (String url : urls){
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
            File file = new File("src/main/resources/item/" + url + "/"+ url + ".json");
            Map<String, Object> tempMap = objectMapper.readValue(file, new TypeReference<>() {});
            for (Map.Entry<String, Object> entry : tempMap.entrySet()) {
                ItemName key = ItemName.valueOf(entry.getKey());
                Item value = objectMapper.convertValue(entry.getValue(), Item.class);
                map.put(key, value);
            }
        }
        return map;
    }

    public void gameOptionReader() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            gameOptions = objectMapper.readValue(new File("src/main/resources/options/game_options.json"), GameOptions.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public GameOptions getGameOptions() {
        return gameOptions;
    }
}
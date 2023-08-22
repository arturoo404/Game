package com.arturoo404.game.item;

import com.arturoo404.game.file.FileReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ItemAction {

    private Map<ItemName, Item> itemMap = new HashMap<>();

    public void init() throws IOException {
        itemMap = FileReader.readItems();
    }
}

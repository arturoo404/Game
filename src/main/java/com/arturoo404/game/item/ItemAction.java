package com.arturoo404.game.item;

import com.arturoo404.game.file.FileReader;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ItemAction {

    private Map<ItemName, Item> itemMap = new HashMap<>();

    public void init() throws IOException {
        itemMap = FileReader.readItems("armor", "belt", "earring", "gloves", "helmet", "ingredients", "necklace", "ring", "shoes", "weapon");
    }

    public Item getItemByName(ItemName itemName){
        return itemMap.get(itemName);
    }

    public Image getItemImageByName(ItemName itemName){
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(itemMap.get(itemName).getTxtPath())));
    }

}

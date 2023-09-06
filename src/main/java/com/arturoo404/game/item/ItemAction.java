package com.arturoo404.game.item;

import com.arturoo404.game.entity.Entity;
import com.arturoo404.game.file.CustomFileReader;
import com.google.gson.internal.LinkedTreeMap;
import javafx.scene.image.Image;

import java.util.*;

public class ItemAction {

    private Map<ItemName, Item> itemsMap = new LinkedTreeMap<>();
    private List<Item> itemsAtMap = new ArrayList<>();

    public void init() {
        try {
            itemsMap.putAll(CustomFileReader
                    .readItems("armor", "belt", "earring", "gloves", "helmet", "ingredients", "necklace", "ring", "shoes", "weapon"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Item getItemByName(ItemName itemName){
        return itemsMap.get(itemName);
    }

    public Image getItemImageByName(ItemName itemName){
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(itemsMap.get(itemName).getTxtPath())));
    }

    public void createItemAtMap(Entity entity){
        entity.getDropItems().stream()
                .forEach(dropItem -> {
                    Item item = getItemByName(dropItem.getItemName());
                });
    }

}

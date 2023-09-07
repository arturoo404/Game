package com.arturoo404.game.item;

import com.arturoo404.game.entity.Entity;
import com.arturoo404.game.file.CustomFileReader;
import com.google.gson.internal.LinkedTreeMap;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;

public class ItemAction {

    private Map<ItemName, ItemBasic> itemsMap = new LinkedTreeMap<>();
    private List<Item> itemsAtMap = new ArrayList<>();

    public void init() {
        try {
            itemsMap.putAll(CustomFileReader
                    .readItems("armor", "belt", "earring", "gloves", "helmet", "ingredients", "necklace", "ring", "shoes", "weapon"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public ItemBasic getItemByName(ItemName itemName){
        return itemsMap.get(itemName);
    }

    public Image getItemImageByName(ItemName itemName){
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(itemsMap.get(itemName).getTxtPath())));
    }

    public void createItemAtMap(Entity entity){
        entity.getDropItems()
                .forEach(dropItem -> itemsAtMap
                        .add(new Item(getItemByName(dropItem.getItemName()), generateItemAtMapRect(entity))));
    }

    private Rectangle generateItemAtMapRect(Entity entity){
        Rectangle item = new Rectangle(entity.getRectangle().getX(), entity.getRectangle().getY(), 60, 60);
        item.setFill(Color.AQUA);
        entity.getPane().getChildren().add(item);
        return item;
    }
}

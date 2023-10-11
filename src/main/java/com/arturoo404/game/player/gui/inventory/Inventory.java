package com.arturoo404.game.player.gui.inventory;


import com.arturoo404.game.item.ItemAction;
import com.arturoo404.game.item.ItemBasic;
import com.arturoo404.game.item.ItemName;
import com.arturoo404.game.player.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private ItemAction itemAction;
    private Player player;
    private Map<Slot, ItemBasic> slotItemMap = new HashMap<>(){{
                slotItemMap.put(Slot.SLOT_1, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_2, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_3, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_4, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_5, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_6, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_7, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_8, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_9, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_10, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_11, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_12, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_13, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_14, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_15, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_16, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_17, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_18, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_19, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_20, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_21, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_22, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_23, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
                slotItemMap.put(Slot.SLOT_24, itemAction.getItemByName(ItemName.BASIC_INGREDIENTS));
    }};

    public void init(){
        inventoryTask();
    }


    private void inventoryTask(){
        Thread thread = new Thread(() -> {
            Timeline entityDetection = new Timeline(new KeyFrame(Duration.millis(100), actionEvent -> {
               // for (itemAction.)
            }));
            entityDetection.setCycleCount(Animation.INDEFINITE);
            entityDetection.play();
        });

        thread.start();
    }

    public Inventory(ItemAction itemAction, Player player) {
        this.itemAction = itemAction;
        this.player = player;
    }
}

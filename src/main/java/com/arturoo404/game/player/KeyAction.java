package com.arturoo404.game.player;

import com.arturoo404.game.player.gui.inventory.InventoryOpen;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;


@Getter
public class KeyAction {
    private InventoryOpen inventoryOpen;

    private BooleanProperty openInventory = new SimpleBooleanProperty();
    private final BooleanBinding keyPress = openInventory.or(openInventory);

    public void init(){
        inventoryOpen = new InventoryOpen();
        keyPress.addListener(((observableValue, aBoolean, t1) -> {
            if (aBoolean){
                try {
                    inventoryOpen.openInventory();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {

            }
        }));
    }

    public void setOpenInventory(boolean openInventory) {
        this.openInventory.set(openInventory);
    }
}

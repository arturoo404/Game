package com.arturoo404.game.player;

import com.arturoo404.game.player.gui.inventory.InventoryOpen;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.Initializable;
import lombok.Getter;

import java.io.IOException;


@Getter
public class KeyAction {
    private InventoryOpen inventoryOpen;
    private final Initializable initializable;

    private final BooleanProperty openInventory = new SimpleBooleanProperty(true);
    private final BooleanBinding keyPress = openInventory.or(openInventory);

    public void init(){
        inventoryOpen = new InventoryOpen(initializable);
        keyPress.addListener(((observableValue, aBoolean, t1) -> {
            if (aBoolean){
                try {
                    inventoryOpen.openInventory();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                try {
                    inventoryOpen.closeInventory();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }));
    }

    public void setOpenInventory(boolean openInventory) {
        this.openInventory.set(openInventory);
    }

    public KeyAction(Initializable initializable) {
        this.initializable = initializable;
    }
}

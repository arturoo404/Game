package com.arturoo404.game.player;

import com.arturoo404.game.player.gui.inventory.InventoryOpen;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;

import java.io.IOException;


@Getter
public class KeyAction {
    private InventoryOpen inventoryOpen;
    private final Initializable initializable;

    private final BooleanProperty openInventory = new SimpleBooleanProperty(false);
    private final BooleanBinding keyPress = openInventory.or(openInventory);

    public void init(Player player){
        inventoryOpen = new InventoryOpen(initializable, player);
        keyPress.addListener(((observableValue, aBoolean, t1) -> {
            try {
                inventoryOpen.inventoryAction(aBoolean);
            } catch (IOException e) {
                throw new RuntimeException(e);
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

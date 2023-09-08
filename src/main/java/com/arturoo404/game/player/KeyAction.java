package com.arturoo404.game.player;

import com.arturoo404.game.player.gui.inventory.InventoryOpen;
import com.arturoo404.game.player.gui.mastery.MasteryOpen;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.Initializable;
import lombok.Getter;

import java.io.IOException;


@Getter
public class KeyAction {
    private MasteryOpen masteryOpen;
    private InventoryOpen inventoryOpen;
    private final Initializable initializable;

    private final BooleanProperty openMastery = new SimpleBooleanProperty(false);
    private final BooleanProperty openInventory = new SimpleBooleanProperty(false);
    private final BooleanBinding keyPress = openInventory.or(openInventory);
    private final BooleanBinding masteryKeyPress = openMastery.or(openMastery);

    public void init(Player player){
        masteryOpen = new MasteryOpen(initializable, player);
        inventoryOpen = new InventoryOpen(initializable, player);
        keyPress.addListener(((observableValue, aBoolean, t1) -> {
            try {
                inventoryOpen.inventoryAction(aBoolean);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        masteryKeyPress.addListener((((observableValue, aBoolean, t1) -> {
            try {
                masteryOpen.masteryAction(aBoolean);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        })));
    }

    public void setOpenInventory(boolean openInventory) {
        this.openInventory.set(openInventory);
    }
    public void setOpenMastery(boolean openMastery) {
        this.openMastery.set(openMastery);
    }

    public KeyAction(Initializable initializable) {
        this.initializable = initializable;
    }
}

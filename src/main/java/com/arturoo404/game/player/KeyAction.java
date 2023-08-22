package com.arturoo404.game.player;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;


@Getter
public class KeyAction {
    private BooleanProperty openInventory = new SimpleBooleanProperty();
    private final BooleanBinding keyPress = openInventory.or(openInventory);

    public void init(){
        keyPress.addListener(((observableValue, aBoolean, t1) -> {
//            if (aBoolean){
//                openInventory
//            }else {
//                closeInventory
//            }
        }));
    }

    public void setOpenInventory(boolean openInventory) {
        this.openInventory.set(openInventory);
    }
}

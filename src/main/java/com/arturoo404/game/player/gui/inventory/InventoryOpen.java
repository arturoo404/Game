package com.arturoo404.game.player.gui.inventory;

import com.arturoo404.game.player.Player;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import lombok.Getter;

import java.io.IOException;

public class InventoryOpen {
    private Initializable initializable;
    private Player player;
    @Getter
    private  Parent root;

    public void inventoryAction(boolean isOpen) throws IOException {
        if (!isOpen){
            FXMLLoader loader = new FXMLLoader(initializable.getClass().getResource("inventory-window.fxml"));
            root = loader.load();
            root.setLayoutX(player.getPlayerGuiController().getPane().getLayoutX() + 550);
            root.setLayoutY(player.getPlayerGuiController().getPane().getLayoutY() - 650);
            player.getAnchorPane().getChildren().addAll(root);
        }else {
            player.getAnchorPane().getChildren().remove(root);
            player.getAnchorPane().requestFocus();
        }
    }

    public InventoryOpen(Initializable initializable, Player player) {
        this.initializable = initializable;
        this.player = player;
    }

}


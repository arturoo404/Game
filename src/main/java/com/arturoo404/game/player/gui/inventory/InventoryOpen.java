package com.arturoo404.game.player.gui.inventory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;

import java.io.IOException;

public class InventoryOpen {
    @Getter
    private Stage inventoryStage;
    public void openInventory() throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inventory-window.fxml"));
            Parent root = loader.load();
            inventoryStage = new Stage();
            inventoryStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root, 600, 650);
            inventoryStage.initStyle(StageStyle.UNDECORATED);
            inventoryStage.initStyle(StageStyle.TRANSPARENT);
            inventoryStage.setScene(scene);
            inventoryStage.show();
    }

        public void closeInventory() throws IOException {

        }
}


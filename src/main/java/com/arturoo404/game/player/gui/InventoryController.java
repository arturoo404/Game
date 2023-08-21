package com.arturoo404.game.player.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class InventoryController {
    private Stage inventoryStage;
    private Scene scene;

    public void setInventoryStage(Stage inventoryStage) {
        this.inventoryStage = inventoryStage;
    }

    public void openInventory() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("inventory-window.fxml"));
        Parent root = loader.load();

        inventoryStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(root, 600, 650);
        inventoryStage.initStyle(StageStyle.UNDECORATED);
        inventoryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(null);
        inventoryStage.setScene(scene);
        inventoryStage.show();
    }

    public Stage getInventoryStage() {
        return inventoryStage;
    }
}


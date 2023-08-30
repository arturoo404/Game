package com.arturoo404.game.player.gui.inventory;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class InventoryOpen {
    private Stage inventoryStage;
    private Initializable initializable;


    public void openInventory() throws IOException {
            FXMLLoader loader = new FXMLLoader(initializable.getClass().getResource("inventory-window.fxml"));
            Parent root = loader.load();
            inventoryStage = new Stage();
            inventoryStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root, 1400, 800);
            inventoryStage.initStyle(StageStyle.UNDECORATED);
            inventoryStage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(null);
            inventoryStage.setScene(scene);
            inventoryStage.show();
    }

        public void closeInventory() throws IOException {
            inventoryStage.close();
        }

    public InventoryOpen(Initializable initializable) {
        this.initializable = initializable;
    }

}


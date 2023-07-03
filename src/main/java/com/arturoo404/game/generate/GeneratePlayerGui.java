package com.arturoo404.game.generate;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GeneratePlayerGui {

    private final Initializable initializable;

    public GeneratePlayerGui(Initializable initializable) {
        this.initializable = initializable;
    }

    public void init() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.initializable.getClass().getResource("player-gui.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);

        Scene scene = new Scene(root, 1000, 200);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        stage.setAlwaysOnTop(true);
        stage.setX(850);
        stage.setY(1000);
    }
}

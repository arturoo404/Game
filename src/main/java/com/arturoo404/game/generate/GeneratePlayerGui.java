package com.arturoo404.game.generate;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GeneratePlayerGui {

    private final Initializable initializable;
    private final AnchorPane pane;

    public GeneratePlayerGui(Initializable initializable, AnchorPane pane) {
        this.initializable = initializable;
        this.pane = pane;
    }

    public void init() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.initializable.getClass().getResource("player-gui.fxml"));
        Parent root = loader.load();
        pane.getChildren().addAll(root);
    }
}

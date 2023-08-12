package com.arturoo404.game.generate;

import com.arturoo404.game.file.FileReader;
import com.arturoo404.game.file.GameOptions;
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
    private Stage stage;

    public GeneratePlayerGui(Initializable initializable) {
        this.initializable = initializable;
    }

    public void init() throws IOException {
        FileReader fileReader = new FileReader();
        fileReader.gameOptionReader();
        GameOptions gameOptions = fileReader.getGameOptions();

        FXMLLoader loader = new FXMLLoader(this.initializable.getClass().getResource("player-gui.fxml"));
        Parent root = loader.load();
        stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(root, 1000, 200);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        stage.setAlwaysOnTop(true);
        stage.setX(540);
        stage.setY(Double.parseDouble(gameOptions.getResolutionV()) - 244);
    }
    public Stage getStage() {
        return stage;
    }
}

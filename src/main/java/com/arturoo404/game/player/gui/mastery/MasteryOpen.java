package com.arturoo404.game.player.gui.mastery;

import com.arturoo404.game.player.Player;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import lombok.Getter;

import java.io.IOException;

public class MasteryOpen {
    private Initializable initializable;
    private Player player;
    @Getter
    private  Parent root;

    public void masteryAction(boolean isOpen) throws IOException {
        if (!isOpen){
            FXMLLoader loader = new FXMLLoader(initializable.getClass().getResource("mastery-window.fxml"));
            root = loader.load();
            root.setLayoutX(player.getPlayerGuiController().getPane().getLayoutX() + 550);
            root.setLayoutY(player.getPlayerGuiController().getPane().getLayoutY() - 650);
            player.getAnchorPane().getChildren().addAll(root);
        }else {
            player.getAnchorPane().getChildren().remove(root);
        }
    }

    public MasteryOpen(Initializable initializable, Player player) {
        this.initializable = initializable;
        this.player = player;
    }

}

package com.arturoo404.game.player;

import com.arturoo404.game.player.Player;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import static com.arturoo404.game.GameWindowController.getPlayer;

public class PlayerGuiController implements Initializable {

    private Player player;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player = getPlayer();
    }
}

package com.arturoo404.game.player.movement;

import com.arturoo404.game.player.Player;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class CameraMovement {

    private final Player player;
    private final AnchorPane pane;

    public CameraMovement(Player player) {
        this.player = player;
        pane = player.getAnchorPane();

    }

    /**
     * Move the camera based on the key pressed
     * @param keyCode
     */

    public void moveCamera(KeyCode keyCode){
        double playerX = player.getPlayerShape().getX();
        double playerY = player.getPlayerShape().getY();

        switch (keyCode){
            case D -> {
                if (playerX > 920 && playerX < 2920){
                    pane.setLayoutX(pane.getLayoutX() - 2);
                    player.getPlayerGuiController().getPane().setLayoutX(
                            player.getPlayerGuiController().getPane().getLayoutX() + 2);
                }
            }
            case A -> {
                if (playerX > 920 && playerX < 2920){
                    pane.setLayoutX(pane.getLayoutX() + 2);
                    player.getPlayerGuiController().getPane().setLayoutX(
                            player.getPlayerGuiController().getPane().getLayoutX() - 2);
                }
            }
            case S -> {
                if (playerY > 480 && playerY < 1560){
                    pane.setLayoutY(pane.getLayoutY() - 2);
                    player.getPlayerGuiController().getPane().setLayoutY(
                            player.getPlayerGuiController().getPane().getLayoutY() + 2);
                }
            }
            case W -> {
                if (playerY > 480 && playerY < 1560){
                    pane.setLayoutY(pane.getLayoutY() + 2);
                    player.getPlayerGuiController().getPane().setLayoutY(
                            player.getPlayerGuiController().getPane().getLayoutY() - 2);
                }
            }
        }
    }
}

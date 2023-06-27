package com.arturoo404.game.player.movement;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class CameraMovement {

    private final Rectangle player;
    private final AnchorPane pane;

    public CameraMovement(Rectangle player) {
        this.player = player;
        pane = (AnchorPane) player.getParent();

    }

    /**
     * Move the camera based on the key pressed
     * @param keyCode
     */

    public void moveCamera(KeyCode keyCode){
        double playerX = player.getX();
        double playerY = player.getY();

        switch (keyCode){
            case D -> {
                if (playerX > 920 && playerX < 2920){
                    pane.setLayoutX(pane.getLayoutX() - 2);
                }
            }
            case A -> {
                if (playerX > 920 && playerX < 2920){
                    pane.setLayoutX(pane.getLayoutX() + 2);
                }
            }
            case S -> {
                if (playerY > 480 && playerY < 1560){
                    pane.setLayoutY(pane.getLayoutY() - 2);
                }
            }
            case W -> {
                if (playerY > 480 && playerY < 1560){
                    pane.setLayoutY(pane.getLayoutY() + 2);
                }
            }
        }
    }
}

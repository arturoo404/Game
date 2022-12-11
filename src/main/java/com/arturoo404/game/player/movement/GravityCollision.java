package com.arturoo404.game.player.movement;

import com.arturoo404.game.player.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class GravityCollision extends AnimationTimer {

    private final List<Rectangle> rectangleList;
    private final Player player;

    public GravityCollision(List<Rectangle> rectangleList, Player player) {
        this.rectangleList = rectangleList;
        this.player = player;
    }

    @Override
    public void handle(long now) {
        Rectangle playerRect = player.getRectangle();

        for (Rectangle rectangle : rectangleList){
            if (playerRect.getY() + 63 >= rectangle.getY() && playerRect.getY() + 63 <= rectangle.getY() + 63 && playerRect.getX() >= rectangle.getX() && playerRect.getX() < rectangle.getX() + 60 ||
                    playerRect.getY() + 63 >= rectangle.getY() && playerRect.getY() + 63 <= rectangle.getY() + 63 && playerRect.getX() + 60 > rectangle.getX() && playerRect.getX() + 60 < rectangle.getX() + 60){
                player.getMovement().setGravityStatus(false);
                break;
            }else {
                player.getMovement().setGravityStatus(true);
            }
        }
    }
}

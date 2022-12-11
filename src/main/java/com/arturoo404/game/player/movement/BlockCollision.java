package com.arturoo404.game.player.movement;

import com.arturoo404.game.player.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class BlockCollision extends AnimationTimer {

    private final List<Rectangle> rectangleList;
    private final Player player;

    public BlockCollision(List<Rectangle> rectangleList, Player player) {
        this.rectangleList = rectangleList;
        this.player = player;
    }

    @Override
    public void handle(long now) {
        Rectangle playerRect = player.getRectangle();
        for (Rectangle rectangle : rectangleList){

            if (playerRect.getY() + 60 >= rectangle.getY() && playerRect.getY() + 60 <= rectangle.getY() + 60 && playerRect.getX() >= rectangle.getX() && playerRect.getX() < rectangle.getX() + 60 ||
                    playerRect.getY() + 60 >= rectangle.getY() && playerRect.getY() + 60 <= rectangle.getY() + 60 && playerRect.getX() + 60 > rectangle.getX() && playerRect.getX() + 60 < rectangle.getX() + 60){
                player.getMovement().setGoDown(false);
            }

            if (playerRect.getY() <= rectangle.getY() + 60 && playerRect.getY() >= rectangle.getY() && playerRect.getX() >= rectangle.getX() && playerRect.getX() < rectangle.getX() + 60 ||
                    playerRect.getY() <= rectangle.getY() + 60 && playerRect.getY() >= rectangle.getY() && playerRect.getX() + 60 > rectangle.getX() && playerRect.getX() + 60 < rectangle.getX() + 60){
                player.getMovement().setJumpTimes(0);
            }

            if (playerRect.getX() + 60 == rectangle.getX() && playerRect.getY() >= rectangle.getY() && playerRect.getY() < rectangle.getY() + 60 ||
                    playerRect.getX() + 60 == rectangle.getX() && playerRect.getY() + 60 > rectangle.getY() && playerRect.getY() + 60 < rectangle.getY() + 60){
                player.getMovement().setGoRight(false);
            }else

            if (playerRect.getX() == rectangle.getX() + 60 && playerRect.getY() >= rectangle.getY() && playerRect.getY() < rectangle.getY() + 60 ||
                    playerRect.getX() == rectangle.getX() + 60 && playerRect.getY() + 60 > rectangle.getY() && playerRect.getY() + 60 < rectangle.getY() + 60){
                player.getMovement().setGoLeft(false);
            }
        }
    }
}

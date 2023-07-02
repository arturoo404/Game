package com.arturoo404.game.player.movement;

import com.arturoo404.game.player.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class CollisionTimer extends AnimationTimer {

    private final List<Rectangle> rectangleList;
    private final Player player;

    public CollisionTimer(List<Rectangle> rectangleList, Player player) {
        this.rectangleList = rectangleList;
        this.player = player;
    }

    /**
     * This method is called once per frame.
     * @param now
     *            The timestamp of the current frame given in nanoseconds. This
     *            value will be the same for all {@code AnimationTimers} called
     *            during one frame.
     */
    @Override
    public void handle(long now) {
        Rectangle playerRect = player.getRectangle();
        for (Rectangle rectangle : rectangleList){

            final int block = 120;
            final int width = 80;
            final int height = 120;

            if (playerRect.getY() + height >= rectangle.getY() && playerRect.getY() + height <= rectangle.getY() + block && playerRect.getX() >= rectangle.getX() && playerRect.getX() < rectangle.getX() + block ||
                    playerRect.getY() + height >= rectangle.getY() && playerRect.getY() + height <= rectangle.getY() + block && playerRect.getX() + width > rectangle.getX() && playerRect.getX() + width < rectangle.getX() + block){
                player.getMovement().setGoDown(false);
            }

            if (playerRect.getY() <= rectangle.getY() + block && playerRect.getY() >= rectangle.getY() && playerRect.getX() >= rectangle.getX() && playerRect.getX() < rectangle.getX() + block ||
                    playerRect.getY() <= rectangle.getY() + block && playerRect.getY() >= rectangle.getY() && playerRect.getX() + width > rectangle.getX() && playerRect.getX() + width < rectangle.getX() + block){
                player.getMovement().setGoUp(false);
            }

            if (playerRect.getX() + width == rectangle.getX() && playerRect.getY() >= rectangle.getY() && playerRect.getY() < rectangle.getY() + block ||
                    playerRect.getX() + width == rectangle.getX() && playerRect.getY() + height > rectangle.getY() && playerRect.getY() + height < rectangle.getY() + block){
                player.getMovement().setGoRight(false);
            }

            if (playerRect.getX() == rectangle.getX() + block && playerRect.getY() >= rectangle.getY() && playerRect.getY() < rectangle.getY() + block ||
                    playerRect.getX() == rectangle.getX() + block && playerRect.getY() + height > rectangle.getY() && playerRect.getY() + height < rectangle.getY() + block){
                player.getMovement().setGoLeft(false);
            }
        }
    }
}

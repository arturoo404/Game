package com.arturoo404.game.player.movement;

import com.arturoo404.game.player.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class MapCollision implements Runnable{

    private final List<Rectangle> rectangleList;
    private final Player player;

    public MapCollision(List<Rectangle> rectangleList, Player player) {
        this.rectangleList = rectangleList;
        this.player = player;
    }

    /**
     * This method is used to check if the player is colliding with a wall.
     */
    @Override
    public void run() {
        AnimationTimer animationTimer = new CollisionTimer(rectangleList, player);
        animationTimer.start();
    }

}

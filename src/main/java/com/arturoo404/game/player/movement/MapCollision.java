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

    @Override
    public void run() {
        AnimationTimer blockCollision = new BlockCollision(rectangleList, player);
        blockCollision.start();

        AnimationTimer gravityCollision = new GravityCollision(rectangleList, player);
        gravityCollision.start();
    }

}

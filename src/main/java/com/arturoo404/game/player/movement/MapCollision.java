package com.arturoo404.game.player.movement;

import com.arturoo404.game.player.Player;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.stream.Collectors;

public class MapCollision implements Runnable{

    private final List<Rectangle> rectangleList;
    private final Player player;

    public MapCollision(List<Rectangle> rectangleList, Player player) {
        this.rectangleList = rectangleList;
        this.player = player;
    }

    @Override
    public void run() {
        List<Rectangle2D> list = rectangleList.stream()
                .map(r -> new Rectangle2D(r.getX(), r.getY(), 60, 60))
                .toList();

        AnimationTimer animationTimer = new CollisionTimer(rectangleList, player);
        animationTimer.start();
    }

}

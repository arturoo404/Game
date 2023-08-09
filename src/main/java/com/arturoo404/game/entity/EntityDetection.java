package com.arturoo404.game.entity;

import com.arturoo404.game.entity.wolf.Wolf;
import com.arturoo404.game.player.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class EntityDetection {

    private LivingEntities livingEntities;

    private Player player;

    public void init(){
     detectEntityDetectRange();
    }

    private void detectEntityDetectRange(){
        Thread thread = new Thread(() -> {
            Timeline entityDetection = new Timeline(new KeyFrame(Duration.millis(100), actionEvent -> {
                for (Wolf wolf : livingEntities.getWolves()){
                    wolf.setPlayerDetection(check(wolf));
                }
            }));
            entityDetection.setCycleCount(Animation.INDEFINITE);
            entityDetection.play();
        });

        thread.start();
    }

    private boolean check(Wolf wolf) {
        if (player.getRectangle().getBoundsInParent().intersects(wolf.getCircle().getBoundsInParent())){
            Line line = new Line(wolf.getRectangle().getX() + 55, wolf.getRectangle().getY() + 30, player.getRectangle().getX() + 40, player.getRectangle().getY() + 80);
            player.getAnchorPane().getChildren().add(line);
            return detectBehindWallChecker(line);
        }
        return false;
    }

    private boolean detectBehindWallChecker(Line line){
        for (Rectangle rectangle : player.getMovement().getBlocks()){
            if (rectangle.getBoundsInParent().intersects(line.getBoundsInParent())){
                return false;
            }
        }
        return true;
    }
}

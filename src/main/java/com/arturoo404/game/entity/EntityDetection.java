package com.arturoo404.game.entity;

import com.arturoo404.game.entity.wolf.Wolf;
import com.arturoo404.game.player.Player;
import com.arturoo404.game.player.skills.bullet.BulletAttackObject;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
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
                    wolf.setPlayerDetection(check(player.getRectangle(), wolf.getCircle()));
                }
            }));
            entityDetection.setCycleCount(Animation.INDEFINITE);
            entityDetection.play();
        });

        thread.start();
    }

    private boolean check(Rectangle player, Circle circle) {
        return player.getBoundsInParent().intersects(circle.getBoundsInParent());
    }
}

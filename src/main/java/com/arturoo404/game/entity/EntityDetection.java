package com.arturoo404.game.entity;

import com.arturoo404.game.entity.wolf.Wolf;
import com.arturoo404.game.player.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;
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

    //TODO Clean this ifs
    private void detectEntityDetectRange(){
        Thread thread = new Thread(() -> {
            Timeline entityDetection = new Timeline(new KeyFrame(Duration.millis(100), actionEvent -> {
                for (Wolf wolf : livingEntities.getWolves()){
                    if (check(wolf)){
                        if (wolf.getDetectionStatus().equals(DetectionStatus.IGNORE) || wolf.getDetectionStatus().equals(DetectionStatus.OUT_OF_RANGE)){
                            wolf.setDetectionStatus(DetectionStatus.IN_RANGE);
                            wolf.setDetectionTimer(wolf.getDetectionTimerDefaultValue());
                        }
                    }else {
                        if (wolf.getDetectionStatus().equals(DetectionStatus.IN_RANGE)){
                            wolf.setDetectionStatus(DetectionStatus.OUT_OF_RANGE);
                            wolf.setDetectionTimer(detectionTimerCalc(wolf));
                        }else if (wolf.getDetectionStatus().equals(DetectionStatus.OUT_OF_RANGE)){
                            wolf.setDetectionTimer(detectionTimerCalc(wolf));
                            if (wolf.getDetectionTimer() == 0){
                                wolf.setDetectionStatus(DetectionStatus.IGNORE);
                            }
                        }
                    }

                    if (wolf.getDetectionStatus().equals(DetectionStatus.IN_RANGE) || wolf.getDetectionStatus().equals(DetectionStatus.OUT_OF_RANGE)){
                        moveEntityTowardsPlayer(wolf.getRectangle(), wolf.getCircle(), player.getPlayerShape(), wolf.getSpeed());
                    }
                }
            }));
            entityDetection.setCycleCount(Animation.INDEFINITE);
            entityDetection.play();
        });

        thread.start();
    }

    private int detectionTimerCalc(Wolf wolf){
        return wolf.getDetectionTimer() - 1;
    }

    private boolean check(Wolf wolf) {
        if (isRangeCollision(wolf.getCircle(), player.getEntityHitBox())){
            Line line = new Line(wolf.getRectangle().getX() + 55, wolf.getRectangle().getY() + 30, player.getEntityHitBox().getX() + 20, player.getEntityHitBox().getY() + 30);
            player.getAnchorPane().getChildren().add(line);
            return detectBehindWallChecker(line);
        }
        return false;
    }

    private boolean isRangeCollision(Circle circle, Rectangle square) {
        double closestX = Math.max(square.getX(), Math.min(circle.getCenterX(), square.getX() + square.getWidth()));
        double closestY = Math.max(square.getY(), Math.min(circle.getCenterY(), square.getY() + square.getHeight()));

        double distanceX = circle.getCenterX() - closestX;
        double distanceY = circle.getCenterY() - closestY;
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        return distance <= circle.getRadius();
    }


    private void moveEntityTowardsPlayer(Rectangle entity, Circle entityRange, Rectangle player, double speed) {
        double dx = player.getX() - entity.getX();
        double dy = player.getY() - entity.getY();

        if (Math.abs(dx) > Math.abs(dy)) {
            entity.setX(entity.getX() + Math.signum(dx) * speed);
            entityRange.setCenterX(entity.getX() + entity.getWidth() / 2);
        } else {
            entity.setY(entity.getY() + Math.signum(dy) * speed);
            entityRange.setCenterY(entity.getY() + entity.getHeight() / 2);
        }
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

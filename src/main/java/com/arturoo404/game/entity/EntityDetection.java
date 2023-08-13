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
                        moveEntityTowardsPlayer(wolf, wolf.getCircle(), player.getPlayerShape(), wolf.getSpeed());
                        wolf.getEntityMovementAnimation().setPlay(true);
                    }else {
                        wolf.getEntityMovementAnimation().setPlay(false);
                    }

                    System.out.println(wolf.getRectangle().getWidth() + " " + wolf.getRectangle().getHeight());
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


    private void moveEntityTowardsPlayer(Wolf entity, Circle entityRange, Rectangle player, double speed) {
        double dx = player.getX() - entity.getRectangle().getX();
        double dy = player.getY() - entity.getRectangle().getY();

        if (Math.abs(dx) > Math.abs(dy)) {
            final double signum = Math.signum(dx);
            if (signum > 0){
                entity.getRectangle().setX(entity.getRectangle().getX() + signum * speed);
                entityRange.setCenterX(entity.getRectangle().getX() + entity.getWidth() / 2);
                entity.setDirection(EntityDirection.RIGHT);
            }else {
                entity.getRectangle().setX(entity.getRectangle().getX() + signum * speed);
                entityRange.setCenterX(entity.getRectangle().getX() + entity.getWidth() / 2);
                entity.setDirection(EntityDirection.LEFT);
            }
            if (!entity.getDirection().equals(EntityDirection.RIGHT) && !entity.getDirection().equals(EntityDirection.LEFT)){
                switchEntitySize(entity);
            }
        } else {
            final double signum = Math.signum(dy);
            if (signum > 0){
                entity.getRectangle().setY(entity.getRectangle().getY() + signum * speed);
                entityRange.setCenterY(entity.getRectangle().getY() + entity.getHeight() / 2);
                entity.setDirection(EntityDirection.DOWN);
            }else {
                entity.getRectangle().setY(entity.getRectangle().getY() + signum * speed);
                entityRange.setCenterY(entity.getRectangle().getY() + entity.getHeight() / 2);
                entity.setDirection(EntityDirection.UP);
            }
            if (!entity.getDirection().equals(EntityDirection.DOWN) && !entity.getDirection().equals(EntityDirection.UP)){
                switchEntitySize(entity);
            }
        }
    }

    private synchronized void switchEntitySize(Entity entity){
        double width = entity.getRectangle().getWidth(), height = entity.getRectangle().getHeight();
        entity.getRectangle().setWidth(height);
        entity.getRectangle().setHeight(width);
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

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

import java.util.Comparator;
import java.util.Random;
import java.util.stream.Stream;


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
        if (entity.getAiValue().getRepeating() == 0 || entity.getAiValue().getRepeating() == 4){
            calculateCloseAttackPoint(entity);
            entity.getAiValue().setRepeating(0);
        }

        double dx = entity.getAiValue().getDx();
        double dy = entity.getAiValue().getDy();

        if (entity.getAiValue().isXMoveRepeating()) {
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
        } else if (entity.getAiValue().isYMoveRepeating()){
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

        entity.getAiValue().setRepeating(entity.getAiValue().getRepeating() + 1);
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

    private void calculateCloseAttackPoint(Entity entity){
        EntityPointValue integers = Stream.of(
                calculateValue(entity, 0, -60),
                calculateValue(entity, 0, 140),
                calculateValue(entity, -120, 40),
                calculateValue(entity, 100, 40)
        ).min(Comparator.comparingDouble(EntityPointValue::getValue)).orElseThrow();

        entity.getAiValue().setDx(integers.getDx());
        entity.getAiValue().setDy(integers.getDy());
        entity.getAiValue().setSignumY(integers.getYMove());
        entity.getAiValue().setSignumX(integers.getXMove());
        calculateChanceToMove(entity);
    }

    private EntityPointValue calculateValue(Entity entity, double x, double y){
        double dx = player.getPlayerShape().getX() + x - entity.getRectangle().getX();
        double dy = player.getPlayerShape().getY() + y - entity.getRectangle().getY();

        return new EntityPointValue(
                (int) dx, (int) dy, (Math.abs(dx) + Math.abs(dy)), (int) x, (int) y
        );
    }

    private void calculateChanceToMove(Entity entity){
        if (randomNumber(entity) < Math.abs(entity.getAiValue().getDx()) || randomNumber(entity) > Math.abs(entity.getAiValue().getDy())){
            entity.getAiValue().setYMoveRepeating(false);
            entity.getAiValue().setXMoveRepeating(true);
        }else {
            entity.getAiValue().setXMoveRepeating(false);
            entity.getAiValue().setYMoveRepeating(true);
        }

        if (entity.getAiValue().getDx() < 35 && entity.getAiValue().getDx() > -35 && entity.getAiValue().getDy() < 35 && entity.getAiValue().getDy() > -35){
            entity.getAiValue().setXMoveRepeating(false);
            entity.getAiValue().setYMoveRepeating(false);
        } else if (entity.getAiValue().getDx() < 35 && entity.getAiValue().getDx() > -35) {
            entity.getAiValue().setXMoveRepeating(false);
            entity.getAiValue().setYMoveRepeating(true);
        }else if (entity.getAiValue().getDy() < 35 && entity.getAiValue().getDy() > -35){
            entity.getAiValue().setYMoveRepeating(false);
            entity.getAiValue().setXMoveRepeating(true);
        }

    }

    private int randomNumber(Entity entity){
        Random random = new Random();
        return random.nextInt(Math.abs(entity.getAiValue().getDx()) + Math.abs(entity.getAiValue().getDy()) + 1);
    }
}

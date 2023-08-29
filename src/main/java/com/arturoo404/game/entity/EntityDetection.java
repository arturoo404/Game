package com.arturoo404.game.entity;

import com.arturoo404.game.entity.wolf.Wolf;
import com.arturoo404.game.entity.wolf.skills.WolfAttack;
import com.arturoo404.game.player.Player;
import com.arturoo404.game.player.movement.MapCollision;
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
                        moveEntityTowardsPlayer(wolf, wolf.getCircle(), wolf.getSpeed());
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


    private void moveEntityTowardsPlayer(Wolf entity, Circle entityRange, double speed) {
        if (entity.getAiValue().getRepeating() == 0 || entity.getAiValue().getRepeating() == 4){
            calculateCloseAttackPoint(entity);
            entity.getAiValue().setRepeating(0);
        }

        if (entity.getAiValue().isXMoveRepeating() && !entity.isAttackAnimation()) {
            if (isCollisionWithWall(entity)){
                entity.getAiValue().setSignumX(entity.getAiValue().getSignumX() * -1);
            }

            if (entity.getAiValue().getSignumX() > 0){
                entity.getRectangle().setX(entity.getRectangle().getX() + entity.getAiValue().getSignumX() * speed);
                entityRange.setCenterX(entity.getRectangle().getX() + entity.getWidth() / 2);
                entity.setDirection(EntityDirection.RIGHT);
            }else {
                entity.getRectangle().setX(entity.getRectangle().getX() + entity.getAiValue().getSignumX() * speed);
                entityRange.setCenterX(entity.getRectangle().getX() + entity.getWidth() / 2);
                entity.setDirection(EntityDirection.LEFT);
            }

            if (!entity.getDirection().equals(EntityDirection.RIGHT) && !entity.getDirection().equals(EntityDirection.LEFT)){
                if (isNotCollisionWithWallSwitchEntity(entity)){
                    switchEntitySize(entity);
                }
            }
        } else if (entity.getAiValue().isYMoveRepeating() && !entity.isAttackAnimation()){
            if (isCollisionWithWall(entity)){
                entity.getAiValue().setSignumY(entity.getAiValue().getSignumY() * -1);
            }

            if (entity.getAiValue().getSignumY() > 0){
                entity.getRectangle().setY(entity.getRectangle().getY() + entity.getAiValue().getSignumY() * speed);
                entityRange.setCenterY(entity.getRectangle().getY() + entity.getHeight() / 2);
                entity.setDirection(EntityDirection.DOWN);
            }else {
                entity.getRectangle().setY(entity.getRectangle().getY() + entity.getAiValue().getSignumY() * speed);
                entityRange.setCenterY(entity.getRectangle().getY() + entity.getHeight() / 2);
                entity.setDirection(EntityDirection.UP);
            }

            if (!entity.getDirection().equals(EntityDirection.DOWN) && !entity.getDirection().equals(EntityDirection.UP)){
                if (isNotCollisionWithWallSwitchEntity(entity)){
                    switchEntitySize(entity);
                }
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
                calculateValue(entity, 0, -60, EntityDirection.UP),
                calculateValue(entity, 0, 140, EntityDirection.DOWN),
                calculateValue(entity, -120, 40, EntityDirection.LEFT),
                calculateValue(entity, 100, 40, EntityDirection.RIGHT)
        ).min(Comparator.comparingDouble(EntityPointValue::getValue)).orElseThrow();

        entity.getAiValue().setDx(integers.getDx());
        entity.getAiValue().setDy(integers.getDy());
        entity.getAiValue().setSignumY(integers.getDy());
        entity.getAiValue().setSignumX(integers.getDx());
        entity.getAiValue().setDetectionDirection(integers.getEntityDirection());
        calculateChanceToMove(entity);
    }

    private EntityPointValue calculateValue(Entity entity, double x, double y, EntityDirection entityDirection){
        double dx = player.getPlayerShape().getX() + x - entity.getRectangle().getX();
        double dy = player.getPlayerShape().getY() + y - entity.getRectangle().getY();

        return new EntityPointValue(
                (int) dx, (int) dy, (Math.abs(dx) + Math.abs(dy)), (int) x, (int) y, entityDirection
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
            entity.setAttack(true);
            if (entity instanceof Wolf && !entity.isAttackAnimation()){
                System.out.println("a");
                entity.setAttackAnimation(true);
                Thread thread = new Thread(new WolfAttack(entity, player));
                thread.start();
            }
            return;
        } else if (entity.getAiValue().getDx() < 35 && entity.getAiValue().getDx() > -35) {
            entity.getAiValue().setXMoveRepeating(false);
            entity.getAiValue().setYMoveRepeating(true);
        }else if (entity.getAiValue().getDy() < 35 && entity.getAiValue().getDy() > -35){
            entity.getAiValue().setYMoveRepeating(false);
            entity.getAiValue().setXMoveRepeating(true);
        }

        entity.setAttack(false);
    }

    private int randomNumber(Entity entity){
        Random random = new Random();
        return random.nextInt(Math.abs(entity.getAiValue().getDx()) + Math.abs(entity.getAiValue().getDy()) + 1);
    }

    private boolean isNotCollisionWithWallSwitchEntity(Entity entity){
        for (Rectangle rectangle : player.getMovement().getBlocks()){
            if (checkCollisionSwitch(rectangle, entity.getRectangle())){
                return false;
            }
        }
        return true;
    }

    private boolean isCollisionWithWall(Entity entity){
        for (Rectangle rectangle : player.getMovement().getBlocks()){
            if (checkCollision(rectangle, entity.getRectangle(), 12 * entity.getAiValue().getSignumX(), 12 * entity.getAiValue().getSignumY())){
                return true;
            }
        }
        return false;
    }

    private boolean checkCollisionSwitch(Rectangle rect1, Rectangle rect2) {
        rect2 = new Rectangle(rect2.getY(), rect2.getX(), rect2.getHeight() + 10, rect2.getWidth() + 10);
        return rect1.getBoundsInParent().intersects(rect2.getBoundsInParent());
    }

    private boolean checkCollision(Rectangle rect1, Rectangle rect2, double dx, double dy) {
        rect2 = new Rectangle(rect2.getX() + dx, rect2.getY() + dy, rect2.getWidth(), rect2.getHeight());
        return rect1.getBoundsInParent().intersects(rect2.getBoundsInParent());
    }
}

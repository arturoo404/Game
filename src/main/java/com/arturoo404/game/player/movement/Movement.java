package com.arturoo404.game.player.movement;

import com.arturoo404.game.player.Player;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Movement {

    private final BooleanProperty jump = new SimpleBooleanProperty();
    private final BooleanProperty  goDown = new SimpleBooleanProperty();
    private final BooleanProperty  goRight = new SimpleBooleanProperty();
    private final BooleanProperty goLeft = new SimpleBooleanProperty();
    private final BooleanBinding keyPress = jump.or(goDown).or(goLeft).or(goRight);
    private final BooleanProperty gravityStatus = new SimpleBooleanProperty();
    private final BooleanBinding gravity = gravityStatus.or(gravityStatus);
    private boolean jumpProgres;
    private final Player player;
    private final List<Rectangle> rectangleList;
    private int jumpTimes = 0;
    private int jumpClick = 0;

    public Movement(Player player, List<Rectangle> rectangleList) {
        this.player = player;
        this.rectangleList = rectangleList;
    }

    public void init(){
        Thread blockCollision = new Thread(new MapCollision(rectangleList, player));
        blockCollision.start();
        keyPress.addListener(((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                movementTimer.start();
            } else {
                movementTimer.stop();
            }
        }));

        gravity.addListener(((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                gravTimer.start();
            } else {
                gravTimer.stop();
            }
        }));
    }

    private void move(){
        if (jump.get() && jumpTimes == 0 && jumpClick == 0&& !gravityStatus.get()){
            jumpChecker();
        } else if (goDown.get()) {
            //player.getRectangle().setY(player.getRectangle().getY() + 5);
        }else if (goLeft.get()) {
            player.getRectangle().setX(player.getRectangle().getX() - 4);
        }else if (goRight.get()) {
            player.getRectangle().setX(player.getRectangle().getX() + 4);
        }
    }

    private void jumpChecker(){
        jumpTimer.start();
        jumpTimes = 50;
        jumpClick = 1;
        jumpProgres = true;
        gravityStatus.set(false);
    }

    private void jumpAnimation(){
        if (jumpTimes == 0){
            jumpTimer.stop();
            jumpProgres = false;
            gravityStatus.set(true);
            jumpClick = 0;
            return;
        }

        player.getRectangle().setY(player.getRectangle().getY() - 3);
        jumpTimes--;
    }

    AnimationTimer jumpTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            jumpAnimation();
        }
    };

    AnimationTimer movementTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            move();
        }
    };

    private void grav(){
        player.getRectangle().setY(player.getRectangle().getY() + 3);
    }

    AnimationTimer gravTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            grav();
        }
    };

    public void setJump(boolean jump) {
        this.jump.set(jump);
    }

    public void setGoDown(boolean goDown) {
        this.goDown.set(goDown);
    }

    public void setGoRight(boolean goRight) {
        this.goRight.set(goRight);
    }

    public void setGoLeft(boolean goLeft) {
        this.goLeft.set(goLeft);
    }

    public void setGravityStatus(boolean gravityStatus) {
        this.gravityStatus.set(gravityStatus);
    }

    public boolean isJumpProgres() {
        return jumpProgres;
    }

    public void setJumpTimes(int jumpTimes) {
        this.jumpTimes = jumpTimes;
    }
}

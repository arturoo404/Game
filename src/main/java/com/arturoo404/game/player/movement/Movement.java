package com.arturoo404.game.player.movement;

import com.arturoo404.game.player.Player;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Movement {

    private final BooleanProperty goUp = new SimpleBooleanProperty();
    private final BooleanProperty  goDown = new SimpleBooleanProperty();
    private final BooleanProperty  goRight = new SimpleBooleanProperty();
    private final BooleanProperty goLeft = new SimpleBooleanProperty();
    private final BooleanBinding keyPress = goUp.or(goDown).or(goLeft).or(goRight);
    private final BooleanProperty gravityStatus = new SimpleBooleanProperty();
    private final BooleanBinding gravity = gravityStatus.or(gravityStatus);
    private final Player player;
    private final List<Rectangle> rectangleList;

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
        if (goUp.get()){
            player.getRectangle().setY(player.getRectangle().getY() - 5);
        } else if (goDown.get()) {
            //player.getRectangle().setY(player.getRectangle().getY() + 5);
        }else if (goLeft.get()) {
            player.getRectangle().setX(player.getRectangle().getX() - 5);
        }else if (goRight.get()) {
            player.getRectangle().setX(player.getRectangle().getX() + 5);
        }
    }

    AnimationTimer movementTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            move();
        }
    };

    private void grav(){
        player.getRectangle().setY(player.getRectangle().getY() + 2);
    }

    AnimationTimer gravTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            grav();
        }
    };

    public void setGoUp(boolean goUp) {
        this.goUp.set(goUp);
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
}

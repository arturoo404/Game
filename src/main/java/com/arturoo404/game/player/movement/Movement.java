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
    private final Player player;
    private final List<Rectangle> rectangleList;
    private final MovementAnimation movementAnimation;

    public Movement(Player player, List<Rectangle> rectangleList) {
        this.player = player;
        this.rectangleList = rectangleList;
        movementAnimation = new MovementAnimation(player.getRectangle());
    }

    public void init(){
        Thread thread = new Thread(new MapCollision(rectangleList, player));
        thread.start();
        movementAnimation.init();
        keyPress.addListener(((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                animationTimer.start();
                movementAnimation.setPlay(true);
            } else {
                animationTimer.stop();
                movementAnimation.setPlay(false);
            }
        }));
    }

    private void move(){
        if (goUp.get()){
            player.getRectangle().setY(player.getRectangle().getY() - 2);
            movementAnimation.setKey("W");
        } else if (goDown.get()) {
            player.getRectangle().setY(player.getRectangle().getY() + 2);
            movementAnimation.setKey("S");
        }else if (goLeft.get()) {
            player.getRectangle().setX(player.getRectangle().getX() - 2);
            movementAnimation.setKey("A");
        }else if (goRight.get()) {
            player.getRectangle().setX(player.getRectangle().getX() + 2);
            movementAnimation.setKey("D");
        }
    }

    AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            move();
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
}

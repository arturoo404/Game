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

    private boolean down;
    private boolean up;
    private boolean right;
    private boolean left;
    private final Player player;

    private final List<Rectangle> rectangleList;

    public Movement(Player player, List<Rectangle> rectangleList) {
        this.player = player;
        this.rectangleList = rectangleList;
    }

    public void init(){
        Thread thread = new Thread(new MapCollision(rectangleList, player));
        thread.start();
        keyPress.addListener(((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                animationTimer.start();
            } else {
                animationTimer.stop();
            }
        }));
    }

    private void move(){
        if (goUp.get() && up){
            player.getRectangle().setY(player.getRectangle().getY() - 5);
        } else if (goDown.get() && down) {
            player.getRectangle().setY(player.getRectangle().getY() + 5);
        }else if (goLeft.get() && left) {
            player.getRectangle().setX(player.getRectangle().getX() - 5);
        }else if (goRight.get() && right) {
            player.getRectangle().setX(player.getRectangle().getX() + 5);
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

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
}

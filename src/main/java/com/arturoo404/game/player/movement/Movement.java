package com.arturoo404.game.player.movement;

import com.arturoo404.game.player.Player;
import com.arturoo404.game.player.PlayerBars;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyCode;
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
    private final CameraMovement cameraMovement;
    private PlayerBars playerBars;

    public Movement(Player player, List<Rectangle> rectangleList) {
        this.player = player;
        this.rectangleList = rectangleList;
        movementAnimation = new MovementAnimation(player.getRectangle());
        cameraMovement = new CameraMovement(player.getRectangle());
    }
    /**
     * This method is used to initialize the movement of the player.
     */
    public void init(){
        Thread thread = new Thread(new MapCollision(rectangleList, player));
        thread.start();
        movementAnimation.init();
        playerBars = new PlayerBars(player);
        playerBars.init();
        player.setDirection(KeyCode.S);

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

    /**
     * This method is used to move the player.
     */
    private void move(){
        if (goUp.get()){
            player.getRectangle().setY(player.getRectangle().getY() - 2);
            movementAnimation.setKey(KeyCode.W);
            player.setDirection(KeyCode.W);
            cameraMovement.moveCamera(KeyCode.W);
            playerBars.getPlayerHpBar().setLayoutY(player.getRectangle().getY() - 14);
            playerBars.getPlayerHpBar().setLayoutX(player.getRectangle().getX() - 15);
        } else if (goDown.get()) {
            player.getRectangle().setY(player.getRectangle().getY() + 2);
            movementAnimation.setKey(KeyCode.S);
            player.setDirection(KeyCode.S);
            cameraMovement.moveCamera(KeyCode.S);
            playerBars.getPlayerHpBar().setLayoutY(player.getRectangle().getY() - 14);
            playerBars.getPlayerHpBar().setLayoutX(player.getRectangle().getX() - 15);
        }else if (goLeft.get()) {
            player.getRectangle().setX(player.getRectangle().getX() - 2);
            movementAnimation.setKey(KeyCode.A);
            player.setDirection(KeyCode.A);
            cameraMovement.moveCamera(KeyCode.A);
            playerBars.getPlayerHpBar().setLayoutY(player.getRectangle().getY() - 14);
            playerBars.getPlayerHpBar().setLayoutX(player.getRectangle().getX() - 15);
        }else if (goRight.get()) {
            player.getRectangle().setX(player.getRectangle().getX() + 2);
            movementAnimation.setKey(KeyCode.D);
            player.setDirection(KeyCode.D);
            cameraMovement.moveCamera(KeyCode.D);
            playerBars.getPlayerHpBar().setLayoutY(player.getRectangle().getY() - 14);
            playerBars.getPlayerHpBar().setLayoutX(player.getRectangle().getX() - 15);
        }
    }

    /**
     * This method is used to start the movement of the player.
     */
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

    public MovementAnimation getMovementAnimation() {
        return movementAnimation;
    }

    public List<Rectangle> getRectangleList() {
        return rectangleList;
    }
}

package com.arturoo404.game.player.movement;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MovementAnimation {

    private boolean play;
    private KeyCode key;
    private final Rectangle player;
    private final Image image;
    private int animationCount = 1;

    /**
     * This method is used to initialize the movement animation.
     */
    public void init(){
        key = KeyCode.S;
        Thread thread = new Thread(() -> {
            Timeline playerAnimation = new Timeline(new KeyFrame(Duration.millis(100), actionEvent -> {
                if (play){
                    player.setFill(new ImagePattern(playerTexturePlay()));
                }else {
                    animationCount = 1;
                    player.setFill(new ImagePattern(playerTextureStop()));
                }
            }));
            playerAnimation.setCycleCount(Animation.INDEFINITE);
            playerAnimation.play();
        });

        thread.start();
    }

    /**
     * This method is used to change the texture of the player.
     * @return
     */
    private WritableImage playerTexturePlay(){
        int x = 160 + (animationCount * 80);

        if (animationCount == 3){
            animationCount = 1;
        }else {
            animationCount++;
        }

        return switch (key) {
            case S -> writableImage(x, 481);
            case A -> writableImage(x, 601);
            case D -> writableImage(x, 721);
            case W -> writableImage(x, 841);
            default -> writableImage(320, 481);
        };
    }

    private WritableImage playerTextureStop(){
        return switch (key) {
            case A -> writableImage(320, 601);
            case D -> writableImage(320, 721);
            case W -> writableImage(320, 841);
            default -> writableImage(320, 481);
        };
    }

    /**
     * This method is used to create entity_location writable image.
     * @param x
     * @param y
     * @return
     */
    private WritableImage writableImage(int x, int y){
        PixelReader reader = image.getPixelReader();
        return new WritableImage(reader, x, y, 80, 119);
    }

    /**
     * This method is used to initialize the movement animation.
     * @param player
     */
    public MovementAnimation(Rectangle player) {
        this.player = player;
        image = new Image(getClass().getResourceAsStream("/txt/player/player.png"));
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public void setKey(KeyCode key) {
        this.key = key;
    }

    public KeyCode getKey() {
        return key;
    }
}

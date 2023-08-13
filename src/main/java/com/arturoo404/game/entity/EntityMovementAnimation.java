package com.arturoo404.game.entity;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class EntityMovementAnimation {
    private boolean play;
    private final Entity entity;
    private final Image image;
    private int animationCount;
    private int imageWidth;
    private int imageHeight;
    private int imagePositionX;
    private int imagePositionY;
    private int pixelReaderMoveX;
    private int pixelReaderMoveY;

    //TODO Fix wolf txt
    public void init(){
        Thread thread = new Thread(() -> {
            Timeline playerAnimation = new Timeline(new KeyFrame(Duration.millis(100), actionEvent -> {
                if (play){
                    entity.getRectangle().setFill(new ImagePattern(entityTexturePlay()));
                }else {
                    animationCount = 1;
                    entity.getRectangle().setFill(new ImagePattern(writableImage(imagePositionX, directionYValue())));
                }
            }));
            playerAnimation.setCycleCount(Animation.INDEFINITE);
            playerAnimation.play();
        });

        thread.start();
    }

    private WritableImage entityTexturePlay(){
        int x;
        switch (animationCount) {
            case 2, 3 -> x = imagePositionX + ((animationCount - 1) * pixelReaderMoveX);
            default -> x = imagePositionX;
        }

        if (animationCount == 3){
            animationCount = 1;
        }else {
            animationCount++;
        }

        return writableImage(x, directionYValue());
    }

    private int directionYValue(){
        switch (entity.getDirection()) {
            case LEFT -> {
                return imagePositionY + pixelReaderMoveY;
            }
            case RIGHT -> {
                return imagePositionY + (pixelReaderMoveY * 2);
            }
            case UP -> {
                return imagePositionY + (pixelReaderMoveY * 3);
            }
            default -> {
                return imagePositionY;
            }
        }
    }

    private WritableImage writableImage(int x, int y){
        PixelReader reader = image.getPixelReader();
        return new WritableImage(reader, x, y, imageWidth, imageHeight);
    }


}

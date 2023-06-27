package com.arturoo404.game.player.skills.animation;

import com.arturoo404.game.player.skills.bullet.BasicAttack;
import com.arturoo404.game.player.skills.bullet.BulletAttackObject;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class BasicAttackSkillsAnimationController {
    private final BasicAttack basicAttack;
    private final Image image;
    public void initBasicAttackAnimation(){
        Thread thread = new Thread(() -> {
            Timeline playerAnimation = new Timeline(new KeyFrame(Duration.millis(100), actionEvent -> {
                synchronized (basicAttack){
                    basicAttack.getBasickAttackList().forEach(f -> f.getSkill().setFill(new ImagePattern(bulletImage(f))));
                }
            }));
            playerAnimation.setCycleCount(Animation.INDEFINITE);
            playerAnimation.play();
        });
        thread.start();
    }

    public BasicAttackSkillsAnimationController(BasicAttack basicAttack) {
        image = new Image(getClass().getResourceAsStream("/txt/skills/basicAttack.png"));
        this.basicAttack = basicAttack;
    }

    private synchronized WritableImage bulletImage(BulletAttackObject bulletAttackObject){
        int x, y,  width, height;
        if (bulletAttackObject.getKeyCode().equals(KeyCode.S)){
            if (bulletAttackObject.getAnimationFrame() == 0){
                x = 75;
            }else {
                x = 75 + bulletAttackObject.getAnimationFrame() * 193;
            }
            y = 24; width = 65; height = 127;
        } else if (bulletAttackObject.getKeyCode().equals(KeyCode.W)) {
            if (bulletAttackObject.getAnimationFrame() == 0){
                x = 75;
            }else {
                x = 75 + bulletAttackObject.getAnimationFrame() * 193;
            }
            y = 620; width = 65; height = 127;
        } else if (bulletAttackObject.getKeyCode().equals(KeyCode.A)) {
            if (bulletAttackObject.getAnimationFrame() == 0){
                x = 30;
            }else {
                x = 30 + bulletAttackObject.getAnimationFrame() * 193;
            }
            y = 260; width = 130; height = 65;
        } else {
            if (bulletAttackObject.getAnimationFrame() == 0){
                x = 20;
            }else {
                x = 20 + bulletAttackObject.getAnimationFrame() * 193;
            }
            y = 453; width = 130; height = 65;
        }

        if (bulletAttackObject.getAnimationFrame() == 2){
            bulletAttackObject.setAnimationFrame(0);
        }else {
            bulletAttackObject.setAnimationFrame(bulletAttackObject.getAnimationFrame() + 1);
        }

        return writableImage(x, y,  width, height);
    }

    private WritableImage writableImage(int x, int y, int width, int height){
        PixelReader reader = image.getPixelReader();
        return new WritableImage(reader, x, y, width, height);
    }

}

package com.arturoo404.game.player.skills.collision;

import com.arturoo404.game.player.skills.bullet.BasicAttack;
import com.arturoo404.game.player.skills.bullet.BulletAttackObject;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class SkillsMapCollision {

    private final List<Rectangle> mapBlock;

    private final BasicAttack basicAttack;
    public SkillsMapCollision(List<Rectangle> mapBlock, BasicAttack basicAttack) {
        this.basicAttack =  basicAttack;
        this.mapBlock = mapBlock;
    }

    /**
     * This method is used to initialize the basic attack collision detection.
     */
    public void init(){
        Thread thread = new Thread(() -> {
            Timeline collision = new Timeline(new KeyFrame(Duration.millis(20), actionEvent -> {
                List<BulletAttackObject> bulletAttackObjects = basicAttack.getBasickAttackList();
                List<BulletAttackObject> toDelete = new ArrayList<>();

                for (BulletAttackObject bullet : bulletAttackObjects){
                    for (Rectangle block : mapBlock) {
                        if (check(block, bullet)){
                            toDelete.add(bullet);
                        }
                    }
                }
                basicAttack.update(toDelete);
            }));
            collision.setCycleCount(Animation.INDEFINITE);
            collision.play();
        });
        thread.start();
    }

    /**
     * This method is used to check if the bullet is colliding with the map.
     * @param mapBlock
     * @param bulletAttackObject
     * @return
     */
    private boolean check(Rectangle mapBlock, BulletAttackObject bulletAttackObject) {
        return mapBlock.getBoundsInParent().intersects(bulletAttackObject.getSkill().getBoundsInParent());
    }
}

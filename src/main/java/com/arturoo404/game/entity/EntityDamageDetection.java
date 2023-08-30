package com.arturoo404.game.entity;

import com.arturoo404.game.player.Player;
import com.arturoo404.game.player.skills.bullet.BulletAttackObject;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.arturoo404.game.utils.StatsUtils.calcDamageAfterReduction;

@AllArgsConstructor
public class EntityDamageDetection {

    private LivingEntities livingEntities;

    private Player player;

    public void init(){
        detectEntityReceivedDamage();
    }

    private void detectEntityReceivedDamage(){
        Thread thread = new Thread(() -> {
            Timeline entityDetection = new Timeline(new KeyFrame(Duration.millis(50), actionEvent -> {
                for (Entity entity : livingEntities.getWolves()){
                    detectHitBySkill(entity);
                }
            }));
            entityDetection.setCycleCount(Animation.INDEFINITE);
            entityDetection.play();
        });

        thread.start();
    }

    private void detectHitBySkill(Entity entity){
        if (!player.getSkillsController().getBasic().getBasickAttackList().isEmpty()){
            List<BulletAttackObject> toDelete = new ArrayList<>();
            for (BulletAttackObject bulletAttackObject : player.getSkillsController().getBasic().getBasickAttackList()){
                if (checkHit(entity, bulletAttackObject)){
                    toDelete.add(bulletAttackObject);
                    entity.setCurrentHealth(entity.getCurrentHealth() - calcDamageAfterReduction(player.getSkillStats().getDamage(), entity.getArmor()));
                    if (entity.getCurrentHealth() <= 0){
                        System.out.println("AA");
                    }
                }
            }
            player.getSkillsController().getBasic().update(toDelete);
        }
    }

    private boolean checkHit(Entity entity, BulletAttackObject bullet){
        return entity.getRectangle().getBoundsInParent().intersects(bullet.getSkill().getBoundsInParent());
    }
}

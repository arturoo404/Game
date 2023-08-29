package com.arturoo404.game.entity.wolf.skills;

import com.arturoo404.game.entity.Entity;
import com.arturoo404.game.player.Player;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import lombok.AllArgsConstructor;

import static com.arturoo404.game.utils.StatsUtils.calcDamageAfterReduction;

@AllArgsConstructor
public class WolfAttack implements Runnable{

    private Entity entity;

    private Player player;

    @Override
    public void run() {
        TranslateTransition forwardTransition = new TranslateTransition(Duration.millis(entity.calculateAttackSpeedTime()),  entity.getRectangle());
        TranslateTransition backwardTransition = new TranslateTransition(Duration.millis(entity.calculateAttackSpeedTime()),  entity.getRectangle());
        calculateAttackDirection(forwardTransition, backwardTransition);
        forwardTransition.setOnFinished(v ->{
            if (checkWolfCollision()){
                player.getSkillStats().setCurrentHealth(
                        player.getSkillStats().getCurrentHealth() - (calcDamageAfterReduction(entity.getDamage(), player.getSkillStats().getArmor()))
                );
            }
        });

        SequentialTransition sequence = new SequentialTransition(forwardTransition, backwardTransition);

        sequence.setAutoReverse(true);
        sequence.play();
        sequence.setOnFinished(f -> entity.setAttackAnimation(false));
    }

    private boolean checkWolfCollision(){
        return entity.getRectangle().getBoundsInParent().intersects(player.getPlayerShape().getBoundsInParent());
    }

    private void calculateAttackDirection(TranslateTransition forwardTransition, TranslateTransition backwardTransition){
        switch (entity.getAiValue().getDetectionDirection()){
            case DOWN -> {
                forwardTransition.setToY(-75);
                backwardTransition.setToY(0);
            }
            case UP -> {
                forwardTransition.setToY(75);
                backwardTransition.setToY(0);
            }
            case LEFT -> {
                forwardTransition.setToX(75);
                backwardTransition.setToX(0);
            }
            case RIGHT -> {
                forwardTransition.setToX(-75);
                backwardTransition.setToX(0);
            }
        }
    }
}

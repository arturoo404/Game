package com.arturoo404.game.player.skills;

import com.arturoo404.game.player.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class HealAbility {

    private Player player;
    private boolean start;
    private boolean healCd;
    private int healCdLenght = 4000;


    public HealAbility(Player player) {
        this.player = player;
    }

    public void init() {
        healCd = true;
        Timeline healAbility = new Timeline(new KeyFrame(Duration.millis(50), actionEvent -> {
            if (start && healCd){
                heal();
                countCd();
            } else if (start && !healCd){
                System.out.println("skill is on cd");
            }
        }));
        healAbility.setCycleCount(Animation.INDEFINITE);
        healAbility.play();
    }


    private void heal(){
        player.getSkillStats().setCurrentHealth(player.getSkillStats().getCurrentHealth() + 50);
        healCd = false;
    }
    private void countCd(){
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(healCdLenght);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            healCd = true;
        });
        thread.start();
    }


    public void setStart(boolean start) {
        this.start = start;
    }
}

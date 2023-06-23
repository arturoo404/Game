package com.arturoo404.game.player.skills.bullet;


import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class BulletSkillStatsController {

    private final BasicAttack basicAttack;
    public void countBasicAttackStack(){
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep((int) (1000 / basicAttack.getPlayer().getSkillStats().getAttackSpeed()));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            basicAttack.setStack(true);
        });

        thread.start();
    }

    public BulletSkillStatsController(BasicAttack basicAttack) {
        this.basicAttack = basicAttack;
    }
}

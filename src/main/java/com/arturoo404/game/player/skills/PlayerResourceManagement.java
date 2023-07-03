package com.arturoo404.game.player.skills;

import com.arturoo404.game.player.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class PlayerResourceManagement {
    private final Player player;
    public PlayerResourceManagement(Player player) {
        this.player = player;
    }

    public void init() {
        resourceRegainTimer();
    }

    private void resourceRegainTimer() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    // Regeneracja zdrowia gracza
                    if (player.getSkillStats().getCurrentHealth() < player.getSkillStats().getMaxHealth()) {
                        double healthRegain = player.getSkillStats().getCurrentHealth() + player.getSkillStats().getHealthRegen() + player.getSkillStats().getPercentHealthRegen();
                        player.getSkillStats().setCurrentHealth((int) healthRegain);
                        player.getMovement().getPlayerBars().getPlayerHpBar().setProgress((double) player.getSkillStats().getCurrentHealth() / player.getSkillStats().getMaxHealth());
                    } else {
                        player.getSkillStats().setCurrentHealth(player.getSkillStats().getMaxHealth());
                    }
                    // Regeneracja many gracza
                    if (player.getSkillStats().getCurrentMana() < player.getSkillStats().getMaxMana()) {
                        double manaRegain = player.getSkillStats().getCurrentMana() + player.getSkillStats().getManaRegen() + player.getSkillStats().getPercentManaRegen();
                        player.getSkillStats().setCurrentMana((int) manaRegain);
                        player.getMovement().getPlayerBars().getPlayerManaBar().setProgress((double) player.getSkillStats().getCurrentMana() / player.getSkillStats().getMaxMana());
                    } else {
                        player.getSkillStats().setCurrentMana(player.getSkillStats().getMaxMana());
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
package com.arturoo404.game.player.skills;

import com.arturoo404.game.player.Player;
import com.arturoo404.game.player.PlayerBars;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class PlayerResourceManagement {
    private final Player player;
    private PlayerBars playerBars;

    public PlayerResourceManagement(Player player) {
        this.player = player;
    }

    public void setPlayerBars(PlayerBars playerBars) {
        this.playerBars = playerBars;
    }

    public void init() {
        resourceRegainTimer();
    }

    public void resourceRegainTimer() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    // Regeneracja zdrowia gracza
                    if (player.getSkillStats().getCurrentHealth() < player.getSkillStats().getMaxHealth()) {
                        double healthRegain = player.getSkillStats().getCurrentHealth() + player.getSkillStats().getHealthRegen() + player.getSkillStats().getPercentHealthRegen();
                        player.getSkillStats().setCurrentHealth((int) healthRegain);
                        playerBars.getPlayerHpBar().setProgress((double) player.getSkillStats().getCurrentHealth() / player.getSkillStats().getMaxHealth());
                    } else {
                        player.getSkillStats().setCurrentHealth(player.getSkillStats().getMaxHealth());
                    }
                    // Regeneracja many gracza
                    if (player.getSkillStats().getCurrentMana() < player.getSkillStats().getMaxMana()) {
                        double manaRegain = player.getSkillStats().getCurrentMana() + player.getSkillStats().getManaRegen() + player.getSkillStats().getPercentManaRegen();
                        player.getSkillStats().setCurrentMana((int) manaRegain);
                        playerBars.getPlayerManaBar().setProgress((double) player.getSkillStats().getCurrentMana() / player.getSkillStats().getMaxMana());
                    } else {
                        player.getSkillStats().setCurrentMana(player.getSkillStats().getMaxMana());
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
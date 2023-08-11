package com.arturoo404.game.player.gui;

import com.arturoo404.game.player.Player;
import javafx.scene.control.ProgressBar;

public class PlayerBars{

    private ProgressBar playerHpBar, playerManaBar;
    private final Player player;

    public PlayerBars(Player player) {
        this.player = player;
    }
    public void init(){
        playerHpBar = initProgressBar();
        playerManaBar = initProgressBar();
        player.getAnchorPane().getChildren().add(playerHpBar);
        player.getAnchorPane().getChildren().add(playerManaBar);
        barAppearance();
    }

    private ProgressBar initProgressBar(){
        return new ProgressBar();
    }
    public ProgressBar getPlayerHpBar() {
        return playerHpBar;
    }
    public ProgressBar getPlayerManaBar() {return  playerManaBar;}

    private void barAppearance(){
        // ustawia pasek zdrowia nad graczom
        playerManaBar.setLayoutX(player.getPlayerShape().getX() - 12);
        playerManaBar.setLayoutY(player.getPlayerShape().getY() - 10);
        playerHpBar.setLayoutY(player.getPlayerShape().getY() - 22);
        playerHpBar.setLayoutX(player.getPlayerShape().getX() - 12);
        playerManaBar.setProgress((double) player.getSkillStats().getCurrentMana() / player.getSkillStats().getMaxMana());
        playerHpBar.setProgress((double) player.getSkillStats().getCurrentHealth() / player.getSkillStats().getMaxHealth()); // ustawia pasek zdrowia na obecne zdrowie gracza
        playerHpBar.setStyle("-fx-border-width: 1px; -fx-accent: red; -fx-control-inner-background: rgba(255,255,255,0); ");
        playerManaBar.setStyle("-fx-border-width: 1px; -fx-accent: blue; -fx-control-inner-background: rgba(255,255,255,0); ");
        playerHpBar.setMaxHeight(12);
        playerManaBar.setMaxHeight(12);
    }


}
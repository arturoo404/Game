package com.arturoo404.game.player;

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
        ProgressBar progressBar = new ProgressBar();
        return progressBar;
    }
    public ProgressBar getPlayerHpBar() {
        return playerHpBar;
    }
    public ProgressBar getPlayerManaBar() {return  playerManaBar;}

    private void barAppearance(){
        // ustawia pasek zdrowia nad graczem
        playerManaBar.setLayoutX(player.getRectangle().getX() - 12);
        playerManaBar.setLayoutY(player.getRectangle().getY() - 10);
        playerHpBar.setLayoutY(player.getRectangle().getX() - 20);
        playerHpBar.setLayoutX(player.getRectangle().getY() - 12);
        playerManaBar.setProgress((double) player.getSkillStats().getCurrentMana() / player.getSkillStats().getMaxMana());
        playerHpBar.setProgress((double) player.getSkillStats().getCurrentHealth() / player.getSkillStats().getMaxHealth()); // ustawia pasek zdrowia na obecne zdrowie gracza
        playerHpBar.setStyle("-fx-border-width: 1px; -fx-accent: red; -fx-control-inner-background: black; ");
        playerManaBar.setStyle("-fx-border-width: 1px; -fx-accent: blue; -fx-control-inner-background: black; ");
        playerHpBar.setMaxHeight(12);
        playerManaBar.setMaxHeight(12);
    }
}
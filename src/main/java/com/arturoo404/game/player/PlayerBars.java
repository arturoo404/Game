package com.arturoo404.game.player;

import javafx.scene.control.ProgressBar;

public class PlayerBars{

    private ProgressBar playerHpBar;
    private final Player player;

    public PlayerBars(Player player) {
        this.player = player;
    }
    public void init(){
        playerHpBar = initProgressBar();
        player.getAnchorPane().getChildren().add(playerHpBar);
        barAppearance();
    }

    private ProgressBar initProgressBar(){
        ProgressBar progressBar = new ProgressBar();
        return progressBar;
    }
    public ProgressBar getPlayerHpBar() {
        return playerHpBar;
    }

    private void barAppearance(){
        // ustawia pasek zdrowia nad graczem
        playerHpBar.setLayoutY(player.getRectangle().getX() - 20);
        playerHpBar.setLayoutX(player.getRectangle().getY() - 12);
        playerHpBar.setProgress((double) player.getSkillStats().getCurrentHealth() / player.getSkillStats().getMaxHealth()); // ustawia pasek zdrowia na obecne zdrowie gracza
        playerHpBar.setStyle("-fx-accent: red;");
        playerHpBar.setStyle("-fx-control-inner-background: black;");
    }
}
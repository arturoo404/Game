package com.arturoo404.game.player.gui;

import com.arturoo404.game.generate.GeneratePlayerGui;
import com.arturoo404.game.player.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


import static com.arturoo404.game.GameWindowController.getPlayer;

public class PlayerGuiController implements Initializable {

    private GeneratePlayerGui controller;
    private Player player;
    @FXML
    private Label damage, attackSpeed, healthRegen, armor, manaRegen, cooldownReduction, hpText, manaText, experiance, money, playerLvl;
    @FXML
    private ProgressBar hpBar, manaBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player = getPlayer();
        statHandler();
    }


    private void statHandler(){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), event -> {
                    damage.setText("Damage: " + player.getSkillStats().getDamage());
                    attackSpeed.setText("Attack Speed: " + player.getSkillStats().getAttackSpeed());
                    armor.setText("Armor: " + player.getSkillStats().getArmor());
                    healthRegen.setText("Health Regeneration: " + player.getSkillStats().getHealthRegen());
                    manaRegen.setText("Mana Regeneration: " + player.getSkillStats().getManaRegen());
                    cooldownReduction.setText("Cooldown Reduction : " + player.getSkillStats().getCooldownReduction());
                    hpBar.setProgress((double) player.getSkillStats().getCurrentHealth() / player.getSkillStats().getMaxHealth());
                    hpText.setText(player.getSkillStats().getCurrentHealth()+"/"+player.getSkillStats().getMaxHealth()+" Hp");
                    manaText.setText(player.getSkillStats().getCurrentMana()+"/"+player.getSkillStats().getMaxMana()+" Mana");
                    manaBar.setProgress((double) player.getSkillStats().getCurrentMana() / player.getSkillStats().getMaxMana());
                    experiance.setText(player.getSkillStats().getCurrentExp()+"/"+player.getSkillStats().getNextLvlExp()+" Exp");
                    playerLvl.setText(player.getSkillStats().getPlayerLvl()+" Level");
                    money.setText("Money: "+player.getSkillStats().getMoney());
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}

package com.arturoo404.game.player.gui;

import com.arturoo404.game.player.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


import static com.arturoo404.game.GameWindowController.getPlayer;

public class PlayerGuiController implements Initializable {


    private Player player;
    @FXML
    private Label damage, attackSpeed, healthRegen, armor, manaRegen, cooldownReduction, hpText, manaText, experiance, money, playerLvl;
    @FXML
    private ProgressBar hpBar, manaBar;
    @FXML
    private Button iconTooltip;
    Tooltip tooltipI = new Tooltip();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player = getPlayer();
        showTooltips();
        statHandler();
    }


    private void statHandler(){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    damage.setText(String.valueOf(player.getSkillStats().getDamage()));
                    attackSpeed.setText(String.valueOf(player.getSkillStats().getAttackSpeed()));
                    armor.setText(String.valueOf(player.getSkillStats().getArmor()));
                    healthRegen.setText(String.valueOf(player.getSkillStats().getHealthRegen()));
                    manaRegen.setText(String.valueOf(player.getSkillStats().getManaRegen()));
                    cooldownReduction.setText(String.valueOf(player.getSkillStats().getCooldownReduction()));
                    hpBar.setProgress(player.getSkillStats().getCurrentHealth() / player.getSkillStats().getMaxHealth());
                    hpText.setText(player.getSkillStats().getCurrentHealth()+"/"+player.getSkillStats().getMaxHealth());
                    manaText.setText(player.getSkillStats().getCurrentMana()+"/"+player.getSkillStats().getMaxMana());
                    manaBar.setProgress(player.getSkillStats().getCurrentMana() / player.getSkillStats().getMaxMana());
                    experiance.setText(player.getSkillStats().getCurrentExp()+"/"+player.getSkillStats().getNextLvlExp()+" Exp");
                    playerLvl.setText(player.getSkillStats().getPlayerLvl()+" Level");
                    money.setText("Money: "+player.getSkillStats().getMoney());
                    tooltipI.setText(iconTooltipFormat());
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void showTooltips(){
        tooltipI.setShowDelay(Duration.ZERO);
        tooltipI.setHideDelay(Duration.ZERO);
        iconTooltip.setTooltip(tooltipI);
        iconTooltip.getStyleClass().add("tooltip");
    }

    private String iconTooltipFormat() {
        return """
                   Fire bolt
                   Shoot a fire bolt in direction you facing
                   dealing\s""" + player.getSkillStats().basicAttackDamage() + " damage"
                + "\nand...";
    }
}

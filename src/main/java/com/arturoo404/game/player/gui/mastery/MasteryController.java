package com.arturoo404.game.player.gui.mastery;

import com.arturoo404.game.player.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import static com.arturoo404.game.GameWindowController.getPlayer;

public class MasteryController implements Initializable {
    private Player player;
    @FXML
    private Button vigorUp, intelligenceUp;
    @FXML
    private Label vigorValue, currentHealth, lvlUpHealth, vigorLvl, masteryPoints, intelligenceValue, currentAp, lvlUpAp, intelligenceLvl;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player = getPlayer();
        labelUpdate();
        initButtons();
    }

    private void initButtons(){
        vigorUp.setOnAction(this::vigorUp);
        intelligenceUp.setOnAction(this::intelligenceUp);
    }

    private void vigorUp(ActionEvent event){
        if(player.getSkillStats().getSkillPoint() > 0){
            player.getSkillStats().setSkillPoint(player.getSkillStats().getSkillPoint() -1);
            player.getSkillStats().setMaxHealth(player.getSkillStats().getMaxHealth() + player.getPlayerMastery().getVigorValue());
            player.getPlayerMastery().setVigor(player.getPlayerMastery().getVigor() + 1);

            switch (player.getPlayerMastery().getVigor()) {
                case 40 -> player.getPlayerMastery().setVigorValue(8);
                case 70 -> player.getPlayerMastery().setVigorValue(5);
            }
            labelUpdate();
        }
    }

    private void intelligenceUp(ActionEvent event){
        if(player.getSkillStats().getSkillPoint() > 0){
            player.getSkillStats().setSkillPoint(player.getSkillStats().getSkillPoint() -1);
            player.getSkillStats().setDamage(player.getSkillStats().getDamage() + player.getPlayerMastery().getIntelligenceValue());
            player.getPlayerMastery().setIntelligence(player.getPlayerMastery().getIntelligence() + 1);

            switch (player.getPlayerMastery().getIntelligence()) {
                case 40 -> player.getPlayerMastery().setIntelligenceValue(2);
                case 70 -> player.getPlayerMastery().setIntelligenceValue(1);
            }
            labelUpdate();
        }
    }

    private void labelUpdate(){
        vigorLvl.setText(String.valueOf(player.getPlayerMastery().getVigor()));
        intelligenceLvl.setText(String.valueOf(player.getPlayerMastery().getIntelligence()));

        vigorValue.setText("(+" +(player.getPlayerMastery().getVigorValue()) + ")");
        currentHealth.setText((player.getSkillStats().getMaxHealth()) + " HP");
        lvlUpHealth.setText((player.getSkillStats().getMaxHealth() + player.getPlayerMastery().getVigorValue()) + " HP");

        intelligenceValue.setText("(+" +(player.getPlayerMastery().getIntelligenceValue()) + ")");
        currentAp.setText((player.getSkillStats().getDamage()) + " AP");
        lvlUpAp.setText((player.getSkillStats().getDamage() + player.getPlayerMastery().getIntelligenceValue()) + " AP");

        masteryPoints.setText(String.valueOf(player.getSkillStats().getSkillPoint()));
    }
}

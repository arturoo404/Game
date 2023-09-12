package com.arturoo404.game.player.gui.mastery;

import com.arturoo404.game.player.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static com.arturoo404.game.GameWindowController.getPlayer;

public class MasteryController implements Initializable {
    private Player player;
    @FXML
    private Button vigorUp, intelligenceUp, mindUp, dexterityUp, arcaneUp;
    @FXML
    private Label vigorValue, currentHealth, lvlUpHealth, vigorLvl, masteryPoints, intelligenceValue, currentAp, lvlUpAp, intelligenceLvl, mindValue, currentMana, lvlUpMana,
            mindLvl, dexterityValue, currentAttackSpeed, lvlUpAttackSpeed, dexterityLvl, arcaneValue, currentCooldownReduction, lvlUpCooldownReduction, arcaneLvl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player = getPlayer();
        labelUpdate();
        initButtons();
    }

    private void initButtons(){
        vigorUp.setOnAction(this::vigorUp);
        intelligenceUp.setOnAction(this::intelligenceUp);
        mindUp.setOnAction(this::mindUp);
        dexterityUp.setOnAction(this::dexterityUp);
        arcaneUp.setOnAction(this::arcaneUp);
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

    private void mindUp(ActionEvent event){
        if(player.getSkillStats().getSkillPoint() > 0){
            player.getSkillStats().setSkillPoint(player.getSkillStats().getSkillPoint() -1);
            player.getSkillStats().setMaxMana(player.getSkillStats().getMaxMana() + player.getPlayerMastery().getMindValue());
            player.getPlayerMastery().setMind(player.getPlayerMastery().getMind() + 1);

            switch (player.getPlayerMastery().getMind()) {
                case 40 -> player.getPlayerMastery().setMindValue(6);
                case 70 -> player.getPlayerMastery().setMindValue(4);
            }
            labelUpdate();
        }
    }

    private void dexterityUp(ActionEvent event){
        if(player.getSkillStats().getSkillPoint() > 0){
            player.getSkillStats().setSkillPoint(player.getSkillStats().getSkillPoint() -1);
            player.getSkillStats().setAttackSpeed(player.getSkillStats().getAttackSpeed() + player.getPlayerMastery().getDexterityValue());
            player.getPlayerMastery().setDexterity(player.getPlayerMastery().getDexterity() + 1);

            switch (player.getPlayerMastery().getDexterity()) {
                case 5 -> player.getPlayerMastery().setDexterityValue(0.04);
                case 10 -> player.getPlayerMastery().setDexterityValue(0.03);
                case 20 -> player.getPlayerMastery().setDexterityValue(0.02);
                case 50 -> player.getPlayerMastery().setDexterityValue(0.01);
            }
            labelUpdate();
        }
    }

    private void arcaneUp(ActionEvent event){
        if(player.getSkillStats().getSkillPoint() > 0){
            player.getSkillStats().setSkillPoint(player.getSkillStats().getSkillPoint() -1);
            player.getSkillStats().setCooldownReduction(player.getSkillStats().getCooldownReduction() + player.getPlayerMastery().getArcaneValue());
            player.getPlayerMastery().setArcane(player.getPlayerMastery().getArcane() + 1);

            switch (player.getPlayerMastery().getArcane()) {
                case 3 -> player.getPlayerMastery().setArcaneValue(3);
                case 6 -> player.getPlayerMastery().setArcaneValue(2);
                case 12 -> player.getPlayerMastery().setArcaneValue(1);
                case 20 -> player.getPlayerMastery().setArcaneValue(0.5);
            }
            labelUpdate();
        }
    }

    private void labelUpdate(){
        vigorLvl.setText(String.valueOf(player.getPlayerMastery().getVigor()));
        intelligenceLvl.setText(String.valueOf(player.getPlayerMastery().getIntelligence()));
        mindLvl.setText(String.valueOf(player.getPlayerMastery().getMind()));
        dexterityLvl.setText(String.valueOf(player.getPlayerMastery().getDexterity()));
        arcaneLvl.setText(String.valueOf(player.getPlayerMastery().getArcane()));

        vigorValue.setText("(+" +(player.getPlayerMastery().getVigorValue()) + ")");
        currentHealth.setText(String.valueOf((player.getSkillStats().getMaxHealth())));
        lvlUpHealth.setText(String.valueOf((player.getSkillStats().getMaxHealth() + player.getPlayerMastery().getVigorValue())));

        intelligenceValue.setText("(+" +(player.getPlayerMastery().getIntelligenceValue()) + ")");
        currentAp.setText(String.valueOf((player.getSkillStats().getDamage())));
        lvlUpAp.setText(String.valueOf((player.getSkillStats().getDamage() + player.getPlayerMastery().getIntelligenceValue())));

        mindValue.setText("(+" +(player.getPlayerMastery().getMindValue()) + ")");
        currentMana.setText(String.valueOf((player.getSkillStats().getMaxMana())));
        lvlUpMana.setText(String.valueOf((player.getSkillStats().getMaxMana() + player.getPlayerMastery().getMindValue())));

        dexterityValue.setText("(+" +(player.getPlayerMastery().getDexterityValue() * 100) + "%)");
        currentAttackSpeed.setText(String.valueOf((player.getSkillStats().getAttackSpeed())));
        lvlUpAttackSpeed.setText(String.valueOf((player.getSkillStats().getAttackSpeed() + player.getPlayerMastery().getDexterityValue())));

        arcaneValue.setText("(+" +(player.getPlayerMastery().getArcaneValue()) + "%)");
        currentCooldownReduction.setText(player.getSkillStats().getCooldownReduction() + "%");
        lvlUpCooldownReduction.setText(player.getSkillStats().getCooldownReduction() + player.getPlayerMastery().getArcaneValue() + "%");

        masteryPoints.setText(String.valueOf(player.getSkillStats().getSkillPoint()));
    }
}

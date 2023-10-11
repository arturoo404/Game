package com.arturoo404.game.player.skills;

import com.arturoo404.game.player.Player;
import com.arturoo404.game.player.skills.bullet.BasicAttack;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class SkillsController {

    private final Player player;

    private final BooleanProperty basicAttack = new SimpleBooleanProperty();

    private final BooleanProperty healAbilityBoolean = new SimpleBooleanProperty();

    private final BooleanBinding healAbilityKeyPress = healAbilityBoolean.or(healAbilityBoolean);
    private final BooleanBinding keyPress = basicAttack.or(basicAttack);

    private final BasicAttack basic;
    private final HealAbility healAbility;

    /**
     * Initialize the player skills
     */
    public void init(){
        player.setSkillsController(this);
        basic.init();
        healAbilityKeyPress.addListener((((observableValue, aBoolean, t1) -> healAbility.heal())));
        keyPress.addListener(((observableValue, aBoolean, t1) -> basic.setPlay(!aBoolean)));
    }


    public SkillsController(Player player) {
        this.player = player;
        basic = new BasicAttack(player);
        healAbility = new HealAbility(player);
    }

    public void setBasicAttack(boolean basicAttack) {
        this.basicAttack.set(basicAttack);
    }
    public void setHealAbilityBoolean(boolean healAbilityBoolean) {
        this.healAbilityBoolean.set(healAbilityBoolean);
    }

    public BasicAttack getBasic() {
        return basic;
    }
}

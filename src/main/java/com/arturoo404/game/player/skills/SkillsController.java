package com.arturoo404.game.player.skills;

import com.arturoo404.game.player.Player;
import com.arturoo404.game.player.skills.bullet.BasicAttack;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class SkillsController {

    private final Player player;

    private final BooleanProperty basicAttack = new SimpleBooleanProperty();

    private final BooleanBinding keyPress = basicAttack.or(basicAttack);

    private final BasicAttack basic;

    /**
     * Initialize the player skills
     */
    public void init(){
        player.setSkillsController(this);
        basic.init();
        keyPress.addListener(((observableValue, aBoolean, t1) -> {
            basic.setPlay(!aBoolean);
        }));
    }


    public SkillsController(Player player) {
        this.player = player;
        basic = new BasicAttack(player);
    }

    public void setBasicAttack(boolean basicAttack) {
        this.basicAttack.set(basicAttack);
    }

    public BasicAttack getBasic() {
        return basic;
    }
}

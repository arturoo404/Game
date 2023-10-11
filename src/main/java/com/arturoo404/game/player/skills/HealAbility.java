package com.arturoo404.game.player.skills;

import com.arturoo404.game.player.Player;
import lombok.Setter;

public class HealAbility {

    private final Player player;
    @Setter
    private boolean start = true;
    private boolean healCd = true;

    public HealAbility(Player player) {
        this.player = player;
    }

    public void heal() {
        if (start && healCd){
            player.getSkillStats().setCurrentHealth(player.getSkillStats().getCurrentHealth() + 50);
            healCd = false;
            countCooldownReduction();
        } else if (start){
            System.out.println("skill is on cd");
        }
    }

    private void countCooldownReduction(){
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            healCd = true;
        });
        thread.start();
    }
}

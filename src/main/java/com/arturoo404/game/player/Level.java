package com.arturoo404.game.player;

public class Level {
    private final Player player;
    public Level(Player player) {
        this.player = player;
    }

    public void levelUp(int exp) {
        player.getPlayerExperiences().setCurrentExp(exp);
        levelScript();
    }

    private void levelScript(){
        if (player.getPlayerExperiences().getCurrentExp() >= player.getPlayerExperiences().getNextLvlExp()) {
            player.getPlayerExperiences().setCurrentExp(player.getPlayerExperiences().getCurrentExp() - player.getPlayerExperiences().getNextLvlExp());
            player.getSkillStats().setPlayerLvl(player.getSkillStats().getPlayerLvl() + 1);
            levelScript();
        }
    }
}


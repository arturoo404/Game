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
            player.getSkillStats().setAbilityPoint(player.getSkillStats().getAbilityPoint() + 1);
            player.getSkillStats().setSkillPoint(player.getSkillStats().getSkillPoint() + 2);
            player.getPlayerExperiences().setNextLvlExp((int) (player.getPlayerExperiences().getNextLvlExp() * 1.5));
            levelScript();
        }
    }
}


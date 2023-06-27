package com.arturoo404.game.player.skills.bullet;


public class BulletSkillStatsController {

    private final BasicAttack basicAttack;

    /**
     * This method is used to refresh the basic attack stack
     */
    public void countBasicAttackStack(){
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep((int) (1000 / basicAttack.getPlayer().getSkillStats().getAttackSpeed()));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            basicAttack.setStack(true);
        });

        thread.start();
    }

    public BulletSkillStatsController(BasicAttack basicAttack) {
        this.basicAttack = basicAttack;
    }
}

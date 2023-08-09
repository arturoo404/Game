package com.arturoo404.game.entity;

import com.arturoo404.game.player.Player;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntityController {

    private LivingEntities livingEntities;

    private EntityDetection entityDetection;

    private Player player;

    public EntityController(LivingEntities livingEntities, Player player) {
        this.livingEntities = livingEntities;
        this.player = player;
        entityDetection = new EntityDetection(livingEntities, player);
    }

    public void init(){
        entityDetection.init();
    }
}

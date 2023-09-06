package com.arturoo404.game.entity;

import com.arturoo404.game.entity.wolf.Wolf;
import com.arturoo404.game.item.ItemAction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LivingEntities {
    private List<Wolf> wolves;
    private ItemAction itemAction;

    public LivingEntities(ItemAction itemAction) {
        this.itemAction = itemAction;
    }
}

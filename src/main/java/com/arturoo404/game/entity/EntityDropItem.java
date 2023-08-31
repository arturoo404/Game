package com.arturoo404.game.entity;

import com.arturoo404.game.item.ItemName;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntityDropItem {

    private ItemName itemName;
    private int dropChance;
    private int dropQuantity;
}

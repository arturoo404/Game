package com.arturoo404.game.item;

import javafx.scene.shape.Rectangle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Item extends ItemBasic{
    private Rectangle itemAtMap;

    public Item(ItemBasic itemBasic, Rectangle itemAtMap) {
        super(itemBasic.getName(), itemBasic.getDescription(), itemBasic.getAttackSpeed(),
                itemBasic.getArmor(), itemBasic.getCooldownReduction(), itemBasic.getDamage(),
                itemBasic.getHealth(), itemBasic.getMana(), itemBasic.getManaRegen(),
                itemBasic.getHpRegen(), itemBasic.getLvlRequirement(), itemBasic.getMarketPrice(),
                itemBasic.getTier(), itemBasic.getItemSet(), itemBasic.getTxtPath(), itemBasic.getItemType());
        this.itemAtMap = itemAtMap;
    }
}

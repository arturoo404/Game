package com.arturoo404.game.player.skills.bullet;

import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BulletAttackObject {

    private Rectangle skill;
    private Timeline timeline;
    private Bullet bullet;
    private KeyCode keyCode;
    private int animationFrame;
}

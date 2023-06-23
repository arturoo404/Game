package com.arturoo404.game.player.skills.bullet;

import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;

public class BulletAttackObject {

    private Rectangle skill;
    private Timeline timeline;
    private Bullet bullet;
    private KeyCode keyCode;
    private int animationFrame;

    public KeyCode getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(KeyCode keyCode) {
        this.keyCode = keyCode;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    public Rectangle getSkill() {
        return skill;
    }

    public void setSkill(Rectangle skill) {
        this.skill = skill;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public int getAnimationFrame() {
        return animationFrame;
    }

    public void setAnimationFrame(int animationFrame) {
        this.animationFrame = animationFrame;
    }
}

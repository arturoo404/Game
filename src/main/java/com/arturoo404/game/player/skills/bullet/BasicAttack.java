package com.arturoo404.game.player.skills.bullet;

import com.arturoo404.game.player.Player;
import com.arturoo404.game.player.skills.animation.BasicAttackSkillsAnimationController;
import com.arturoo404.game.player.skills.collision.SkillsMapCollision;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BasicAttack {

    private boolean play;
    private final Player player;
    private boolean stack;
    private Image image;
    private final List<BulletAttackObject> basickAttackList;
    private BulletSkillStatsController bulletSkillStatsController;
    private BasicAttackSkillsAnimationController skillsAnimationController;

    private SkillsMapCollision skillsMapCollision;

    public BasicAttack(Player player) {
        basickAttackList = Collections.synchronizedList(new ArrayList<>());
        this.player = player;
    }

    /**
     * This method is used to initialize the basic attack
     */
    public void init(){
        stack = true;
        bulletSkillStatsController = new BulletSkillStatsController(this);
        skillsAnimationController = new BasicAttackSkillsAnimationController(this);
        skillsAnimationController.initBasicAttackAnimation();
        skillsMapCollision = new SkillsMapCollision(player.getMovement().getBlocks(), this);
        skillsMapCollision.init();
        image = new Image(getClass().getResourceAsStream("/txt/skills/basicAttack.png"));
        Thread thread = new Thread(() -> {
            Timeline basicAttack = new Timeline(new KeyFrame(Duration.millis(50), actionEvent -> {
                if (play && stack){
                    bulletSkillStatsController.countBasicAttackStack();
                    shot();
                }
            }));
            basicAttack.setCycleCount(Animation.INDEFINITE);
            basicAttack.play();
        });

        thread.start();
    }

    /**
     * This method is used to create the basic attack object
     */
    private void shot(){
        stack = false;
        BulletAttackObject bullet = basicAttack();
        AnchorPane anchorPane = (AnchorPane) player.getRectangle().getParent();
        anchorPane.getChildren().add(bullet.getSkill());
        skillAnimation(bullet);
        basickAttackList.add(bullet);
    }

    /**
     * This method is used to update the basic attack position
     * @param bullet
     */
    private void skillAnimation(BulletAttackObject bullet){
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(30), actionEvent -> {
            switch (bullet.getKeyCode()){
                case S -> bullet.getSkill().setY(bullet.getSkill().getY() + 15);
                case A -> bullet.getSkill().setX(bullet.getSkill().getX() - 15);
                case D -> bullet.getSkill().setX(bullet.getSkill().getX() + 15);
                case W -> bullet.getSkill().setY(bullet.getSkill().getY() - 15);
            }
        }));

        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

        bullet.setTimeline(animation);
    }

    /**
     * This method is used to create BulletAttackObject and set it to correct position
     * @return BulletAttackObject
     */
    private BulletAttackObject basicAttack(){
        Rectangle rectangle = new Rectangle();
        BulletAttackObject bullet = new BulletAttackObject();

        switch (player.getDirection()){
            case S -> {
                rectangle = new Rectangle(player.getRectangle().getX() + 30, player.getRectangle().getY() + 120, 40, 80);
                bullet.setKeyCode(KeyCode.S);
            }
            case A -> {
                rectangle = new Rectangle(player.getRectangle().getX() - 40, player.getRectangle().getY() + 80, 80, 40);
                bullet.setKeyCode(KeyCode.A);
            }
            case D ->{
                rectangle = new Rectangle(player.getRectangle().getX() + 60, player.getRectangle().getY() + 80, 80, 40);
                bullet.setKeyCode(KeyCode.D);

            }
            case W -> {
                rectangle = new Rectangle(player.getRectangle().getX() + 30, player.getRectangle().getY() - 40, 40, 80);
                bullet.setKeyCode(KeyCode.W);
            }
        }

        bullet.setBullet(Bullet.BASIC_ATTACK);
        bullet.setSkill(rectangle);
        rectangle.setFill(new ImagePattern(bulletImage(bullet)));
        return bullet;
    }

    /**
     * This method is used to get the image of the basic attack
     * @param bulletAttackObject
     * @return WritableImage
     */
    private WritableImage bulletImage(BulletAttackObject bulletAttackObject){
        return switch (bulletAttackObject.getKeyCode()) {
            case S -> writableImage(75, 24,  56, 127);
            case A -> writableImage(40, 260, 127, 56);
            case D -> writableImage(20, 453, 127, 56);
            case W -> writableImage(75, 620, 56, 127);
            default -> writableImage(75, 24, 56, 127);
        };
    }

    /**
     * This method is used to get the image of the basic attack
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    private WritableImage writableImage(int x, int y, int width, int height){
        PixelReader reader = image.getPixelReader();
        return new WritableImage(reader, x, y, width, height);
    }

    public synchronized List<BulletAttackObject> getBasickAttackList() {
        return basickAttackList;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public boolean isStack() {
        return stack;
    }

    public void setStack(boolean stack) {
        this.stack = stack;
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * This method is used to update the basic attack. Remove from list and from view.
     * @param attackObject
     */
    public void update(List<BulletAttackObject> attackObject){
        basickAttackList.removeAll(attackObject);
        attackObject.forEach(a -> player.getAnchorPane().getChildren().remove(a.getSkill()));
    }
}

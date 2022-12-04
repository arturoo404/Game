package com.arturoo404.game.player;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Player {
    private final BooleanProperty goUp = new SimpleBooleanProperty();
    private final BooleanProperty  goDown = new SimpleBooleanProperty();
    private final BooleanProperty  goRight = new SimpleBooleanProperty();
    private final BooleanProperty goLeft = new SimpleBooleanProperty();

    private final BooleanBinding keyPress = goUp.or(goDown).or(goLeft).or(goRight);


    public void createPlayer(){
        keyPress.addListener(((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                System.out.println("tttt");
            } else {
                System.out.println("aaaaa");
            }
        }));
    }


    public void setGoUp(boolean goUp) {
        this.goUp.set(goUp);
    }

    public void setGoDown(boolean goDown) {
        this.goDown.set(goDown);
    }

    public void setGoRight(boolean goRight) {
        this.goRight.set(goRight);
    }

    public void setGoLeft(boolean goLeft) {
        this.goLeft.set(goLeft);
    }
}

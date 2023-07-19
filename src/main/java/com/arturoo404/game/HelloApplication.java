package com.arturoo404.game;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("start-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setMinHeight(720);
        stage.setMinWidth(1280);
        stage.setTitle("Game!");
        stage.setScene(scene);
        stage.show();
        stage.setMaxHeight(720);
        stage.setMaxWidth(1280);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), fxmlLoader.getRoot());
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    public static void main(String[] args) {
        launch();
    }
}
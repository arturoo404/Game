module com.arturoo404.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;
    requires com.fasterxml.jackson.databind;

    opens com.arturoo404.game to javafx.fxml;
    exports com.arturoo404.game;
    exports com.arturoo404.game.file;
    exports com.arturoo404.game.entity;
    exports com.arturoo404.game.player;
    opens com.arturoo404.game.player to javafx.fxml;
}
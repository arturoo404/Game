module com.arturoo404.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;
    requires com.fasterxml.jackson.databind;
    requires lombok;
    requires javafx.web;

    opens com.arturoo404.game to javafx.fxml;
    exports com.arturoo404.game;
    exports com.arturoo404.game.file;
    exports com.arturoo404.game.entity;
    exports com.arturoo404.game.item;
    exports com.arturoo404.game.player;
    opens com.arturoo404.game.player to javafx.fxml;
    exports com.arturoo404.game.player.gui;
    opens com.arturoo404.game.player.gui to javafx.fxml;
    exports com.arturoo404.game.options;
    opens com.arturoo404.game.options to javafx.fxml;
    exports com.arturoo404.game.generate.difficulty;
    opens com.arturoo404.game.generate.difficulty to javafx.fxml;
    exports com.arturoo404.game.player.gui.inventory;
    opens com.arturoo404.game.player.gui.inventory to javafx.fxml;
    exports com.arturoo404.game.player.gui.bar;
    opens com.arturoo404.game.player.gui.bar to javafx.fxml;
}
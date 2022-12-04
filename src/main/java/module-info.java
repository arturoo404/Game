module com.arturoo404.game {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.arturoo404.game to javafx.fxml;
    exports com.arturoo404.game;
}
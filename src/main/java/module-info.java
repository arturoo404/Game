module com.arturoo404.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;


    opens com.arturoo404.game to javafx.fxml;
    exports com.arturoo404.game;
}
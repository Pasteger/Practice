module JavaFX {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;

    opens sample;
    opens sample.controllers;
    opens sample.database;
    opens sample.layout;
}

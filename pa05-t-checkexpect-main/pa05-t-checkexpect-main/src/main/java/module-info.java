module cs3500.pa05 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.fasterxml.jackson.databind;

    opens cs3500.pa05 to javafx.fxml;
    exports cs3500.pa05;
    exports cs3500.pa05.controller;
    exports cs3500.pa05.model;
    exports cs3500.pa05.view;
    exports cs3500.pa05.model.json;
    opens cs3500.pa05.controller to javafx.fxml;
    opens cs3500.pa05.model to com.fasterxml.jackson.databind;
    exports cs3500.pa05.view.activities;
    exports cs3500.pa05.view.delegates;
    exports cs3500.pa05.view.tables;
    exports cs3500.pa05.model.enums;
    exports cs3500.pa05.model.activities;
}
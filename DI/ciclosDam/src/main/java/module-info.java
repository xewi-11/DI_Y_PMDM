module com.example.ciclosdam {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires org.json;
    requires com.google.gson;
    requires static lombok;

    opens com.example.ciclosdam to javafx.fxml;
    exports com.example.ciclosdam;
    exports com.example.ciclosdam.Controller;
    opens com.example.ciclosdam.Controller to javafx.fxml,com.google.gson,org.json;
    exports com.example.ciclosdam.DataBase;
    opens com.example.ciclosdam.DataBase to javafx.fxml,java.sql;
}
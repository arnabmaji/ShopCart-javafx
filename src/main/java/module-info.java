module io.github.arnabmaji19 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires com.jfoenix;
    requires javafx.graphics;

    opens io.github.arnabmaji19 to javafx.fxml;
    opens io.github.arnabmaji19.controller;
    opens io.github.arnabmaji19.model;
    exports io.github.arnabmaji19;
}

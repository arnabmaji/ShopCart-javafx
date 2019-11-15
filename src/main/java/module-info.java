module io.github.arnabmaji19 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires com.jfoenix;

    opens io.github.arnabmaji19 to javafx.fxml;
    opens io.github.arnabmaji19.controller;
    exports io.github.arnabmaji19;
}

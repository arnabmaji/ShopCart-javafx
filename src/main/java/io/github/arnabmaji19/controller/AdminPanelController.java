package io.github.arnabmaji19.controller;

import com.jfoenix.controls.JFXTabPane;
import io.github.arnabmaji19.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminPanelController implements Initializable {
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private Tab logOutTab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //On clicking log out tab navigate user to log in page
            if (newValue.equals(logOutTab)) {
                try {
                    App.setRoot("log_in");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

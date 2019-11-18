package io.github.arnabmaji19.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.client.model.Filters;
import io.github.arnabmaji19.App;
import io.github.arnabmaji19.model.AlertDialog;
import io.github.arnabmaji19.model.Database;
import io.github.arnabmaji19.model.Session;
import io.github.arnabmaji19.model.User;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;

public class LogInController {

    @FXML private StackPane stackPane;
    @FXML private JFXTextField emailField;
    @FXML private JFXPasswordField passwordField;

    @FXML
    private void logInExistingUser(){
        String email = emailField.getText();
        String password = passwordField.getText();

        if(email.isBlank()
            || password.isBlank()){
            AlertDialog.show(stackPane, "Field can't be Blank!");
            return;
        }

        new Thread(() -> {
            User user = Database.getInstance()
                    .getUsersCollection()
                    .find(Filters.eq("email", email)).first();
            if(user == null){
                Platform.runLater(() -> AlertDialog.show(stackPane, "Email not registered!"));
                return;
            }
            if(!password.equals(user.getPassword())){
                Platform.runLater(() -> AlertDialog.show(stackPane, "Password Invalid!"));
                return;
            }
            Platform.runLater(() -> {
                AlertDialog.show(stackPane, "Log In successful!");

                //Creating session for current user
                Session.getInstance().createSession(user.getId(), user.getName(), email);

                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(event -> {
                    try {
                        App.setRoot("market_place");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                delay.play();
            });

        }).start();

    }
    
    @FXML
    private void signUpNewUser() throws Exception{
        App.setRoot("sign_up_user");
    }
}

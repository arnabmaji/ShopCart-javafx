package io.github.arnabmaji19.controller;

import io.github.arnabmaji19.App;
import javafx.fxml.FXML;

public class LogInController {

    @FXML
    private void signUpNewUser() throws Exception{
        App.setRoot("sign_up_user");
    }
}

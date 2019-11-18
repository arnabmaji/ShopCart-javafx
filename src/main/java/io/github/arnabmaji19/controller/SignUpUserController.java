package io.github.arnabmaji19.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.client.MongoCollection;
import io.github.arnabmaji19.model.AlertDialog;
import io.github.arnabmaji19.model.Database;
import io.github.arnabmaji19.model.User;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.eq;



public class SignUpUserController {
    @FXML private StackPane stackPane;
    @FXML private JFXTextField userNameField;
    @FXML private JFXTextField userEmailField;
    @FXML private JFXTextField userPhoneNumberField;
    @FXML private JFXPasswordField userPasswordField;
    @FXML private JFXPasswordField userConfirmPasswordField;
    @FXML private JFXCheckBox termsCheckBox;

    @FXML
    private void createNewUser(){
        String name = userNameField.getText();
        String email = userEmailField.getText();
        String phoneNumber = userPhoneNumberField.getText();
        String password = userPasswordField.getText();
        String confirmPassword = userConfirmPasswordField.getText();

        if(name.isBlank()
            || email.isBlank()
            || phoneNumber.isBlank()
            || password.isBlank()
            || confirmPassword.isBlank()){
            AlertDialog.show(stackPane, "Field can't be blank!");
            return;
        }

        if(!isEmailValid(email)){
            AlertDialog.show(stackPane, "Please enter a valid email!");
            return;
        }

        if(!isPhoneNumberValid(phoneNumber)){
            AlertDialog.show(stackPane, "Phone number invalid!");
            return;
        }

        if(!password.equals(confirmPassword)){
            AlertDialog.show(stackPane, "Passwords do not match!");
            return;
        }

        if(!termsCheckBox.isSelected()){
            AlertDialog.show(stackPane, "Please agree to our terms and conditions!");
            return;
        }


        MongoCollection<User> userMongoCollection = Database.getInstance()
                .getUsersCollection();

        User user = userMongoCollection.find(eq("email", email)).first();
        if(user != null){
            AlertDialog.show(stackPane, "Email already registered with us!");
            return;
        }

        new Thread(() ->{
            User newUser = new User(new ObjectId(), name, email, phoneNumber, password, new ArrayList<>());
            userMongoCollection.insertOne(newUser);

        }).start();

        AlertDialog.show(stackPane, "Sign Up successful!");

        //clearing out input fields
        userNameField.clear();
        userEmailField.clear();
        userPhoneNumberField.clear();
        userPasswordField.clear();
        userConfirmPasswordField.clear();
    }

    private boolean isEmailValid(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    private boolean isPhoneNumberValid(String number){
        try{
            Long.parseLong(number);
        } catch (NumberFormatException e){
            return false;
        }

        return number.length() == 10;
    }
}

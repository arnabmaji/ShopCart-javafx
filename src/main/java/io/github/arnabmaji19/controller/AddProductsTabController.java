package io.github.arnabmaji19.controller;

import com.jfoenix.controls.JFXTextField;
import io.github.arnabmaji19.model.AlertDialog;
import io.github.arnabmaji19.model.Database;
import io.github.arnabmaji19.model.Product;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class AddProductsTabController {

    @FXML private StackPane stackPane;
    @FXML private JFXTextField productNameField;
    @FXML private JFXTextField productDescriptionField;
    @FXML private JFXTextField priceField;
    @FXML private JFXTextField quantityField;

    @FXML
    private void onAddNewProduct(){

        String productName = productNameField.getText();
        String productDescription = productDescriptionField.getText();
        int price;
        int quantity;
        if(productName.isBlank()
            || productDescription.isBlank()){
            AlertDialog.show(stackPane, "Field can't be blank");
            return;
        }
        try{
            price = Integer.parseInt(priceField.getText());
            quantity = Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e){
            AlertDialog.show(stackPane, "Integer value needed!");
            return;
        }

        //Clearing out previously entered data
        AlertDialog.show(stackPane, "Product added successfully!");
        productNameField.clear();
        productDescriptionField.clear();
        priceField.clear();
        quantityField.clear();

        //Adding new product in Background Thread
        new Thread(() -> {
            var product = new Product(productName, productDescription, price, quantity);
            Database.getInstance().getProductsCollection().insertOne(product);
        }).start();

    }

}

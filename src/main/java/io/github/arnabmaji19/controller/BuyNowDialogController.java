package io.github.arnabmaji19.controller;

import io.github.arnabmaji19.model.AlertDialog;
import io.github.arnabmaji19.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class BuyNowDialogController{

    @FXML private StackPane stackPane;
    @FXML private Label productNameLabel;
    @FXML private Label productDescription;
    @FXML private Label productPrice;

    private Product product;


    public void initData(Product product){
        this.product = product;

        productNameLabel.setText(product.getName());
        productDescription.setText(product.getDescription());
        productPrice.setText(product.getPrice()+"");
    }

    @FXML
    private void proceedToCheckOut(){
        //TODO: Implement a form to get the delivery location and instructions
        AlertDialog.show(stackPane, "Product will be delivered to you!");

        new Thread(() -> {
        }).start();

    }
}

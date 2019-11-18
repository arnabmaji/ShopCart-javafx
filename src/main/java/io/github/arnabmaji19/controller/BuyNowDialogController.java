package io.github.arnabmaji19.controller;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import io.github.arnabmaji19.model.AlertDialog;
import io.github.arnabmaji19.model.Database;
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


    void initData(Product product){
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
            Database.getInstance()
                    .getProductsCollection()
                    .updateOne(Filters.eq("id", this.product.get_id()), Updates.inc("quantity", -1));
        }).start();
    }
}

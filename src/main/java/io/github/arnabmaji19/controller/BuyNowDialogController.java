package io.github.arnabmaji19.controller;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import io.github.arnabmaji19.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalTime;

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
        AlertDialog.show(stackPane, "Thank you for shopping with us!");

        new Thread(() -> {

            Session session = Session.getInstance();
            Transaction transaction = new Transaction(new ObjectId(),
                    product.getId(),
                    session.getUserId(),
                    LocalTime.now().toString(),
                    LocalDate.now().toString(),1, product.getPrice());
            Database.getInstance()
                    .getTransactionsCollection()
                    .insertOne(transaction);

            //Update stock
            Database.getInstance()
                    .getProductsCollection()
                    .updateOne(Filters.eq("_id", this.product.getId()), Updates.inc("quantity", -1));
        }).start();
    }
}

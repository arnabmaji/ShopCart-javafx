package io.github.arnabmaji19.controller;

import com.jfoenix.controls.JFXListView;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import io.github.arnabmaji19.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.bson.types.ObjectId;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ViewCartController implements Initializable {

    @FXML private StackPane stackPane;
    @FXML private Label cartOwnerLabel;
    @FXML private JFXListView<CartListViewCellController.CartDetails> cartItemsListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cartOwnerLabel.setText(Session.getInstance().getUsername() + "'s Cart");

        List<CartItem> cart =  Objects.requireNonNull(Database.getInstance()
                .getUsersCollection()
                .find(Filters.eq("_id", Session.getInstance().getUserId()))
                .first()).getCart();

        //Getting cart Items and Preparing data for ListView
        ObservableList<CartListViewCellController.CartDetails> cartItems = FXCollections.observableArrayList();
        for(CartItem item : cart){
            Product product = Database.getInstance()
                    .getProductsCollection()
                    .find(Filters.eq("_id", item.getProductId())).first();
            assert product != null;
            var cartDetails = new CartListViewCellController.CartDetails(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    item.getQuantity(),
                    product.getPrice()
            );
            cartItems.add(cartDetails);
        }

        cartItemsListView.setCellFactory(param -> new CartListViewCellController());
        cartItemsListView.setItems(cartItems);
        cartItemsListView.setFocusTraversable(false);
    }

    @FXML
    private void proceedToCheckout() {
        new Thread(() -> {
            Database database = Database.getInstance();
            var transactionMongoCollection
                    = database.getTransactionsCollection();
            var productMongoCollection
                    = database.getProductsCollection();
            var userMongoCollection
                    = database.getUsersCollection();

            ObjectId userId = Session.getInstance().getUserId();
            String timeOfPurchase = LocalTime.now().toString();
            String dateOfPurchase = LocalDate.now().toString();


            for (var cartDetails : cartItemsListView.getItems()) {
                ObjectId productId = cartDetails.getProductId();
                int quantity = cartDetails.getQuantity();
                int totalPrice = cartDetails.getPrice() * quantity;

                //Creating transaction history(In database) for each item purchased
                var transaction = new Transaction(new ObjectId(), productId, userId,
                        timeOfPurchase, dateOfPurchase, quantity, totalPrice);
                transactionMongoCollection.insertOne(transaction);

                //Decrementing product quantity in stock
                productMongoCollection.updateOne(Filters.eq("_id", productId),
                        Updates.inc("quantity", -quantity));
            }
            //Emptying user's cart
            userMongoCollection.updateOne(Filters.eq("_id", userId),
                    Updates.set("cart", new ArrayList<>()));
        }).start();

        AlertDialog.show(stackPane, "Thank you for shopping with us!");
    }

}

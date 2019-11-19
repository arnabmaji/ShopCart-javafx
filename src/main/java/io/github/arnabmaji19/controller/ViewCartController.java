package io.github.arnabmaji19.controller;

import com.jfoenix.controls.JFXListView;
import com.mongodb.client.model.Filters;
import io.github.arnabmaji19.model.CartItem;
import io.github.arnabmaji19.model.Database;
import io.github.arnabmaji19.model.Product;
import io.github.arnabmaji19.model.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
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
            CartListViewCellController.CartDetails cartDetails = new CartListViewCellController.CartDetails(
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
}

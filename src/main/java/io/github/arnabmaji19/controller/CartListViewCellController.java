package io.github.arnabmaji19.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.bson.types.ObjectId;

import java.io.IOException;

public class CartListViewCellController extends JFXListCell<CartListViewCellController.CartDetails> {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label productName;
    @FXML
    private Label totalPrice;
    @FXML
    private Label productDescription;
    @FXML
    private Label totalQuantity;
    @FXML
    private JFXButton removeItemFromCartButton;

    private FXMLLoader loader;

    @Override
    protected void updateItem(CartDetails item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
            return;
        }

        if (loader == null) {
            loader = new FXMLLoader(getClass().getResource("/io/github/arnabmaji19/fxml/cart_list_view_cell.fxml"));
            loader.setController(this);
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

            productName.setText(item.getProductName());
            productDescription.setText("Description: " + item.getDescription());
            totalPrice.setText(item.getPrice() * item.getQuantity() + "");
            totalQuantity.setText("Quantity: " + item.getQuantity() + "");

            removeItemFromCartButton.setOnAction(event -> {
                //TODO: Remove current item from User's cart
//                new Thread(() -> {
//                    Database.getInstance()
//                            .getUsersCollection()
//                            .updateOne(Filters.eq("_id", Session.getInstance().getUserId()),
//                                    Updates.pull("cart", item.getProductId()));
//                }).start();
                //TODO: Remove item from ListView
                getListView().getItems().remove(getItem());
            });

            setText(null);
            setGraphic(anchorPane);
    }

    //Inner class for Handling CartItemDetails
    public static class CartDetails {
        private ObjectId productId;
        private String productName;
        private String description;
        private int quantity;
        private int price;

        public CartDetails() {
        }

        public CartDetails(ObjectId productId, String productName, String description, int quantity, int price) {
            this.productId = productId;
            this.productName = productName;
            this.description = description;
            this.quantity = quantity;
            this.price = price;
        }

        public ObjectId getProductId() {
            return productId;
        }

        public void setProductId(ObjectId productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}

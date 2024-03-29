package io.github.arnabmaji19.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import io.github.arnabmaji19.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductListViewCellController extends JFXListCell<Product> {

    @FXML private GridPane gridPane;
    @FXML private Label productName;
    @FXML private Label description;
    @FXML private Label price;
    @FXML private JFXButton buyNowButton;
    @FXML private JFXButton addToCartButton;

    private MarketPlaceController marketPlaceController;

    private FXMLLoader loader;

    ProductListViewCellController(MarketPlaceController marketPlaceController) {
        this.marketPlaceController = marketPlaceController;
    }

    @Override
    protected void updateItem(Product item, boolean empty) {
        super.updateItem(item, empty);


        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/io/github/arnabmaji19/fxml/product_list_view_cell.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            productName.setText(item.getName());
            description.setText(item.getDescription());

            //If item is out of stock user can't buy or add to cart
            if (item.getQuantity() == 0) {
                price.setText("Out of Stock");
                addToCartButton.setDisable(true);
                buyNowButton.setDisable(true);
            } else {
                price.setText(item.getPrice() + "");
                addToCartButton.setDisable(false);
                buyNowButton.setDisable(false);
            }


            buyNowButton.setOnAction(event -> {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/io/github/arnabmaji19/fxml/buy_now_dialog.fxml"));
                try{
                    Parent root = loader.load();
                    BuyNowDialogController controller = loader.getController();
                    controller.initData(item);
                    stage.setHeight(400);
                    stage.setWidth(500);
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                    marketPlaceController.refreshMarketPlace();
                } catch (IOException e){
                    e.printStackTrace();
                }
            });

            addToCartButton.setOnAction(event -> {
                new Thread(() -> {
                    MongoCollection<User> userMongoCollection = Database.getInstance().getUsersCollection();

                    //If item is already in cart, increment cart item quantity
                    long modifiedCount =  userMongoCollection.updateOne(Filters.and(Filters.eq("_id",Session.getInstance().getUserId()),
                            Filters.eq("cart.productId", item.getId())),
                            Updates.inc("cart.$.quantity", 1)).getModifiedCount();

                    if(modifiedCount == 0){ //If not in cart add separately in cart
                        userMongoCollection
                                .updateOne(Filters.eq("_id", Session.getInstance().getUserId()),
                                        Updates.push("cart", new CartItem(item.getId(),1)));
                    }
                }).start();
                AlertDialog.show(marketPlaceController.stackPane, item.getName() + " added to cart");
            });

            setText(null);
            setGraphic(gridPane);
        }
    }
}

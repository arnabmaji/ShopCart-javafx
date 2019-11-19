package io.github.arnabmaji19.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import io.github.arnabmaji19.model.CartItem;
import io.github.arnabmaji19.model.Database;
import io.github.arnabmaji19.model.Product;
import io.github.arnabmaji19.model.Session;
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

    private FXMLLoader loader;

    @Override
    protected void updateItem(Product item, boolean empty) {
        super.updateItem(item, empty);

        //TODO: If a product is out of stock mark it differently

        if( empty || item == null){
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
            price.setText(item.getPrice() + "");

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
                    stage.show();
                } catch (IOException e){
                    e.printStackTrace();
                    System.out.println("Failed to load Window!");
                }
            });

            addToCartButton.setOnAction(event -> new Thread(() -> Database.getInstance()
                    .getUsersCollection()
                    .updateOne(Filters.eq("_id", Session.getInstance().getUserId()),
                            Updates.push("cart", new CartItem(item.getId(),1)))).start());


            setText(null);
            setGraphic(gridPane);
        }
    }
}

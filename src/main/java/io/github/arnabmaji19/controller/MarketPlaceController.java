package io.github.arnabmaji19.controller;

import com.jfoenix.controls.JFXListView;
import io.github.arnabmaji19.App;
import io.github.arnabmaji19.model.Database;
import io.github.arnabmaji19.model.Product;
import io.github.arnabmaji19.model.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MarketPlaceController implements Initializable {

    @FXML private JFXListView<Product> productsListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshMarketPlace();
        productsListView.setCellFactory(param -> new ProductListViewCellController(MarketPlaceController.this));
        productsListView.setFocusTraversable(false);
    }

    @FXML
    protected void refreshMarketPlace() {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        for (Product product : Database.getInstance()
                .getProductsCollection().find()) {
            productList.add(product);
        }
        productsListView.setItems(productList);
    }

    @FXML
    private void viewCart() throws Exception {
        //Setting up stage to show user's cart
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setWidth(800);
        stage.setHeight(600);

        //Scene
        Parent root = App.loadFXML("view_cart");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        refreshMarketPlace();
    }

    @FXML
    private void logOutCurrentUser() throws Exception {
        //Clearing out current session
        Session.getInstance().clear();
        //Redirecting to Log In page
        App.setRoot("log_in");
    }
}

package io.github.arnabmaji19.controller;

import com.jfoenix.controls.JFXListView;
import io.github.arnabmaji19.App;
import io.github.arnabmaji19.model.Database;
import io.github.arnabmaji19.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MarketPlaceController implements Initializable {

    @FXML private JFXListView<Product> productsListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshMarketPlace();
        productsListView.setCellFactory(param -> new ProductListViewCellController());
        productsListView.setFocusTraversable(false);
    }

    @FXML
    private void refreshMarketPlace(){
        ObservableList<Product> productList = FXCollections.observableArrayList();
        for (Product product : Database.getInstance()
                .getProductsCollection().find()) {
            productList.add(product);
        }
        productsListView.setItems(productList);
    }

    @FXML
    private void viewCart(){
        //TODO: Show user's cart
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.initModality(Modality.APPLICATION_MODAL);

        //Scene
        try{
            Parent root = App.loadFXML("view_cart");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

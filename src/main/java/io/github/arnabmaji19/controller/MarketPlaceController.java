package io.github.arnabmaji19.controller;

import com.jfoenix.controls.JFXListView;
import io.github.arnabmaji19.model.Database;
import io.github.arnabmaji19.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MarketPlaceController implements Initializable {

    @FXML private JFXListView<Product> productsListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        for (Product product : Database.getInstance()
                .getProductsCollection().find()) {
            productList.add(product);
        }
        productsListView.setItems(productList);
        productsListView.setCellFactory(param -> new ProductListViewCellController());
        productsListView.setFocusTraversable(false);
    }
}

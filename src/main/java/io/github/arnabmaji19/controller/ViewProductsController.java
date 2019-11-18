package io.github.arnabmaji19.controller;

import com.mongodb.client.MongoCursor;
import io.github.arnabmaji19.model.Database;
import io.github.arnabmaji19.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ViewProductsController implements Initializable {

    @FXML private TableView<Product> productsTableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        var productNameColumn = new TableColumn<Product, String>("Product Name");
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        var productDescriptionColumn = new TableColumn<Product, String>("Description");
        productDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        var priceColumn = new TableColumn<Product, Integer>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        var quantityColumn = new TableColumn<Product, Integer>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        productsTableView.setEditable(true);
        productsTableView.getColumns().addAll(
                Arrays.asList(
                        productNameColumn, productDescriptionColumn, priceColumn, quantityColumn
                )
        );
        productsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        refreshTable();
    }

    @FXML
    private void refreshTable(){
        MongoCursor<Product> cursor = Database.getInstance()
                .getProductsCollection().find().iterator();

        ObservableList<Product> list = FXCollections.observableArrayList();
        while (cursor.hasNext()){
            list.add(cursor.next());
        }
        productsTableView.getItems().setAll(list);
    }

}

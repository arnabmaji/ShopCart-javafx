package io.github.arnabmaji19.controller;

import com.jfoenix.controls.JFXListCell;
import io.github.arnabmaji19.model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ProductListViewCellController extends JFXListCell<Product> {

    @FXML private GridPane gridPane;
    @FXML private Label productName;
    @FXML private Label description;
    @FXML private Label price;


    private FXMLLoader loader;

    @Override
    protected void updateItem(Product item, boolean empty) {
        super.updateItem(item, empty);

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


            setText(null);
            setGraphic(gridPane);
        }
    }
}

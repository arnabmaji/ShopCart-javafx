package io.github.arnabmaji19.controller;

import com.mongodb.client.model.Filters;
import io.github.arnabmaji19.model.*;
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

public class TransactionsTabController implements Initializable {
    @FXML private TableView<TransactionDetails> transactionsTableView;

    private Database database;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        database = Database.getInstance();

        transactionsTableView.setEditable(true);
        transactionsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Creating table columns
        var productNameCol = new TableColumn<TransactionDetails, String>("Product Name");
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));

        var buyerNameCol = new TableColumn<TransactionDetails, String>("Buyer Name");
        buyerNameCol.setCellValueFactory(new PropertyValueFactory<>("buyerName"));

        var buyerEmailCol = new TableColumn<TransactionDetails, String>("Buyer Email");
        buyerEmailCol.setCellValueFactory(new PropertyValueFactory<>("buyerEmail"));

        var timeOfPurchaseCol = new TableColumn<TransactionDetails, String>("Time of Purchase");
        timeOfPurchaseCol.setCellValueFactory(new PropertyValueFactory<>("timeOfPurchase"));

        var dateOfPurchaseCol = new TableColumn<TransactionDetails, String>("Date of Purchase");
        dateOfPurchaseCol.setCellValueFactory(new PropertyValueFactory<>("dateOfPurchase"));

        var quantityCol = new TableColumn<TransactionDetails, Integer>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        var priceCol = new TableColumn<TransactionDetails, Integer>("Total Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        transactionsTableView.getColumns().addAll(
                Arrays.asList(
                        productNameCol,
                        buyerNameCol,
                        buyerEmailCol,
                        timeOfPurchaseCol,
                        dateOfPurchaseCol,
                        quantityCol,
                        priceCol
                )
        );
        refreshTable();

    }

    private void refreshTable(){
        ObservableList<TransactionDetails> tableData = FXCollections.observableArrayList();
        for(Transaction transaction : database
                .getTransactionsCollection()
                .find()){
            User user = database
                    .getUsersCollection()
                    .find(Filters.eq("_id", transaction.getUserId()))
                    .first();
            Product product = database
                    .getProductsCollection()
                    .find(Filters.eq("_id", transaction.getProductId()))
                    .first();

            assert product != null;
            assert user != null;
            TransactionDetails details = new TransactionDetails(
                    product.getName(),
                    user.getName(),
                    user.getEmail(),
                    transaction.getTimeOfPurchase(),
                    transaction.getDateOfPurchase(),
                    transaction.getQuantity(),
                    transaction.getTotalPrice()
            );
            tableData.add(details);
        }
        transactionsTableView.getItems().setAll(tableData);
    }

}

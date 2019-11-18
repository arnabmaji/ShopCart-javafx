package io.github.arnabmaji19.controller;

import com.mongodb.client.MongoCursor;
import io.github.arnabmaji19.model.Database;
import io.github.arnabmaji19.model.User;
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

public class ViewUsersTabController implements Initializable {

    @FXML private TableView<User> usersTableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Creating table
        var nameColumn = new TableColumn<User, String>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        var emailColumn = new TableColumn<User, String>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        var phoneNumberColumn = new TableColumn<User, String>("Phone Number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        usersTableView.setEditable(true);
        usersTableView.getColumns().addAll(
                Arrays.asList(
                        nameColumn, emailColumn, phoneNumberColumn
                )
        );
        usersTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        refreshTable();
    }

    @FXML private void refreshTable(){
        MongoCursor<User> cursor = Database.getInstance()
                .getUsersCollection().find().iterator();
        ObservableList<User> usersData = FXCollections.observableArrayList();
        while(cursor.hasNext()){
            usersData.add(cursor.next());
        }

        usersTableView.getItems().setAll(usersData);
    }
}

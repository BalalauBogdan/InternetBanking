package com.example.internetbankingfrontend.controller.admin;

import com.example.internetbankingfrontend.InternetBankingApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdminController {
    @FXML
    private void openAddCustomer() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InternetBankingApplication.class.getResource("add-customer-view.fxml"));
        Parent root = fxmlLoader.load();
        String css = Objects.requireNonNull(getClass().getResource("/style/style.css")).toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = new Stage();
        stage.setTitle("Add Customer");
        stage.setHeight(500);
        stage.setWidth(500);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.showAndWait();
    }
    @FXML
    private void openShowCustomerByID() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InternetBankingApplication.class.getResource("show-customer-view.fxml"));
        Parent root = fxmlLoader.load();
        String css = Objects.requireNonNull(getClass().getResource("/style/style.css")).toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = new Stage();
        stage.setTitle("Show Customer");
        stage.setHeight(700);
        stage.setWidth(500);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.showAndWait();
    }
    @FXML
    private void openDeleteCustomerByUsername() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(InternetBankingApplication.class.getResource("delete-customer-view.fxml"));
        Parent root = fxmlLoader.load();
        String css = Objects.requireNonNull(getClass().getResource("/style/style.css")).toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage stage = new Stage();
        stage.setTitle("Delete Customer");
        stage.setHeight(500);
        stage.setWidth(500);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.showAndWait();
    }

}

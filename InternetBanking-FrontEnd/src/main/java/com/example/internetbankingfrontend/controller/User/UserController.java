package com.example.internetbankingfrontend.controller.User;

import com.example.internetbankingfrontend.InternetBankingApplication;
import com.example.internetbankingfrontend.controller.sold.ShowSoldController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class UserController {
    @FXML
    private void openDepositMoney() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(InternetBankingApplication.class.getResource("Deposit-view.fxml"));
        Parent root = fxmlLoader.load();
        String css = Objects.requireNonNull(getClass().getResource("/style/style.css")).toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Platform.runLater(() -> scene.getRoot().requestFocus());
        Stage stage = new Stage();
        stage.setTitle("Deposit Money");
        stage.setHeight(500);
        stage.setWidth(500);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.showAndWait();
    }
    @FXML
    private void openWithdrawMoney() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(InternetBankingApplication.class.getResource("Withdraw-view.fxml"));
        Parent root = fxmlLoader.load();
        String css = Objects.requireNonNull(getClass().getResource("/style/style.css")).toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Platform.runLater(() -> scene.getRoot().requestFocus());
        Stage stage = new Stage();
        stage.setTitle("Withdraw Money");
        stage.setHeight(500);
        stage.setWidth(500);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.showAndWait();
    }
    @FXML
    private void openTransferMoney() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(InternetBankingApplication.class.getResource("Transfer-view.fxml"));
        Parent root = fxmlLoader.load();
        String css = Objects.requireNonNull(getClass().getResource("/style/style.css")).toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Platform.runLater(() -> scene.getRoot().requestFocus());
        Stage stage = new Stage();
        stage.setTitle("Transfer Money");
        stage.setHeight(500);
        stage.setWidth(500);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.showAndWait();
    }
    @FXML
    private void openShowSold() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InternetBankingApplication.class.getResource("show-sold.fxml"));
        Parent root = fxmlLoader.load();
        ShowSoldController showSoldController = fxmlLoader.getController();
        showSoldController.ShowSold();
        String css = Objects.requireNonNull(getClass().getResource("/style/style.css")).toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Platform.runLater(() -> scene.getRoot().requestFocus());
        Stage stage = new Stage();
        stage.setTitle("Sold");
        stage.setHeight(500);
        stage.setWidth(500);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.showAndWait();
    }
}

package com.example.internetbankingfrontend.controller.savings;

import com.example.internetbankingfrontend.InternetBankingApplication;
import com.example.internetbankingfrontend.controller.sold.ShowSoldController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SavingMenuController {
    @FXML
    private void openTotalSavings() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InternetBankingApplication.class.getResource("show-savings-view.fxml"));
        Parent root = fxmlLoader.load();
        ShowSavingsSoldController showSavingsSoldController = fxmlLoader.getController();
        showSavingsSoldController.ShowSavingsSold();
        String css = Objects.requireNonNull(getClass().getResource("/style/style.css")).toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Platform.runLater(() -> scene.getRoot().requestFocus());
        Stage stage = new Stage();
        stage.setTitle("Saved Money");
        stage.setHeight(500);
        stage.setWidth(500);
        stage.setScene(scene);
        Image piggybankIcon = new Image(getClass().getResourceAsStream("/Icons/piggy-bank-icon.png"));
        stage.getIcons().add(piggybankIcon);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.showAndWait();
    }
    @FXML
    private void openDepositSavings() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InternetBankingApplication.class.getResource("deposit-savings-view.fxml"));
        Parent root = fxmlLoader.load();
        String css = Objects.requireNonNull(getClass().getResource("/style/style.css")).toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Platform.runLater(() -> scene.getRoot().requestFocus());
        Stage stage = new Stage();
        stage.setTitle("Deposit money");
        stage.setHeight(500);
        stage.setWidth(500);
        stage.setScene(scene);
        Image depositmoneyIcon = new Image(getClass().getResourceAsStream("/Icons/deposit-money-icon.png"));
        stage.getIcons().add(depositmoneyIcon);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.showAndWait();
    }
    @FXML
    private void openWithdrawSavings() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InternetBankingApplication.class.getResource("withdraw-savings-view.fxml"));
        Parent root = fxmlLoader.load();
        String css = Objects.requireNonNull(getClass().getResource("/style/style.css")).toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Platform.runLater(() -> scene.getRoot().requestFocus());
        Stage stage = new Stage();
        stage.setTitle("Withdraw money");
        stage.setHeight(500);
        stage.setWidth(500);
        stage.setScene(scene);
        Image withdrawmoneyIcon = new Image(getClass().getResourceAsStream("/Icons/withdraw-money-icon.png"));
        stage.getIcons().add(withdrawmoneyIcon);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.showAndWait();
    }

}

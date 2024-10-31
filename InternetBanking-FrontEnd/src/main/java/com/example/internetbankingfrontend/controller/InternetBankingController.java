package com.example.internetbankingfrontend.controller;

import com.example.internetbankingfrontend.InternetBankingApplication;
import com.example.internetbankingfrontend.controller.admin.AdminLoginController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class InternetBankingController {
    @FXML
    public void OpenLoginOrRegister() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(InternetBankingApplication.class.getResource("login-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            String css = Objects.requireNonNull(getClass().getResource("/style/style.css")).toExternalForm();
            scene.getStylesheets().add(css);
            Stage stage = new Stage();
            stage.setTitle("Login or register into account");
            stage.setHeight(350);
            stage.setWidth(500);
            stage.setScene(scene);
            Image loginIcon = new Image(getClass().getResourceAsStream("/Icons/login-icon.png"));
            stage.getIcons().add(loginIcon);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(stage.getOwner());
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OpenAdmin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(InternetBankingApplication.class.getResource("admin-login-view.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            String css = Objects.requireNonNull(getClass().getResource("/style/style.css")).toExternalForm();
            scene.getStylesheets().add(css);
            Platform.runLater(() -> scene.getRoot().requestFocus());
            Stage stage = new Stage();
            stage.setTitle("Admin login");
            stage.setHeight(500);
            stage.setWidth(500);
            stage.setScene(scene);
            Image adminIcon = new Image(getClass().getResourceAsStream("/Icons/admin-icon.png"));
            stage.getIcons().add(adminIcon);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(stage.getOwner());

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
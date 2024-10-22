package com.example.internetbankingfrontend.controller.login;

import javafx.fxml.FXML;

import java.awt.*;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    public void Login() {
        try {

        } catch (Exception e) {
            this.showConfirmationMessage(Alert.AlertType.ERROR,"Error","Please fill in all the fields");
        }

    }
    private void showConfirmationMessage(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void Register(){

    }
}

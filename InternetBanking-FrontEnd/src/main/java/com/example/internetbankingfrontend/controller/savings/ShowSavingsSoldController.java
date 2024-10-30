package com.example.internetbankingfrontend.controller.savings;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class ShowSavingsSoldController {
    @FXML
    private TextField SavingsSoldField;
    @FXML
    public void initialize() {
        Platform.runLater(() -> SavingsSoldField.getParent().requestFocus());
    }
    @FXML
    public void ShowSavingsSold(){

    }

}

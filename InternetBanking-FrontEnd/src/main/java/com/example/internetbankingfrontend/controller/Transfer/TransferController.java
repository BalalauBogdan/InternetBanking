package com.example.internetbankingfrontend.controller.Transfer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TransferController {
    @FXML
    TextField TransferPerson;
    @FXML
    TextField TransferSum;
    @FXML
    public void initialize() {
        Platform.runLater(() -> TransferPerson.getParent().requestFocus());
    }
    @FXML
    public void TransferSumofMoney() {

    }
}

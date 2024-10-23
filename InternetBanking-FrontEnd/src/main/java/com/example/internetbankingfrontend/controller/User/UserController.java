package com.example.internetbankingfrontend.controller.User;

import com.example.internetbankingfrontend.InternetBankingApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController {
    @FXML
    private void openDepositMoney() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(InternetBankingApplication.class.getResource("Deposit-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Deposit Money");
        stage.setHeight(500);
        stage.setWidth(500);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.showAndWait();
    }
    @FXML
    private void openWithdrawMoney() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(InternetBankingApplication.class.getResource("Withdraw-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Withdraw Money");
        stage.setHeight(500);
        stage.setWidth(500);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.showAndWait();
    }
    @FXML
    private void openTransferMoney() throws IOException{

    }
}

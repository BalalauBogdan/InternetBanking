package com.example.internetbankingfrontend.controller;

import com.example.internetbankingfrontend.InternetBankingApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class InternetBankingController {
    @FXML
    public void OpenLoginOrRegister(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(InternetBankingApplication.class.getResource("login-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Login or register into account");
            stage.setHeight(500);
            stage.setWidth(500);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(stage.getOwner());
            stage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
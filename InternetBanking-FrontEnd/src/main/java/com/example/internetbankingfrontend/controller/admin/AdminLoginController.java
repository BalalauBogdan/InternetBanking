package com.example.internetbankingfrontend.controller.admin;

import javafx.fxml.FXML;
import javafx.stage.Stage;


import javafx.scene.control.TextField;

public class AdminLoginController {
    @FXML
    private TextField adminUsername;
    @FXML
    private TextField adminPassword;

    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public static int loggedIn=0;
    @FXML
    public void loginAdmin(){
        String UsernameAdmin=adminUsername.getText();
        String PasswordAdmin=adminPassword.getText();
        if ("admin".equals(UsernameAdmin) && "admin".equals(PasswordAdmin)) {
            loggedIn=1;
            dialogStage.close();
        } else {
            loggedIn=0;
            adminUsername.setText("");
            adminPassword.setText("");
        }
    }

}

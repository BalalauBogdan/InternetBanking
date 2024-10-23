package com.example.internetbankingfrontend.controller.register;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterController {
    @FXML
    private TextField RegisterUsername;
    @FXML
    private TextField RegisterPassword;
    @FXML
    private TextField RegisterConfirmPassword;
    @FXML
    private TextField RegisterFirstName;
    @FXML
    private TextField RegisterLastName;
    @FXML
    private TextField RegisterPhoneNumber;
    @FXML
    public void RegisterCustomer(){
            try {
                String inputRegisterUsername = RegisterUsername.getText();
                String inputRegisterPassword = RegisterPassword.getText();
                String inputRegisterConfirmPassword = RegisterConfirmPassword.getText();
                String inputRegisterFirstName = RegisterFirstName.getText();
                String inputRegisterLastName = RegisterLastName.getText();
                String inputRegisterPhoneNumber = RegisterPhoneNumber.getText();

                if(inputRegisterPassword.equals(inputRegisterConfirmPassword)) {

                    String jsonInputString = "{"
                            + "\"username\": \"" + inputRegisterUsername + "\","
                            + "\"password\": \"" + inputRegisterPassword + "\","
                            + "\"firstname\": \"" + inputRegisterFirstName + "\","
                            + "\"lastname\": \"" + inputRegisterLastName + "\","
                            + "\"phoneNumber\": \"" + inputRegisterPhoneNumber + "\""
                            + "}";
                    URL url = new URL("http://localhost:8080/api/customer/");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/json; utf-8");
                    con.setRequestProperty("Accept", "application/json");
                    con.setDoOutput(true);

                    try (OutputStream os = con.getOutputStream()) {
                        byte[] input = jsonInputString.getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }
                    int code = con.getResponseCode();
                    if (code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_CREATED) {
                        System.out.println("Customer adăugat cu succes.");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Username inregistrat anterior. ", ButtonType.OK);
                        alert.setTitle("Used Username");
                        alert.setHeaderText(null);
                        alert.showAndWait();
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Parolele nu corespund", ButtonType.OK);
                    alert.setTitle("Eroare Parole");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }


            } catch(Exception e){
                e.printStackTrace();
            }
    }
}

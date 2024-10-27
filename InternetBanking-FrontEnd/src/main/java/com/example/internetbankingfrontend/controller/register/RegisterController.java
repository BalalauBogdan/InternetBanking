package com.example.internetbankingfrontend.controller.register;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
    public void initialize() {
        Platform.runLater(() -> RegisterUsername.getParent().requestFocus());
    }

    @FXML
    public void RegisterCustomer() {
        try {
            String username = RegisterUsername.getText();
            String password = RegisterPassword.getText();
            String confirmPassword = RegisterConfirmPassword.getText();
            String firstName = RegisterFirstName.getText();
            String lastName = RegisterLastName.getText();
            String phoneNumber = RegisterPhoneNumber.getText();

            if (isInputValid(username, password, confirmPassword, firstName, lastName, phoneNumber)) {
                if (password.equals(confirmPassword)) {
                    String jsonInputString = createJsonString(username, password, firstName, lastName, phoneNumber);
                    int responseCode = sendRegistrationRequest(jsonInputString);

                    if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                        showAlert(Alert.AlertType.INFORMATION, "Success", "Customer adăugat cu succes.");
                    } else {
                        showAlert(Alert.AlertType.WARNING, "Used Username", "Username inregistrat anterior.");
                    }
                } else {
                    showAlert(Alert.AlertType.WARNING, "Eroare Parole", "Parolele nu corespund.");
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Eroare", "Completeaza toate campurile.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isInputValid(String username, String password, String confirmPassword, String firstName, String lastName, String phoneNumber) {
        return !username.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()
                && !firstName.isEmpty() && !lastName.isEmpty() && !phoneNumber.isEmpty();
    }

    private String createJsonString(String username, String password, String firstName, String lastName, String phoneNumber) {
        return "{"
                + "\"username\": \"" + username + "\","
                + "\"password\": \"" + password + "\","
                + "\"firstname\": \"" + firstName + "\","
                + "\"lastname\": \"" + lastName + "\","
                + "\"phoneNumber\": \"" + phoneNumber + "\""
                + "}";
    }

    private int sendRegistrationRequest(String jsonInputString) throws Exception {
        URL url = new URL("http://localhost:8080/api/customer/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        return con.getResponseCode();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}

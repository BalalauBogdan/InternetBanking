package com.example.internetbankingfrontend.controller.customer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class ShowCustomerController {
    @FXML
    private TextField CustomerIDField;

    @FXML
    private TextField usernameField;
    @FXML
    private Label usernameLabel;

    @FXML
    private TextField passwordField;
    @FXML
    private Label passwordLabel;

    @FXML
    private TextField firstnameField;
    @FXML
    private Label firstnameLabel;

    @FXML
    private TextField lastnameField;
    @FXML
    private Label lastnameLabel;

    @FXML
    private TextField phonenumberField;
    @FXML
    private Label phonenumberLabel;

    @FXML
    private TextField IbanField;
    @FXML
    private Label IbanLabel;

    @FXML
    public void initialize() {
        Platform.runLater(() -> CustomerIDField.getParent().requestFocus());
    }

    @FXML
    public void findCustomer() {
        String inputCustomerID = CustomerIDField.getText();
        if (inputCustomerID.isEmpty()) {
            showWarningMessage("Warning", "Customer ID is required");
            return;
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/customer/" + inputCustomerID))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(response -> {
                    Platform.runLater(() -> handleResponse(response));
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    Platform.runLater(() -> showWarningMessage("Error", "Failed to retrieve customer information."));
                    return null;
                });
    }

    private void handleResponse(String response) {
        JSONObject jsonResponse = new JSONObject(response);

        if (jsonResponse.has("data") && !jsonResponse.isNull("data")) {
            JSONObject customer = jsonResponse.getJSONObject("data");
            usernameField.setText(customer.getString("username"));
            passwordField.setText(customer.getString("password"));
            firstnameField.setText(customer.getString("firstname"));
            lastnameField.setText(customer.getString("lastname"));
            phonenumberField.setText(customer.getString("phoneNumber"));
            IbanField.setText(customer.getString("iban"));

            usernameLabel.setVisible(true);
            usernameField.setVisible(true);
            passwordLabel.setVisible(true);
            passwordField.setVisible(true);
            firstnameLabel.setVisible(true);
            firstnameField.setVisible(true);
            lastnameLabel.setVisible(true);
            lastnameField.setVisible(true);
            phonenumberLabel.setVisible(true);
            phonenumberField.setVisible(true);
            IbanLabel.setVisible(true);
            IbanField.setVisible(true);
        } else {
            showWarningMessage("Warning", "Customer ID not found. Please enter a valid ID.");
        }
    }

    private void showWarningMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

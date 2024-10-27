package com.example.internetbankingfrontend.controller.customer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeleteCustomerController {
    @FXML
    TextField CustomerUsername;

    @FXML
    public void DeleteCustomer() {
        String inputCustomerUsername = CustomerUsername.getText();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/customer/delete/" + inputCustomerUsername))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    int statusCode = response.statusCode();

                    Platform.runLater(() -> {
                        if (statusCode == 200) {
                            showConfirmationMessage(Alert.AlertType.INFORMATION, "Delete", "Customer was deleted successfully");
                        } else if (statusCode == 404) {
                            showConfirmationMessage(Alert.AlertType.ERROR, "Delete", "Username not found");
                        } else {
                            showConfirmationMessage(Alert.AlertType.ERROR, "Delete", "An error occurred: " + statusCode);
                        }
                    });
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    return null;
                });
    }

    private void showConfirmationMessage(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
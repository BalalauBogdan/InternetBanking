package com.example.internetbankingfrontend.controller.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeleteCustomerController {
    @FXML
    TextField CustomerUsername;

    @FXML
    public void DeleteCustomer(){
        String inputCustomerUsername = CustomerUsername.getText();


        // Creare cerere DELETE
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/customer/delete/" + inputCustomerUsername))
                .POST(HttpRequest.BodyPublishers.noBody())  // dacă serverul acceptă POST pentru delete
                .build();

        // Trimitere cerere asincron și preluarea răspunsului
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(response -> {
                    System.out.println("Response from server: " + response);  // Log răspuns complet
                    showConfirmationMessage(Alert.AlertType.INFORMATION, "Delete","Customer was deleted succesfully");
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
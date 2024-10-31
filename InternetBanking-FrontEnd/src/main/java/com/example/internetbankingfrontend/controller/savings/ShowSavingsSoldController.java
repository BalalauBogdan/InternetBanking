package com.example.internetbankingfrontend.controller.savings;

import com.example.internetbankingfrontend.controller.login.LoginController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ShowSavingsSoldController {
    @FXML
    private TextField SavingsSoldField;
    @FXML
    public void initialize() {
        Platform.runLater(() -> SavingsSoldField.getParent().requestFocus());
    }
    @FXML
    public void ShowSavingsSold() {
        try {
            String inputUsername = LoginController.user.getUsername();

            // Configurarea cererii HTTP cu GET
            URL url = new URL("http://localhost:8080/api/customer/" + inputUsername + "/savings/balance");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Obține răspunsul
            int code = con.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println("Response JSON: " + response);

                    // Prelucrarea răspunsului JSON
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    double sold = jsonResponse.getDouble("data");
                    System.out.println("Sold: " + sold);

                    SavingsSoldField.setText(String.valueOf(sold));
                }
            } else {
                System.out.println("Eroare la obținerea soldului: " + code);
                showConfirmationMessage(Alert.AlertType.ERROR, "Error", "Failed to fetch balance. HTTP code: " + code);
            }

        } catch (Exception e) {
            e.printStackTrace();
            showConfirmationMessage(Alert.AlertType.ERROR, "Error", "An error occurred while fetching the balance.");
        }
    }

    private void showConfirmationMessage(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

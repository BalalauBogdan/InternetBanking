package com.example.internetbankingfrontend.controller.sold;

import com.example.internetbankingfrontend.controller.login.LoginController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ShowSoldController {
    @FXML
    private TextField SoldField;
    @FXML
    public void initialize() {
        Platform.runLater(() -> SoldField.getParent().requestFocus());
    }
    @FXML
    public void ShowSold(){
        try {

            String inputUsername = LoginController.user.getUsername();

            // Creează JSON-ul pentru cerere
            String jsonInputString = "{\n" +
                    "\"username\": \"" + inputUsername + "\"\n" +
                    "}";

            // Configurarea cererii HTTP
            URL url = new URL("http://localhost:8080/api/customer/sold");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // Trimite cererea
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Obține răspunsul
            int code = con.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println("Response JSON: " + response.toString());

                    // Prelucrarea răspunsului JSON
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    double sold = jsonResponse.getDouble("data");
                    System.out.println("Sold: " + sold);


                    SoldField.setText(String.valueOf(sold));

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

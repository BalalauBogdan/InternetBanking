package com.example.internetbankingfrontend.controller.savings;

import com.example.internetbankingfrontend.controller.login.LoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WithdrawSavingsController {
    @FXML
    private TextField withdrawSavingsSumField;
    @FXML
    private void withdrawSavingSum(){
        try {
            String inputUsername = LoginController.user.getUsername();
            double inputWithdrawAmount = Double.parseDouble(withdrawSavingsSumField.getText());

            URL url = new URL("http://localhost:8080/api/customer/" + inputUsername + "/savings/withdraw?amount=" + inputWithdrawAmount);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

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

                    // Prelucrarea răspunsului
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    String message = jsonResponse.getString("message");
                    showConfirmationMessage(Alert.AlertType.INFORMATION, "Success", "Deposit successful: " + message);

                }
            } else {
                System.out.println("Eroare la efectuarea depozitului: " + code);
                showConfirmationMessage(Alert.AlertType.ERROR, "Error", "Failed to deposit. HTTP code: " + code);
            }

        } catch (Exception e) {
            e.printStackTrace();
            showConfirmationMessage(Alert.AlertType.ERROR, "Error", "An error occurred while making the deposit.");
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

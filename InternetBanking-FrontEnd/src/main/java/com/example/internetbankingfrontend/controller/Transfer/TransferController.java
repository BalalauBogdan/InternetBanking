package com.example.internetbankingfrontend.controller.Transfer;

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
import java.net.URL;

public class TransferController {
    @FXML
    TextField TransferRecieverIban;
    @FXML
    TextField TransferSum;
    @FXML
    public void initialize() {
        Platform.runLater(() -> TransferRecieverIban.getParent().requestFocus());
    }

    @FXML
    public void TransferSumofMoney() {
        try {
            // Datele pentru transfer
            String inputSenderIban = LoginController.user.getIban();
            String inputReceiverIban = TransferRecieverIban.getText();
            double inputTransferSum = Double.parseDouble(TransferSum.getText());
            System.out.println("Sender IBAN: " + inputSenderIban);
            System.out.println("Receiver IBAN: " + inputReceiverIban);
            System.out.println("Transfer Sum: " + inputTransferSum);

            // Formatarea datelor într-un JSON corect
            String jsonInputString = "{\n" +
                    "\"senderIban\": \"" + inputSenderIban + "\",\n" +
                    "\"receiverIban\": \"" + inputReceiverIban + "\",\n" +
                    "\"amount\": " + inputTransferSum + "\n" +
                    "}";

            // Configurarea conexiunii HTTP
            URL url = new URL("http://localhost:8080/api/transfer/initiate");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // Trimiterea JSON-ului
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Procesarea răspunsului
            int code = con.getResponseCode();
            System.out.println(code);
            if (code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_CREATED) {
                // Transfer reușit
                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    System.out.println(jsonResponse);
                    this.showConfirmationMessage(Alert.AlertType.INFORMATION, "Transfer", jsonResponse.getString("message"));
                    LoginController.user.setAmount(inputTransferSum);
                }
            } else if (code == HttpURLConnection.HTTP_NOT_FOUND) {
                // Răspunsurile 404 pentru IBAN invalid sau fonduri insuficiente
                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"))) {
                    StringBuilder errorResponse = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        errorResponse.append(responseLine.trim());
                    }
                    JSONObject jsonResponse = new JSONObject(errorResponse.toString());
                    this.showConfirmationMessage(Alert.AlertType.WARNING, "Transfer Error", jsonResponse.getString("message"));
                }
            } else {
                // Orice alt cod de eroare
                this.showConfirmationMessage(Alert.AlertType.ERROR, "Transfer Error", "A apărut o eroare neașteptată. Cod eroare: " + code);
            }

        } catch (Exception e) {
            e.printStackTrace();
            this.showConfirmationMessage(Alert.AlertType.ERROR, "Transfer Error", "A apărut o eroare internă: " + e.getMessage());
        }
        TransferRecieverIban.setText("");
        TransferSum.setText("");
    }

    private void showConfirmationMessage(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

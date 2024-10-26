package com.example.internetbankingfrontend.controller.Withdraw;

import com.example.internetbankingfrontend.controller.login.LoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WithdrawController {
    @FXML
    TextField WithdrawSum;
    @FXML
    public void WithdrawSumofMoney(){
        try {
            String inputUsername = LoginController.user.getUsername();
            double inputSumofMoney = Double.parseDouble(WithdrawSum.getText());

            System.out.println(inputSumofMoney);

            String jsonInputString = "{\n" +
                    "\"username\": \"" + inputUsername + "\",\n" +
                    "\"amount\": " + inputSumofMoney + "\n" +
                    "}";


            URL url = new URL("http://localhost:8080/api/customer/withdraw");
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
                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }

                    System.out.println("Response: " + response);
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    this.showConfirmationMessage(Alert.AlertType.INFORMATION, "Confimation", jsonResponse.getString("message"));
                    LoginController.user.setAmount(inputSumofMoney);
                }


            } else {
                System.out.println("Eroare" + code);
                if(code==400){
                    this.showConfirmationMessage(Alert.AlertType.ERROR,"Error", "Insufficient funds");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            this.showConfirmationMessage(Alert.AlertType.ERROR, "Error", "Please fill in all the fields");
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

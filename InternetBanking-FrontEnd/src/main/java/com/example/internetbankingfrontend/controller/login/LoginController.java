package com.example.internetbankingfrontend.controller.login;

import com.example.internetbankingfrontend.InternetBankingApplication;
import com.example.internetbankingfrontend.entity.Customer;
import javafx.fxml.FXML;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONObject;

public class LoginController {
    public static Customer user = new Customer();
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    public void Login() {
        try {
            String inputUsername = username.getText();
            String inputPassword = password.getText();

            String jsonInputString = "{\"username\":\"" + inputUsername + "\",\"password\":\"" + inputPassword + "\"}";
            URL url=new URL("http://localhost:8080/api/customer/login");
            HttpURLConnection con= (HttpURLConnection) url.openConnection();

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

                    System.out.println("Response: " + response.toString());

                    // Dacă răspunsul este în format JSON, îl poți parsa folosind org.json.JSONObject
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    System.out.println("Message: " + jsonResponse.getString("message"));
                    // Aici poți lucra cu răspunsul, de exemplu extrage datele din JSON
                    JSONObject customer = jsonResponse.getJSONObject("data");

                    user.setUsername(customer.getString("username"));
                    user.setPassword(customer.getString("password"));
                    user.setFirstname(customer.getString("firstname"));
                    user.setLastname(customer.getString("lastname"));
                    user.setIban(customer.getString("iban"));
                    user.setId(customer.getInt("id"));
                    user.setRole(customer.getString("role"));
                    user.setPhoneNumber(customer.getString("phoneNumber"));



                    FXMLLoader fxmlLoader = new FXMLLoader(InternetBankingApplication.class.getResource("User-view.fxml"));
                    Parent root = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("User Menu");
                    stage.setHeight(500);
                    stage.setWidth(500);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initOwner(stage.getOwner());
                    stage.showAndWait();

                    System.out.println(user);
                }



            } else {
                System.out.println("Eroare la autentificare" + code);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.showConfirmationMessage(Alert.AlertType.ERROR,"Error","Please fill in all the fields");
        }

    }
    private void showConfirmationMessage(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void Register() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(InternetBankingApplication.class.getResource("Register-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Register");
            stage.setHeight(500);
            stage.setWidth(500);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(stage.getOwner());
            stage.showAndWait();

    }
}

package com.example.internetbankingfrontend.controller.customer;

import javafx.fxml.FXML;
import netscape.javascript.JSObject;
import org.w3c.dom.Text;
import org.json.JSONObject;
import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
    public void findCustomer() {

        String inputCustomerID=CustomerIDField.getText();
        if(inputCustomerID.isEmpty())
            return;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/customer/" + inputCustomerID))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(response -> {
                    System.out.println(response);
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONObject customer = jsonResponse.getJSONObject("data");
                    usernameField.setText(customer.getString("username"));
                    passwordField.setText(customer.getString("password"));
                    firstnameField.setText(customer.getString("firstname"));
                    lastnameField.setText(customer.getString("lastname"));
                    phonenumberField.setText(customer.getString("phoneNumber"));

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
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    return null;
                });
   }
}

package com.example.internetbankingfrontend.controller.customer;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddCustomerController {
    @FXML
    private TextField CustomerUsername;
    @FXML
    private TextField CustomerPassword;
    @FXML
    private TextField CustomerFirstName;
    @FXML
    private TextField CustomerLastName;
    @FXML
    private TextField CustomerPhoneNumber;
    @FXML
    public void AddCustomer(){
        try {
            String inputCustomerUsername = CustomerUsername.getText();
            String inputCustomerPassword = CustomerPassword.getText();
            String inputCustomerFirstName = CustomerFirstName.getText();
            String inputCustomerLastName = CustomerLastName.getText();
            String inputCustomerPhoneNumber = CustomerPhoneNumber.getText();

            String jsonInputString = "{"
                    + "\"username\": \"" + inputCustomerUsername + "\","
                    + "\"password\": \"" + inputCustomerPassword + "\","
                    + "\"firstname\": \"" + inputCustomerFirstName + "\","
                    + "\"lastname\": \"" + inputCustomerLastName + "\","
                    + "\"phoneNumber\": \"" + inputCustomerPhoneNumber + "\""
                    + "}";
            URL url=new URL("http://localhost:8080/api/customer/");
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
                System.out.println("Customer adăugat cu succes.");
            } else {
                System.out.println("Eroare la adăugarea customer-ului: " + code);
            }


        } catch(Exception e){
            e.printStackTrace();
        }
    }
}

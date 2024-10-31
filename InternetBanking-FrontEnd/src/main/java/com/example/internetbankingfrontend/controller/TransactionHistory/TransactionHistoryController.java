package com.example.internetbankingfrontend.controller.TransactionHistory;

import com.example.internetbankingfrontend.controller.login.LoginController;
import com.example.internetbankingfrontend.entity.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Objects;

public class TransactionHistoryController {

    @FXML
    private TableView<Transaction> transactionTable;
    @FXML
    private TableColumn<Transaction, String> senderColumn;
    @FXML
    private TableColumn<Transaction, String> recipientColumn;
    @FXML
    private TableColumn<Transaction, Double> amountColumn;
    @FXML
    private TableColumn<Transaction, Void> statusColumn;

    private ObservableList<Transaction> transactionList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        // Setăm câmpurile pentru coloanele expeditor, destinatar și sumă
        senderColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));
        recipientColumn.setCellValueFactory(new PropertyValueFactory<>("recipient"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));


        // Adăugăm lista de tranzacții în tabel
        transactionTable.setItems(transactionList);
        loadtransactionshistory();
    }
    private void loadtransactionshistory() {
        String receiverIban = LoginController.user.getIban();
        String urlString = "http://localhost:8080/api/transfer/all/" + receiverIban;

        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    JSONArray data = jsonResponse.getJSONArray("data");
                    System.out.println(data);
                    transactionList.clear();
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject obj = data.getJSONObject(i);
                        System.out.println(obj);

                        Transaction transaction = new Transaction(
                                obj.getInt("id"),                     // id ca Integer
                                obj.getString("senderIban"),           // senderIban ca String
                                obj.getString("receiverIban"),         // receiverIban ca String
                                obj.getDouble("amount"),               // amount ca Double
                                obj.getString("status")                // status ca String
                        );

                        transactionList.add(transaction);
                    }

                }
            } else {

                System.out.println("Eroare la încărcarea tranzacțiilor: cod " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    
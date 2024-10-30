package com.example.internetbankingfrontend.controller.Transaction;

import com.example.internetbankingfrontend.controller.login.LoginController;
import com.example.internetbankingfrontend.entity.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TransactionController {

    @FXML
    private TableView<Transaction> transactionTable;
    @FXML
    private TableColumn<Transaction, String> senderColumn;
    @FXML
    private TableColumn<Transaction, String> recipientColumn;
    @FXML
    private TableColumn<Transaction, Double> amountColumn;
    @FXML
    private TableColumn<Transaction, Void> actionColumn;

    private ObservableList<Transaction> transactionList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        // Setăm câmpurile pentru coloanele expeditor, destinatar și sumă
        senderColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));
        recipientColumn.setCellValueFactory(new PropertyValueFactory<>("recipient"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        actionColumn.setPrefWidth(200);

        // Configurăm coloana de acțiuni pentru a afișa butoanele Accept și Decline
        actionColumn.setCellFactory(column -> new TableCell<>() {
            private final Button acceptButton = new Button("Accept");
            private final Button declineButton = new Button("Decline");
            private final GridPane grid = new GridPane();// Spacing de 5

            {
                acceptButton.setPrefWidth(50);
                declineButton.setPrefWidth(50);
                acceptButton.setPrefHeight(20);
                declineButton.setPrefHeight(20);
                grid.setHgap(0);
                grid.setVgap(0);
                grid.add(acceptButton, 0, 0); // Plasăm butonul Accept în prima coloană
                grid.add(declineButton, 1, 0); // Plasăm butonul Decline în a doua coloană

                // Aliniem GridPane la centrul celulei
                grid.setAlignment(javafx.geometry.Pos.CENTER);
                acceptButton.setStyle("  -fx-background-color: #DFF2EB; -fx-text-fill: black;");
                acceptButton.setOnMouseEntered(event -> {
                    acceptButton.setStyle("  -fx-background-color: #65B741; -fx-text-fill: white; -fx-font-weight: bold;"); // Culoare mai închisă
                });
                acceptButton.setOnMouseExited(event -> {
                    acceptButton.setStyle("  -fx-background-color: #DFF2EB; -fx-text-fill: black; -fx-font-weight: bold;"); // Culoare inițială
                });
                declineButton.setStyle(" -fx-background-color: #DFF2EB; -fx-text-fill: black;");
                declineButton.setOnMouseEntered(event -> {
                    declineButton.setStyle("  -fx-background-color: #FF0000; -fx-text-fill: white; -fx-font-weight: bold;"); // Culoare mai închisă
                });
                declineButton.setOnMouseExited(event -> {
                    declineButton.setStyle(" -fx-background-color: #DFF2EB; -fx-text-fill: black; -fx-font-weight: bold;"); // Culoare inițială
                });
                acceptButton.setOnAction(event -> {
                    Transaction transaction = getTableView().getItems().get(getIndex());
                    handleAccept(transaction);
                });
                declineButton.setOnAction(event -> {
                    Transaction transaction = getTableView().getItems().get(getIndex());
                    handleDecline(transaction);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(grid);
                }
            }
        });

        // Adăugăm lista de tranzacții în tabel
        transactionTable.setItems(transactionList);
        loadPendingTransactions();
    }

    private void loadPendingTransactions() {
        String receiverIban = LoginController.user.getIban();
        String urlString = "http://localhost:8080/api/transfer/pending/" + receiverIban;

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

    private void handleAccept(Transaction transaction) {
        System.out.println("Tranzacție acceptată: " + transaction);
        // Logica de acceptare
    }

    private void handleDecline(Transaction transaction) {
        System.out.println("Tranzacție refuzată: " + transaction);
        // Logica de refuz
    }
}

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
    }
}
    
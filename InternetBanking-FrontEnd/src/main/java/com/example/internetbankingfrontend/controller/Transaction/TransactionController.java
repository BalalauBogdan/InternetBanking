package com.example.internetbankingfrontend.controller.Transaction;

import com.example.internetbankingfrontend.entity.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

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

        // Configurăm coloana de acțiuni pentru a afișa butoanele Accept și Decline
        actionColumn.setCellFactory(column -> new TableCell<>() {
            private final Button acceptButton = new Button("Accept");
            private final Button declineButton = new Button("Decline");
            private final HBox buttons = new HBox(5, acceptButton, declineButton); // Spacing de 5

            {
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
                    setGraphic(buttons);
                }
            }
        });

        populateTableWithSampleData();
        // Adăugăm lista de tranzacții în tabel
        transactionTable.setItems(transactionList);
    }
    private void populateTableWithSampleData() {
        // Adăugăm câteva tranzacții de probă
        transactionList.addAll(
                new Transaction("Alice", "Bob", 100.0),
                new Transaction("Carol", "Dave", 200.5)
        );
    }

    @FXML
    private void handleAccept(Transaction transaction) {
        System.out.println("Tranzacție acceptată: " + transaction);
        // Poți adăuga logica de acceptare aici
    }

    @FXML
    private void handleDecline(Transaction transaction) {
        System.out.println("Tranzacție refuzată: " + transaction);
        // Poți adăuga logica de refuz aici
    }
}

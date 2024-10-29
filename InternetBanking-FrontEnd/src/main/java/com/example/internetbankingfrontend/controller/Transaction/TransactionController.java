package com.example.internetbankingfrontend.controller.Transaction;

import com.example.internetbankingfrontend.entity.Transaction;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.table.TableColumn;
import javax.swing.text.TabableView;
import javax.swing.text.TableView;

public class TransactionController {
    @FXML
    private TableView transactionTable;
    @FXML
    private TableColumn senderColumn;
    @FXML
    private TableColumn recipientColumn;
    @FXML
    private TableColumn amountColumn;
    @FXML
    private TableColumn actionColumn;

    private ObservableList<Transaction> transactionList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
//        senderColumn.setCellValueFactory
    }
}

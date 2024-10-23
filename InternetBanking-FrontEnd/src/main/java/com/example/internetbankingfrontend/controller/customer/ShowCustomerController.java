package com.example.internetbankingfrontend.controller.customer;

import javafx.fxml.FXML;
import org.w3c.dom.Text;

import java.awt.*;
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

//    @FXML
//    public void findCustomer() {
//        try {
//            Customer book = this.bookService.getBookById(Integer.valueOf(bookIdField.getText()));
//            this.titleField.setText(book.getTitle());
//            this.authorField.setText(book.getAuthor());
//            this.descriptionField.setText(book.getDescription());
//            this.priceField.setText(book.getPrice().toString());
//
//            this.titleLabel.setVisible(true);
//            this.titleField.setVisible(true);
//            this.authorLabel.setVisible(true);
//            this.authorField.setVisible(true);
//            this.descriptionLabel.setVisible(true);
//            this.descriptionField.setVisible(true);
//            this.priceLabel.setVisible(true);
//            this.priceField.setVisible(true);
//        } catch (Exception e) {
//            this.showConfirmationMessage(Alert.AlertType.ERROR,"Error","Book not found with id: " + this.bookIdField.getText());
//        }
//    }
//
//    private void showConfirmationMessage(Alert.AlertType alertType, String title, String message) {
//        Alert alert = new Alert(alertType);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
}

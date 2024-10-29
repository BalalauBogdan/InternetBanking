package com.example.internetbankingfrontend;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class InternetBankingApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InternetBankingApplication.class.getResource("homepage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 200);
        stage.setTitle("Main Page");
        String css = Objects.requireNonNull(getClass().getResource("/style/style.css")).toExternalForm();
        scene.getStylesheets().add(css);
        Platform.runLater(() -> scene.getRoot().requestFocus());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
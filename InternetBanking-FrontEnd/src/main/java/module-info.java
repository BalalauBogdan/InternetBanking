module com.example.internetbankingfrontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.internetbankingfrontend to javafx.fxml;
    exports com.example.internetbankingfrontend;
    exports com.example.internetbankingfrontend.controller;
    opens com.example.internetbankingfrontend.controller to javafx.fxml;
    exports com.example.internetbankingfrontend.controller.login;
    opens com.example.internetbankingfrontend.controller.login to javafx.fxml;
}
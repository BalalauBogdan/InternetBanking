module com.example.internetbankingfrontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.net.http;
    requires jdk.jsobject;
    requires org.json;

    opens com.example.internetbankingfrontend to javafx.fxml;
    exports com.example.internetbankingfrontend;
    exports com.example.internetbankingfrontend.controller;
    opens com.example.internetbankingfrontend.controller to javafx.fxml;
    exports com.example.internetbankingfrontend.controller.login;
    opens com.example.internetbankingfrontend.controller.login to javafx.fxml;
    opens com.example.internetbankingfrontend.controller.admin to javafx.fxml;
    exports com.example.internetbankingfrontend.controller.admin;
    opens com.example.internetbankingfrontend.controller.customer to javafx.fxml;
    opens com.example.internetbankingfrontend.controller.register to javafx.fxml;
    opens com.example.internetbankingfrontend.controller.User to javafx.fxml;
    exports com.example.internetbankingfrontend.controller.User;
    opens com.example.internetbankingfrontend.controller.Deposit to javafx.fxml;
    exports com.example.internetbankingfrontend.controller.Deposit;
    opens com.example.internetbankingfrontend.controller.Withdraw to javafx.fxml;
    exports com.example.internetbankingfrontend.controller.Withdraw;
    opens com.example.internetbankingfrontend.controller.sold to javafx.fxml;
    exports com.example.internetbankingfrontend.controller.sold;
    opens com.example.internetbankingfrontend.controller.Transfer to javafx.fxml;
    exports com.example.internetbankingfrontend.controller.Transfer;
    opens com.example.internetbankingfrontend.entity to javafx.fxml;
    exports com.example.internetbankingfrontend.entity;
    opens com.example.internetbankingfrontend.controller.Transaction to javafx.fxml;
    exports com.example.internetbankingfrontend.controller.Transaction;
    opens com.example.internetbankingfrontend.controller.savings to javafx.fxml;
    exports com.example.internetbankingfrontend.controller.savings;

}
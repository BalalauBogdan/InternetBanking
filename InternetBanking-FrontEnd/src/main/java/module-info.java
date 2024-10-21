module com.example.internetbankingfrontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.internetbankingfrontend to javafx.fxml;
    exports com.example.internetbankingfrontend;
}
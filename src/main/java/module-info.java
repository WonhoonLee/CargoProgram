module com.example.cargoprogram {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.example.cargoprogram to javafx.fxml;
    exports com.example.cargoprogram;
}
module com.example.demo200000 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.demo200000 to javafx.fxml;
    exports com.example.demo200000;
    exports com.example.demo200000.additionalMethods;
    opens com.example.demo200000.additionalMethods to javafx.fxml;
    exports com.example.demo200000.enity;
    opens com.example.demo200000.enity to javafx.fxml;
    exports com.example.demo200000.server;
    opens com.example.demo200000.server to javafx.fxml;
    exports com.example.demo200000.window;
    opens com.example.demo200000.window to javafx.fxml;
}
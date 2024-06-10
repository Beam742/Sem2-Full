module com.example.yok {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.yok to javafx.fxml;
    exports com.example.yok;
}
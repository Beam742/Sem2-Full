module com.example.codelab {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.codelab to javafx.fxml;
    exports com.example.codelab;
}
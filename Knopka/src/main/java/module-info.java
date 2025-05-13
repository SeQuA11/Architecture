module org.example.knopka {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.knopka to javafx.fxml;
    exports org.example.knopka;
}
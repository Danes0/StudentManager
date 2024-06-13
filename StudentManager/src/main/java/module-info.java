module org.example.studentmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;


    opens org.example.studentmanager to javafx.fxml;
    exports org.example.studentmanager;
}
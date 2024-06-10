module com.librarysystem {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.main to javafx.fxml;
    exports com.main;
}

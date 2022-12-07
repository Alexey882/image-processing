module com.image.processing {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;

    opens com.image.processing to javafx.fxml;
    exports com.image.processing;
}
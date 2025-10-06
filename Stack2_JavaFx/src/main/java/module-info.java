module Stack2_JavaFx {
    requires javafx.controls;
    requires javafx.fxml;
    opens Ej2 to javafx.fxml;
    exports Ej2;
}

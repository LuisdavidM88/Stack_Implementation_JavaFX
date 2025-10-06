module stackfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens Ej1 to javafx.fxml;
    exports Ej1;
}

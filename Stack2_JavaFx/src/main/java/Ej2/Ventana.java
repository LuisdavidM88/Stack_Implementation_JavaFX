package Ej2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

// Controlador que maneja la interfaz
public class Ventana {

    // area de texto donde se pega el codigo
    @FXML private TextArea txtCodigo;

    // boton para ejecutar la evaluacion
    @FXML private Button btnEvaluar;

    // label que muestra si esta balanceado o no
    @FXML private Label lblRes;

    // labels que muestran la cantidad de aperturas
    @FXML private Label lblParen;   // (
    @FXML private Label lblLlaves;  // {
    @FXML private Label lblCorchs;  // [

    // lista que muestra el paso a paso
    @FXML private ListView<String> lstLog;

    // objeto con la logica de balanceo
    private final Balanceador balanceador = new Balanceador();

    // lista observable para actualizar la vista
    private final ObservableList<String> logItems = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // conectamos la lista visual con los datos
        lstLog.setItems(logItems);

        // boton ejecuta el metodo evaluarCodigo
        btnEvaluar.setOnAction(e -> evaluarCodigo());

        // atajo ctrl enter ejecuta el metodo
        txtCodigo.setOnKeyPressed(ev -> {
            switch (ev.getCode()) {
                case ENTER:
                    if (ev.isControlDown()) evaluarCodigo();
                    break;
            }
        });
    }

    // metodo principal que evalua el texto
    private void evaluarCodigo() {
        String texto = txtCodigo.getText();
        if (texto == null) texto = "";

        // mandamos el texto al balanceador
        Balanceador.Resultado r = balanceador.evaluar(texto);

        // mostramos la cantidad de aperturas
        lblParen.setText("Aperturas '(' " + r.aperturasParentesis);
        lblLlaves.setText("Aperturas '{' " + r.aperturasLlaves);
        lblCorchs.setText("Aperturas '[' " + r.aperturasCorchetes);

        // mostramos si esta balanceado
        lblRes.setText(r.balanceado ? "Balanceado ✅" : "No balanceado ❌");

        // cargamos el detalle en la lista
        cargarLog(r.detalle);
    }

    // metodo que carga el detalle en la lista
    private void cargarLog(String detalle) {
        logItems.clear();
        for (String linea : detalle.split("\n")) {
            if (!linea.isBlank()) logItems.add(linea);
        }
    }
}

package Ej1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.IntegerStringConverter;

import java.util.function.UnaryOperator;

public class Ventana {

    // controles de la interfaz
    @FXML private TextField txtCodigo;
    @FXML private TextField txtTitulo;
    @FXML private TextArea  txtMensaje;

    @FXML private Button btnPush;   // boton insertar
    @FXML private Button btnPop;    // boton eliminar
    @FXML private Button btnPeek;   // boton ver cima

    @FXML private Label lblCantidad;     // muestra cantidad de elementos
    @FXML private ListView<String> lstPila; // lista con el contenido de la pila

    // pila donde se guardan las publicaciones
    private final Pila pila = new Pila();
    // lista observable que actualiza la vista
    private final ObservableList<String> items = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // validacion solo numeros enteros en el campo codigo
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getControlNewText();
            if (text.matches("-?\\d*")) {
                return change;
            }
            return null;
        };
        txtCodigo.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), null, filter));
        txtCodigo.setPromptText("Solo enteros (ej 123)");

        // vinculamos lista visual con los datos
        lstPila.setItems(items);
        actualizarCantidad();
        refrescarLista();

        // acciones de los botones
        btnPush.setOnAction(e -> onPush());
        btnPop.setOnAction(e -> onPop());
        btnPeek.setOnAction(e -> onPeek());
    }

    private void onPush() {
        // validamos que codigo sea numero
        Integer codigo;
        try {
            codigo = Integer.parseInt(txtCodigo.getText());
        } catch (Exception ex) {
            mostrarError("Dato invalido", "El campo Codigo debe ser un entero");
            return;
        }

        String titulo = txtTitulo.getText();
        String mensaje = txtMensaje.getText();

        // validamos que titulo y mensaje no esten vacios
        if (titulo == null || titulo.isBlank()) {
            mostrarError("Dato faltante", "El campo Titulo no puede estar vacio");
            return;
        }
        if (mensaje == null || mensaje.isBlank()) {
            mostrarError("Dato faltante", "El campo Mensaje no puede estar vacio");
            return;
        }

        // creamos la publicacion y la agregamos a la pila
        Publicacion p = new Publicacion(codigo, titulo.trim(), mensaje.trim());
        pila.push(p);

        // limpiamos campos y actualizamos
        limpiarInputs();
        actualizarCantidad();
        refrescarLista();
    }

    private void onPop() {
        try {
            // quitamos elemento de la cima
            Publicacion eliminado = pila.pop();
            actualizarCantidad();
            refrescarLista();
            mostrarInfo("Elemento eliminado", eliminado.toString());
        } catch (Exception e) {
            mostrarAdvertencia("Pila vacia", "No hay elementos para eliminar");
        }
    }

    private void onPeek() {
        try {
            // mostramos elemento de la cima sin eliminar
            Publicacion tope = pila.peek();
            mostrarInfo("Elemento en la cima", tope.toString());
        } catch (Exception e) {
            mostrarAdvertencia("Pila vacia", "No hay elementos en la pila");
        }
    }

    private void actualizarCantidad() {
        // mostramos cantidad de elementos en la pila
        lblCantidad.setText("Elementos en la pila " + pila.size());
    }

    private void refrescarLista() {
        // actualizamos la vista de la pila de tope a fondo
        items.clear();
        String repr = pila.toString();
        for (String linea : repr.split("\n")) {
            if (!linea.isBlank()) items.add(linea);
        }
    }

    private void limpiarInputs() {
        // limpiamos los campos despues de insertar
        txtCodigo.clear();
        txtTitulo.clear();
        txtMensaje.clear();
        txtCodigo.requestFocus();
    }

    // metodos para mostrar alertas
    private void mostrarError(String titulo, String mensaje) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(mensaje);
        a.showAndWait();
    }

    private void mostrarAdvertencia(String titulo, String mensaje) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(mensaje);
        a.showAndWait();
    }

    private void mostrarInfo(String titulo, String mensaje) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(mensaje);
        a.showAndWait();
    }
}

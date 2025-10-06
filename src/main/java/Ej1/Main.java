package Ej1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// clase principal que arranca la app JavaFX
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // cargamos la interfaz desde main.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ej1/main.fxml"));
        Parent root = loader.load();

        // creamos la escena con el contenido
        Scene scene = new Scene(root, 720, 520);

        // configuramos la ventana principal
        stage.setTitle("Ejercicio 1 Pila con JavaFX");
        stage.setScene(scene);
        stage.show(); // mostramos la ventana
    }

    public static void main(String[] args) {
        // inicia la app JavaFX y llama a start
        launch(args);
    }
}

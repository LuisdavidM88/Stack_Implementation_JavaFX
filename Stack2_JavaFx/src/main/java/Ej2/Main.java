package Ej2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// clase principal que arranca la app JavaFX
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // cargamos la interfaz desde el archivo fxml
        Parent root = FXMLLoader.load(getClass().getResource("/Ej2/main.fxml"));

        // creamos la escena con el contenido
        Scene scene = new Scene(root, 880, 600);

        // configuramos la ventana principal
        stage.setTitle("Ejercicio 2 Balanceo de (), {}, []");
        stage.setScene(scene);
        stage.show();
    }

    // metodo main que lanza la aplicacion
    public static void main(String[] args) {
        launch(args);
    }
}

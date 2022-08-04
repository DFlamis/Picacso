
import java.io.IOException;

import Interfaz.VistaOpciones;
import LecturaArchivos.BaseDatos;
import LecturaArchivos.Directory;
import LecturaArchivos.Lector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Principal extends Application {

    @Override
    public void start(Stage principal)
    {
        
        try
        {
            Lector.fileLoader1A( Directory.rootFolder );
            BaseDatos.albunes.add( BaseDatos.defoult );
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        Lector.fileLoader2();

        VistaOpciones vo = new VistaOpciones();

        Scene sc = new Scene(vo.getInicio(),600,600);
        Stage sta = new Stage();
        sta.setTitle("Picacso");
        sta.getIcons().add(new Image( "file:" + Directory.iconFolder + "/Top.png"));
        sta.setScene(sc);
        sta.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
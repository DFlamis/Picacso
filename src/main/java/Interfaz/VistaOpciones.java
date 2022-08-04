package Interfaz;

import java.io.File;
import java.io.IOException;

import Clases.Album;
import Clases.Foto;
import LecturaArchivos.BaseDatos;
import LecturaArchivos.Directory;
import LecturaArchivos.Lector;
import LecturaArchivos.MiHelper;
import LecturaArchivos.NoVolatil;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class VistaOpciones
{
    private VBox root;

    //La ropa de cheerleader

    public VistaOpciones()
    {
        root = new VBox();

        Label title = new Label("Vista: opciones");
        title.setFont(new Font("Times New Roman", 30));

        Label space = new Label(" ");

        Label l_Import = new Label("Importar imagenes");
        l_Import.setFont( new Font("Times New Roman", 15) );

        Label l_Caragar = new Label("Cargar interfaz");
        l_Caragar.setFont( new Font("Times New Roman", 15) );

        Label l_save = new Label("Datos no volatiles");
        l_save.setFont( new Font("Times New Roman", 15) );


        //importar fotos
        Button importThis = new Button("Importar imagenes (localmente)"); //preparar fotos para ser cargadas
        Button importThat = new Button("Importar imagenes"); //Buscar fotos en el equipo

        //Interfaz
        Button photo = new Button("Fotos");
        Button load = new Button("Cargar datos"); //Cargar fotos al sistema

        //Botones datos no volatiles
        Button loadSaved = new Button("Cargar fotos");
        Button saveData = new Button("Guardar fotos");

        root.getChildren().addAll(title,space, l_Import ,importThis,importThat, l_Caragar ,photo,load, l_save ,saveData,loadSaved);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(12);

        load.setOnMouseClicked( (e)  -> {

            Lector.fileLoader2();
        } );

        photo.setOnMouseClicked( (e) -> {
            Fotos ft = new Fotos();

            Scene sc1 = new Scene(ft.getFotos(),600,600);
            Stage fotoPanel = Directory.fotoPanel;
            fotoPanel.setTitle("Fotos");
            fotoPanel.getIcons().add(new Image( "file:" + Directory.iconFolder + "/Top.png"));
            fotoPanel.setScene(sc1);
            fotoPanel.show();
        });

        importThis.setOnMouseClicked( (e) -> {
            try
            {
                Lector.fileLoader1A( Directory.externalFolder );
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }

            File folder = new File( Directory.externalFolder );
            File[] list = folder.listFiles();
            for(File f: list)
            {
                f.delete();
            }

        });

        importThat.setOnMouseClicked( (e) -> {

            File folder = new File( "C:/Users/" + System.getProperty("user.name") + "/Pictures" );            

            ShowFiles.ImagineDragons(folder);

            ShowFiles sf =new ShowFiles();

            Scene sc2 = new Scene( sf.getShowFiles(),600,600 );
            Stage showFiles = new Stage();
            showFiles.setTitle("Buscar Fotos");
            showFiles.getIcons().add(new Image( "file:" + Directory.iconFolder + "/Top.png"));
            showFiles.setScene(sc2);
            showFiles.show();    

        });

        saveData.setOnMouseClicked( (e) -> {
            NoVolatil.saveState( NoVolatil.cahePhoto( BaseDatos.collection ), Directory.fruta ); //Guardar fotos
            NoVolatil.saveState( NoVolatil.cacheAlbum( BaseDatos.albunes ), Directory.leonardo ); //Guardar albunes
        });

        loadSaved.setOnMouseClicked( (e) -> {
            MiHelper.editBurnedData(); //Cargar fotos

            NoVolatil.loadAlbums(); //Cargar albunes
            MiHelper.bananas(); //Asignar albunes a fotos
        });


    }
    
    public VBox getInicio()
    {
        return root;
    }
}
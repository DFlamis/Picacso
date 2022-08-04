package Interfaz;

import Clases.Foto;
import LecturaArchivos.Directory;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class OnlyFoto
{
    private VBox root;

    //Visor
    private HBox titulo;
    private HBox lugar;
    private HBox palabras;
    private HBox labumes;
    private HBox personas;
    
    public OnlyFoto(Foto ft)
    {
        root = new VBox();

        //Visor
        titulo = new HBox();
        lugar = new HBox();
        palabras = new HBox();
        labumes = new HBox();
        personas = new HBox();

        //Variables
        String name = ft.getName();
        String place = ft.getPlace();
        String keyWord = ft.getKeyword();
        String album = ft.getAlbum();
        String people = ft.getPeople();

        ImageView img = new ImageView(new Image("file:"+ Directory.RESOURCE_FOLDER + "/" + name));
        img.setFitHeight(450);
        img.setPreserveRatio(true);

        //Nombre
        Label first_A = new Label("Nombre: ");
        Label first_B = new Label(name);
        first_A.setFont(new Font("Times New Roman", 15));
        first_B.setFont(new Font("Times New Roman", 15));
        titulo.getChildren().addAll(first_A,first_B);
        titulo.setAlignment(Pos.CENTER);

        //Lugar
        Label second_A = new Label("Lugar: ");
        Label second_B = new Label(place);
        second_A.setFont(new Font("Times New Roman", 15));
        second_B.setFont(new Font("Times New Roman", 15));
        lugar.getChildren().addAll(second_A,second_B);
        lugar.setAlignment(Pos.CENTER);

        //Palabras claves
        Label third_A = new Label("KeyWords: ");
        Label third_B = new Label(keyWord);
        third_A.setFont(new Font("Times New Roman", 15));
        third_B.setFont(new Font("Times New Roman", 15));
        palabras.getChildren().addAll(third_A,third_B);
        palabras.setAlignment(Pos.CENTER);

        //albums
        Label fouth_A = new Label("Album: ");
        Label fouth_B = new Label(album);
        fouth_A.setFont(new Font("Times New Roman", 15));
        fouth_B.setFont(new Font("Times New Roman", 15));
        labumes.getChildren().addAll(fouth_A,fouth_B);
        labumes.setAlignment(Pos.CENTER);

        //personas
        Label fifth_A = new Label("Personas: ");
        Label fifth_B = new Label(people);
        fifth_A.setFont(new Font("Times New Roman", 15));
        fifth_B.setFont(new Font("Times New Roman", 15));
        personas.getChildren().addAll(fifth_A,fifth_B);
        personas.setAlignment(Pos.CENTER);

        root.getChildren().addAll(img,titulo,lugar,palabras,labumes,personas);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(8);

    }

    public VBox getOnlyFoto()
    {
        return root;
    }

}

package Interfaz;

import java.util.Arrays;

import Clases.Album;
import Clases.Foto;
import LecturaArchivos.BaseDatos;
import LecturaArchivos.Directory;
import LecturaArchivos.Lector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Fotos
{
    private VBox root;
    private VBox left;
    private VBox right;
    private VBox rightBot;

    private HBox labels;
    private HBox textfields;
    private VBox albunesC;
    private HBox butons;

    private TableView photoCollections;

    public Fotos()
    {
        //Iniciar contenedores
        root = new VBox();
        left = new VBox();
        right = new VBox();
        rightBot = new VBox();

        Label head = new Label("Vista: Fotos");
        head.setFont(new Font("Times New Roman", 30));
        root.getChildren().addAll(head);

        askName();
    }

    public void askName()
    {
        TextField search = new TextField("Busca algo!");
        root.getChildren().addAll(search);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(12);

        
        ShowTable(search);
        // replaceImagineDragons();
    }

    //Table view
    public void ShowTable(TextField search)
    {

        labels = new HBox();
        textfields = new HBox();
        albunesC = new VBox();

        //Botones
        Button replace = new Button("Reemplazar");
        Button delete = new Button("Eliminar");
        Button edit = new Button("Agregar personas o keyWords");
        // Button desca = new Button("Editar descripcion");
        
        //Labels
        Label t_place = new Label("Lugar:");
        Label t_keyWord = new Label("KeyWords:");
        Label t_album = new Label("Album:");
        Label t_people = new Label("Personas");
        Label t_comment = new Label("Comentario:");

        //TextFields
        TextField l_place = new TextField();
        TextField l_keyWord = new TextField();

        TextField l_album = new TextField();
        TextField l_description = new TextField();

        TextField l_people = new TextField();
        TextField l_comment = new TextField();

        photoCollections = new TableView<>();

        // String cazuela = "";

        photoCollections.setOnMouseClicked( (e) -> {
            if( photoCollections.getSelectionModel().isEmpty() )
            {
                System.out.println("Vamos, esto es muy intuitivo");
            }
            else
            {
                int pointer = photoCollections.getSelectionModel().getSelectedIndex();

                l_album.setText( BaseDatos.collection.get(pointer).getAlbum() );
                l_description.setText( BaseDatos.collection.get(pointer).getDescription() );
                
                l_people.setText( BaseDatos.collection.get(pointer).getPeople() );
                l_keyWord.setText( BaseDatos.collection.get(pointer).getKeyword() );

                Directory.cazuela = l_album.getText();
            }

        });

        photoCollections.setRowFactory(tv -> {
            TableRow<Foto> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Foto rowData = row.getItem();

                    Foto ft = Lector.imgLoader(rowData.getName());
                    OnlyFoto of = new OnlyFoto(ft);
                    
                    Scene scn = new Scene(of.getOnlyFoto(),600,600);
                    Stage stg = new Stage();
                    stg.setTitle("Nombre");
                    stg.setScene(scn);
                    stg.show();
                }
            });
            return row ;
        });

        Label empty = new Label("   Aun no hay fotos que mostrar\n¡Prueba importando algunas!");
        empty.setAlignment(Pos.CENTER);
        photoCollections.setPlaceholder(empty);

        TableColumn<Foto, String> name = new TableColumn<Foto, String>("#");
        name.setMinWidth(10);
        name.setCellValueFactory(new PropertyValueFactory<Foto, String>("number"));

        TableColumn<Foto, String> place = new TableColumn<>("Lugar");
        place.setMinWidth(50);
        place.setCellValueFactory(new PropertyValueFactory<>("place"));
  
        TableColumn<Foto, String> album = new TableColumn<>("Album");
        album.setMinWidth(50);
        album.setCellValueFactory(new PropertyValueFactory<>("album"));

        TableColumn<Foto, String> description = new TableColumn<>("Descripcion");
        description.setMinWidth(150);
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Foto, String> people = new TableColumn<>("Personas");
        people.setMinWidth(300);
        people.setCellValueFactory(new PropertyValueFactory<>("people"));

        // ObservableList<Foto> tr = FXCollections.observableArrayList(BaseDatos.collection);
        ObservableList<Foto> tr = FXCollections.observableList( BaseDatos.collection );

        photoCollections.getColumns().addAll(Arrays.asList(name,place,album,description,people));

        //Recibe una lista y un parametro para la comparacion
        FilteredList<Foto> listaFiltrada = new FilteredList<>(tr, b -> true);
        
        //Recibe un String y retorna un valor de verdad segun la coincidencia
        search.textProperty().addListener((Observable, oldValue, newValue) -> {
            listaFiltrada.setPredicate(Foto -> {
                if(newValue == null || newValue.isEmpty())
                {
                    return true;
                }

                //Evitar conflictos con las mayusculas y minusculas
                String minuscula = newValue.toLowerCase();

                //Filtrar por nombre
                if(Foto.getNumber().toLowerCase().indexOf(minuscula) != -1)
                {
                    return true;
                }

                //filtrar por lugar
                else if(Foto.getPlace().toLowerCase().indexOf(minuscula) != -1)
                {
                    return true;
                }

                //Filtrar por palabra
                else if(Foto.getKeyword().toLowerCase().indexOf(minuscula) != -1)
                {
                    return true;
                }

                else if(Foto.getAlbum().toLowerCase().indexOf(minuscula) != -1)
                {
                    return true;
                }

                //Filtrar por persona
                else if(Foto.getPeople().toLowerCase().indexOf(minuscula) != -1)
                {
                    return true;
                }

                //Si no hay coincidencias
                else
                {
                    return false;
                }
            });           
        });

        //Convertirlos en un sortedList para utilizar comparatorProperty()
        SortedList<Foto> listaOrdenada = new SortedList<>(listaFiltrada);

        //Relacionar los comparadores
        listaOrdenada.comparatorProperty().bind(photoCollections.comparatorProperty());

        //Agregar los elementos al table view
        photoCollections.setItems(listaOrdenada);
        

        // photoCollections.getColumns().addAll(Arrays.asList(name, place, people));
        left.getChildren().addAll();
        root.getChildren().addAll(photoCollections);

        // labels = new HBox();
        // textfields = new HBox();
        // albunesC = new VBox();

        // Button replace = new Button("Reemplazar");
        // Button delete = new Button("Eliminar");
        // Button edit = new Button("Agregar personas o keyWords");
        
        // //Labels
        // Label t_place = new Label("Lugar:");
        // Label t_keyWord = new Label("KeyWords:");
        // Label t_album = new Label("Album:");
        // Label t_people = new Label("Personas");
        // Label t_comment = new Label("Comentario:");

        // //TextFields
        // TextField l_place = new TextField();
        // TextField l_keyWord = new TextField();

        // // TextField l_album = new TextField();
        // TextField l_description = new TextField();

        // TextField l_people = new TextField();
        // TextField l_comment = new TextField();

        //Agregar a contenedores

        albunesC.getChildren().addAll(l_album,l_description);

        labels.getChildren().addAll(t_place,t_keyWord,t_album,t_people,t_comment);
        labels.setAlignment(Pos.CENTER);
        labels.setSpacing(80);

        textfields.getChildren().addAll(l_place,l_keyWord,albunesC,l_people,l_comment);
        textfields.setAlignment(Pos.CENTER);
        textfields.setSpacing(40);

        butons = new HBox();
        butons.getChildren().addAll(replace,delete,edit);
        butons.setSpacing(45);
        butons.setAlignment(Pos.CENTER);

        root.getChildren().addAll(butons,labels,textfields);

        //Accion boton remplazar
        replace.setOnMouseClicked( (e) -> {

            int pointer = photoCollections.getSelectionModel().getSelectedIndex() + 1;
            int sub_pointer = photoCollections.getSelectionModel().getSelectedIndex();

            Boolean cond = l_album.getText().isEmpty() && l_comment.getText().isEmpty() && l_keyWord.getText().isEmpty() && l_people.getText().isEmpty() && l_place.getText().isEmpty() && l_description.getText().isEmpty();

            if( photoCollections.getSelectionModel().isEmpty() || cond )
            {
                System.out.println("Algo salio mal xd");
            }
            else
            {
                String tw_place = tr.get( photoCollections.getSelectionModel().getSelectedIndex() ).getPlace();
                String tw_keyWords = tr.get( photoCollections.getSelectionModel().getSelectedIndex() ).getKeyword();
                
                String tw_album = tr.get( photoCollections.getSelectionModel().getSelectedIndex() ).getAlbum();
                String tw_description = tr.get( photoCollections.getSelectionModel().getSelectedIndex() ).getDescription();

                String tw_people = tr.get( photoCollections.getSelectionModel().getSelectedIndex() ).getPeople();
                String tw_comment = tr.get( photoCollections.getSelectionModel().getSelectedIndex() ).getComment();

                String extension = tr.get( photoCollections.getSelectionModel().getSelectedIndex() ).getExtension();
                
                //condiciones
                if( !l_place.getText().isEmpty() )
                {
                    tw_place = l_place.getText();
                }
                if( !l_keyWord.getText().isEmpty() )
                {
                    tw_keyWords = l_keyWord.getText();
                }
                if( !l_people.getText().isEmpty() )
                {
                    tw_people = l_people.getText();
                }

                if( !l_album.getText().isEmpty() )
                {
                    tw_album = l_album.getText();
                }
                if( !l_description.getText().isEmpty() )
                {
                    tw_description = l_description.getText();
                    // tw_description = "Descripcion";
                }

                if( !l_comment.getText().isEmpty() )
                {
                    tw_comment = l_comment.getText();
                }
                
                Album newAlbum = Album.existe(tw_album);

                if( newAlbum == null ) //No existe
                {
                    Album tempAlbum = new Album(tw_album,tw_description);

                    // tr.set( sub_pointer , new Foto(Directory.fileName + pointer + Directory.fileExtension,tw_place,tw_keyWords,tempAlbum,tw_people,tw_comment) );
                
                    BaseDatos.collection.set( sub_pointer , new Foto(Directory.fileName + pointer + extension,tw_place,tw_keyWords,tempAlbum,tw_people,tw_comment));    

                    BaseDatos.albunes.add(tempAlbum);
                }
                else
                {
                    int thisPointer = Album.existeN(tw_album);

                    BaseDatos.collection.set( sub_pointer , new Foto(Directory.fileName + pointer + extension,tw_place,tw_keyWords,newAlbum,tw_people,tw_comment));    
                
                    String desc = BaseDatos.albunes.get(thisPointer).getDescription();
                    newAlbum.setDescription(tw_description);

                    // System.out.println( Directory.cazuela + "-" + tw_album);
                    if( !Directory.cazuela.equals( tw_album ) )
                    {
                        newAlbum = new Album( tw_album, desc );
                    }

                    for( Foto f: BaseDatos.collection )
                    {
                        // System.out.println( ">" + newAlbum.getDescription() );
                        if( f.getAlbum().equals( newAlbum.getName() ) )
                        {
                            f.setAlbum( newAlbum );
                        }
                    }    

                    BaseDatos.albunes.set( thisPointer, newAlbum );
                }

                Fotos ft = new Fotos();

                Scene sc1 = new Scene(ft.getFotos(),600,600);
                Stage fotoPanel = Directory.fotoPanel;
                fotoPanel.setTitle("Fotos");
                fotoPanel.setScene(sc1);
                fotoPanel.show();    

            }
        });

        //Accion boton borrar
        delete.setOnMouseClicked( (e) -> {
            if( photoCollections.getSelectionModel().isEmpty() )
            {
                System.out.println("Algo no esta bien aqui xd");
            }
            else
            {
                BaseDatos.collection.remove( photoCollections.getSelectionModel().getSelectedItem() );
                tr.remove( photoCollections.getSelectionModel().getSelectedItem() );
            }
        } );

        //Accion boton editar
        edit.setOnMouseClicked( (e) -> {
            Boolean cond = l_people.getText().isEmpty() && l_keyWord.getText().isEmpty();
            int sub_pointer = photoCollections.getSelectionModel().getSelectedIndex();
            int pointer = photoCollections.getSelectionModel().getSelectedIndex() + 1;

            if( photoCollections.getSelectionModel().isEmpty() || cond )
            {
                System.out.println("Creo que no entiendes el programa...");
            }
            else
            {
                String tw_people = tr.get( photoCollections.getSelectionModel().getSelectedIndex() ).getPeople();
                String tw_keyWords = tr.get( photoCollections.getSelectionModel().getSelectedIndex() ).getKeyword();

                String extension = tr.get( photoCollections.getSelectionModel().getSelectedIndex() ).getExtension();

                if( !l_people.getText().isEmpty() )
                {
                    tw_people += ","+l_people.getText();
                }
                else
                {
                    tw_people = tr.get( photoCollections.getSelectionModel().getSelectedIndex() ).getPeople();
                }
                if( !l_keyWord.getText().isEmpty() )
                {
                    tw_keyWords += ","+l_keyWord.getText();
                }
                else
                {
                    tw_keyWords = tr.get( photoCollections.getSelectionModel().getSelectedIndex() ).getKeyword();
                }

                String tw_place = tr.get( photoCollections.getSelectionModel().getSelectedIndex() ).getPlace();
                String tw_album = tr.get( photoCollections.getSelectionModel().getSelectedIndex() ).getAlbum();
                String tw_comment = tr.get( photoCollections.getSelectionModel().getSelectedIndex() ).getComment();

                // System.out.print(tw_album);

                Album newAlbum = Album.existe(tw_album);

                // tr.set( sub_pointer , new Foto(Directory.fileName + pointer + Directory.fileExtension,tw_place,tw_keyWords,newAlbum,tw_people,tw_comment) );
                BaseDatos.collection.set( sub_pointer , new Foto(Directory.fileName + pointer + extension,tw_place,tw_keyWords,newAlbum,tw_people,tw_comment) );
                // BaseDatos.collection.get(sub_pointer).addPeople(tw_people);
            }


            Fotos ft = new Fotos();

            Scene sc1 = new Scene(ft.getFotos(),600,600);
            Stage fotoPanel = Directory.fotoPanel;
            fotoPanel.setTitle("Fotos");
            fotoPanel.setScene(sc1);
            fotoPanel.show();    

        });

    }

    public VBox getFotos()
    {
        return root;
    }
}

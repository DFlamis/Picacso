package Interfaz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

import Clases.Archivo;
import LecturaArchivos.BaseDatos;
import LecturaArchivos.Directory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowFiles
{
    private VBox root;
    private TableView showFiles;

    public ShowFiles()
    {
        root = new VBox();
        ShowTable();
    }

    public void ShowTable()
    {

        showFiles = new TableView<>();

        showFiles.setRowFactory(tv -> {
            TableRow<Archivo> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Archivo rowData = row.getItem();

                    if( rowData.getType().equals("Carpeta") )
                    {
                        File folder = new File( rowData.getPath() );

                        ShowFiles.ImagineDragons(folder);
    
                        ShowFiles sf = new ShowFiles();
    
                        Scene sc2 = new Scene( sf.getShowFiles(),600,600 );
                        Stage showFiles = new Stage();
                        showFiles.setTitle("Buscar Fotos");
                        showFiles.getIcons().add(new Image( "file:" + Directory.iconFolder + "/Top.png"));
                        showFiles.setScene(sc2);
                        showFiles.show();        
                    }
                    else
                    {
                        File alfa = new File( rowData.getPath() );
                        File omega = new File("Importar_Imagenes", rowData.getName());
                        try
                        {
                            // System.out.println(alfa.getAbsolutePath());
                            // System.out.println(omega.getAbsolutePath());
                            
                            Files.copy(Paths.get(alfa.getAbsolutePath()), Paths.get(omega.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
        
                }
            });
            return row ;
        });

        Label empty = new Label("   Aun no hay fotos que mostrar\n¡Prueba importando algunas!");
        empty.setAlignment(Pos.CENTER);
        showFiles.setPlaceholder(empty);

        TableColumn<Archivo, String> name = new TableColumn<Archivo, String>("Nombre");
        name.setCellValueFactory(new PropertyValueFactory<Archivo, String>("name"));

        TableColumn<Archivo, String> type = new TableColumn<Archivo, String>("Tipo");
        type.setCellValueFactory(new PropertyValueFactory<Archivo, String>("type"));


        ObservableList<Archivo> tr = FXCollections.observableArrayList(BaseDatos.tempFiles);
        showFiles.getColumns().addAll(Arrays.asList(name,type));

        SortedList<Archivo> listaOrdenada = new SortedList<>(tr);

        listaOrdenada.comparatorProperty().bind(showFiles.comparatorProperty());

        showFiles.setItems(listaOrdenada);

        showFiles.sort();

        root.getChildren().addAll(showFiles);
    }

    public static void ImagineDragons(File folder)
    {
        BaseDatos.tempFiles.clear();

        File[] lista = folder.listFiles();
        for( File f: lista )
        {

            if( f.isFile() )
            {
                Archivo tempArch = new Archivo(f.getName(), f.getPath(),"Imagen");
                if( tempArch.esCompatible() )
                {
                    BaseDatos.tempFiles.add(tempArch);
                }
            }
            else
            {
                Archivo tempArch = new Archivo(f.getName(), f.getPath(), "Carpeta");
                BaseDatos.tempFiles.add(tempArch);
            }
        }

    }

    public VBox getShowFiles()
    {
        return this.root;
    }
}

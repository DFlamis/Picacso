package LecturaArchivos;

import java.net.InetAddress;

import Clases.Album;
import javafx.stage.Stage;

public class Directory 
{
    public static Stage fotoPanel = new Stage();
    public static String cazuela = "Default";

    public static String separator = "/";
    public static String firstPart = "Users";
    public static String lastPart = "Pictures";
    public static String rootPath =  "" + separator + firstPart + separator + System.getProperty("user.name") + separator + lastPart ;

    public static String RESOURCE_FOLDER = System.getProperties().getProperty("jarDir") + "/classes/Files";

    public static String systemRoot = "";

    //Direcciones
    public static String externalFolder = "Importar_Imagenes";
    public static String fileName = "Example";

    //Burned paths
    public static String mainPath = "src/main/resources/";
    public static String rootFolder = mainPath + "Files";
    public static String natto = mainPath + "Data";
    public static String iconFolder = mainPath + "Icons";

    //Archivos no volatiles
    public static String fruta = "/Fotos.txt";
    public static String leonardo = "/Albums.txt";
    
    // public static String fileExtension = ".jpg";

    // public static String fullPath = rootFolder + fileName;
    
}
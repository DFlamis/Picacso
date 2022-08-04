package LecturaArchivos;

import java.io.File;

public class Ficheros
{
    public static int countFiles()
    {
        File carpeta = new File(Directory.RESOURCE_FOLDER);
        File[] lista = carpeta.listFiles();
                
        return lista.length;  
    }

    public static File[] loadFiles(String path)
    {
        File folder = new File( path );
        return folder.listFiles();
    } 

    public static void importarImagenes()
    {
        
    }

}

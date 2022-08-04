package LecturaArchivos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import Clases.Album;
import Clases.Foto;

public class Lector
{
    public static void fileLoader2()
    {
        int point =  BaseDatos.pointer;

        while( point <= Ficheros.countFiles() )
        {
            String fileName = Ficheros.loadFiles( Directory.RESOURCE_FOLDER)[point - 1].getName();
            
            String extencion = "" + fileName.charAt( fileName.length() - 3 ) + fileName.charAt( fileName.length() - 2 ) + "g";
            Album al = BaseDatos.defoult;
            Foto picture = new Foto(Directory.fileName + point + "." + extencion,al);

            if( exist(picture) )
            {
                BaseDatos.collection.add( picture );
            }

            // System.out.println(BaseDatos.pointer);
            // System.out.println(point);

            point += 1;
            BaseDatos.pointer += 1;
        }
    }

    public static void fileLoader1A(String path) throws IOException
    {
        // File[] folder = Ficheros.loadFiles( Directory.RESOURCE_FOLDER );

        int pointer = BaseDatos.newPointer ;
        for( File f: Ficheros.loadFiles( path ) )
        {
            File alfa = f;
            String extencion = "" + f.getName().charAt( f.getName().length() - 3) + f.getName().charAt(f.getName().length() - 2)+"g";
            String name = "Example"+pointer+"." + extencion;
            File omega = new File(Directory.RESOURCE_FOLDER, name );

            Files.copy(Paths.get(alfa.getAbsolutePath()), Paths.get(omega.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
            
            pointer += 1;
            BaseDatos.newPointer += 1;
        }
    }

    public static Foto imgLoader(String name)
    {
        for( Foto ft: BaseDatos.collection )
        {
            if( name == ft.getName() )
            {
                return ft;
            }
        }
        return null;
    }


    public static boolean exist(Foto ft)
    {
        for(Foto f: BaseDatos.collection)
        {
            // System.out.println("Aqio");
            // System.out.println( f.getName().equals(ft.getName()) );
            // System.out.println( f.getName() );
            // System.out.println( ft.getName() );
            if ( f.getName().equals(ft.getName()) )
            {
                return false;
            }
        }
        return true;
    }
}

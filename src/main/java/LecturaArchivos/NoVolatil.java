package LecturaArchivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Clases.Album;
import Clases.Foto;
import util.Arreglo;

/**
 *
 * @author Derek Aviles
 */

public class NoVolatil
{
    //Guardar datos ---------------------------------------------------------------------------------------------------------------------------------------------------------
   
    public static String cahePhoto(Arreglo<Foto> tilla) //Nombre sugerido por Zaida
    {
        String space = "-";
        String cache = "";
        for(Foto f: tilla)
        {
            cache += f.getName() + space + f.getPlace() + space + f.getKeyword() + space + f.getAlbum() + space + f.getPeople() + space + f.getComment() + "\n";
        }

        return cache;
    }

    public static String cacheAlbum(Arreglo<Album> helena) //Nombre suferido por Karen
    {
        String space = "-";
        String cache = "";
        for(Album a: helena)
        {
            cache += a.getName() + space + a.getDescription() + "\n";
        }

        return cache;

    }

    public static boolean saveState(String DFlamis, String name) //Mi firma en el codigo
    {
        try
        {
            File file = new File( Directory.natto + name );
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            
            bw.write( DFlamis );
            bw.close();

            return true;
        }
        catch(IOException e)
        {
            return false;
        }
    }

    //Cargar datos ---------------------------------------------------------------------------------------------------------------------------------------------------------

    public static boolean loadPhotos()
    {
        try(BufferedReader bf = new BufferedReader(new FileReader( Directory.natto + Directory.fruta )))
        {
            String linea;
            while((linea = bf.readLine()) != null)
            {
                String l[] = linea.split("-");
                Album al = new Album(l[3]);
                BaseDatos.collection.add( new Foto(l[0],l[1],l[2],al,l[4],l[5] ) );
            }
            return true;
        }
        catch(IOException e)
        {
            return false;
        }

    }

    public static boolean loadAlbums()
    {
        try(BufferedReader bf = new BufferedReader(new FileReader( Directory.natto + Directory.leonardo )))
        {
            String linea;
            while((linea = bf.readLine()) != null)
            {
                String l[] = linea.split("-");
                BaseDatos.albunes.add( new Album( l[0],l[1] ) );
            }
            return true;

        }
        catch(IOException e)
        {
            return false;
        }

    }
}

package LecturaArchivos;

import Clases.Album;
import Clases.Foto;

public class MiHelper
{
    public static void editBurnedData()
    {
        BaseDatos.collection.clear();
        NoVolatil.loadPhotos();
    }

    public static void bananas() //Reasignar el album a la foto
    {
        for( Foto f: BaseDatos.collection )
        {

            for( Album a: BaseDatos.albunes )
            {

                if( f.getAlbum().equals(a.getName()) )
                {
                    f.setAlbum(a);
                }
            }
        }
    }
}

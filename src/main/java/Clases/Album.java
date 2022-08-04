package Clases;

import LecturaArchivos.BaseDatos;

/**
 *
 * @author Derek Aviles
 */

public class Album
{
    private String name;
    private String description;
    
    public Album(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public Album(String name)
    {
        this.name = name;
        this.description = " ";
    }

    public String getName()
    {
        return this.name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public static Album existe(String aName)
    {
        for( Album a: BaseDatos.albunes )
        {
            if( a.getName().equals(aName) )
            {
                return a;
            }
        }
        return null;
    }

    public static int existeN(String aName)
    {
        int pointer = -1;
        for( Album a: BaseDatos.albunes )
        {
            pointer += 1;
            if( a.getName().equals(aName) )
            {
                return pointer;
            }
        }
        return -1;
    }

}

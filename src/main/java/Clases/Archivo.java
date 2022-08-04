package Clases;

import javafx.beans.property.SimpleStringProperty;

public class Archivo
{
    private SimpleStringProperty name;
    private SimpleStringProperty path;
    private SimpleStringProperty type;

    public Archivo(String name, String path, String type)
    {
        this.name = new SimpleStringProperty( name );
        this.path = new SimpleStringProperty( path );
        this.type = new SimpleStringProperty( type );
    }

    public Boolean esCompatible()
    {
        Boolean condition = false;

        String lastChar = ""+this.name.get().charAt( this.name.get().length() - 1 );
        String point = ""+this.name.get().charAt( this.name.get().length() - 4 );

        if( lastChar.equals("g") && point.equals(".") )
        {
            condition = true;
        }

        return condition;
    }

    public String getName()
    {
        return this.name.get();
    }

    public String getPath()
    {
        return this.path.get();
    }

    public String getType()
    {
        return this.type.get();
    }
}

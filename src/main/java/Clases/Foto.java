package Clases;

import javafx.beans.property.SimpleStringProperty;

public class Foto
{
    //Aparecen en la table view
    private SimpleStringProperty name;
    private SimpleStringProperty place; //Lugar donde se tomo la foto
    private SimpleStringProperty keyWord = new SimpleStringProperty();
    private SimpleStringProperty album;
    private SimpleStringProperty people = new SimpleStringProperty(); //Lista de personas en la foto

    private SimpleStringProperty number;
    private SimpleStringProperty description; //Descripcion del album

    private String comment;
    private String extension;

    private Album trueAlbum;

    //Constructor por defecto
    public Foto(String name,Album anAlbum)
    {
        this.name = new SimpleStringProperty(name);
        this.place =  new SimpleStringProperty(" ");
        this.keyWord =  new SimpleStringProperty(" ");
        this.people = new SimpleStringProperty(" ");

        this.number = new SimpleStringProperty( ""+this.name.get().charAt( this.name.get().length() - 5 ) );

        this.comment = " ";

        this.trueAlbum = anAlbum;

        this.album = new SimpleStringProperty( anAlbum.getName() );
        this.description = new SimpleStringProperty( anAlbum.getDescription() );

        setExtension(name);
    }

    public Foto(String name, String place, String keyWord, Album anAlbum, String people, String comment)
    {
        this.name = new SimpleStringProperty(name);
        this.place =  new SimpleStringProperty(place);
        this.keyWord =  new SimpleStringProperty(keyWord);
        // this.album = new SimpleStringProperty(album);
        this.people =  new SimpleStringProperty(people);

        this.number = new SimpleStringProperty( ""+this.name.get().charAt( this.name.get().length() - 5 ) );

        this.trueAlbum = anAlbum;

        this.album = new SimpleStringProperty( anAlbum.getName() );
        this.description = new SimpleStringProperty( anAlbum.getDescription() );

        this.comment = comment;

        setExtension(name);
    }

    public void addPeople(String pipols)
    {
        int poi = 0;
        String tempura = this.people.get();
        String[] listTempura = pipols.split(",");
        for(String p: listTempura)
        {
            if(poi == 0)
            {
                tempura += p;
                poi += 1;
            }
            else
            {
                tempura += ""+p+",";
            }
        }
        this.people.setValue(tempura);
    }

    public void setExtension(String name)
    {
        String b = ""+name.charAt( name.length() - 2 );
        String a = ""+name.charAt( name.length() - 3 );

        this.extension = "." + a + b + "g";
    }
    
    public String getName()
    {
        return this.name.get();
    }

    public String getPlace()
    {
        return this.place.get();
    }

    public String getKeyword()
    {
        return this.keyWord.get();
    }

    public String getAlbum()
    {
        return this.album.get();
    }

    public String getPeople()
    {
        return this.people.get();
    }

    public String getComment()
    {
        return this.comment;
    }

    public String getDescription()
    {
        return this.description.get();
    }

    public String getNumber()
    {
        return this.number.get();
    }

    public String getExtension()
    {
        return this.extension;
    }

    //Setters

    public void setAlbum(Album a)
    {
        this.trueAlbum = a;
        this.album = new SimpleStringProperty( a.getName() );
        this.description = new SimpleStringProperty( a.getDescription() );
    }

    public static void haveDescription()
    {
        
    }

}

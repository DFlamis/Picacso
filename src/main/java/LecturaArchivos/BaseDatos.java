package LecturaArchivos;

import java.util.ArrayList;

import Clases.Album;
import Clases.Archivo;
import Clases.Foto;
import util.Arreglo;

public class BaseDatos
{
    public static Album defoult =  new Album("Default"," ");

    public static int pointer = 1;
    public static int newPointer = 1;

    //Aqui se guardan las fotos cuando se leen
    // public static ArrayList<Foto> collection = new ArrayList<>(); //No volatil
    public static Arreglo<Foto> collection = new Arreglo<>(); //No volatil

    //Aqui se guardan los albunes
    public static Arreglo<Album> albunes = new Arreglo<>();//No volatil
    
    //Aqui se guardaran los archivos
    public static Arreglo<Archivo> tempFiles = new Arreglo<>();//Volatil
}

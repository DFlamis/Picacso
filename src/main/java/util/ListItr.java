package util;

import java.util.ListIterator;

public class ListItr<E> implements ListIterator<E>
{
    private E[] arreglo;
    private int size;
    private int pointer;
    private int max;

    public ListItr(E[] arreglo, int size, int pointer, int max)
    {
        this.arreglo = arreglo;
        this.size = size;
        this.pointer = pointer;
        this.max = max;
    }

    @Override
    public boolean hasNext()
    {
        return this.pointer < this.size;
    }

    @Override
    public E next()
    {
        if( hasNext() )
        {
            this.pointer += 1;
            return arreglo[pointer-1];
        }
        return null;
    }

    @Override
    public boolean hasPrevious()
    {
        return arreglo[this.pointer] != null && this.pointer >= 0;
    }

    @Override
    public E previous()
    {
        if( !hasPrevious() )
        {
            this.pointer--;
            return arreglo[pointer];
        }
        return null;
    }

    @Override
    public int nextIndex()
    {
        return this.pointer + 1;
    }

    @Override
    public int previousIndex()
    {
        return this.pointer - 1;
    }

    @Override
    public void remove()
    {
        for( int x = this.pointer; x < size; x++ )
        {
            arreglo[pointer] = arreglo[pointer + 1];
        }
    }

    @Override
    public void set(E e)
    {
        if( pointer < max )
        {
            this.arreglo[ nextIndex() ] = e;
        }

    }

    @Override
    public void add(E e)
    {
        System.out.println("Entro aqui tambien");

        if( pointer == max - 1 )
        {
            grow();
        }

        // if( pointer < max )
        // {
        //     arreglo[pointer + 1] = e;
        // }


        arreglo[pointer + 1] = e;

    }

    public void grow()
    {
        max = max + 20;
        E[] newLista = (E[]) new Object[max];
        int pointer = 0;

        for( E ob: this.arreglo )
        {
            newLista[pointer] = ob;
            pointer += 1;
        }

        this.arreglo = newLista;
    }

}

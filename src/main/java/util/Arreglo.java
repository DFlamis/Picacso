package util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Arreglo<E> implements Iterable<E>, List<E>
{
    private E[] lista; //Areglo
    private int size;
    private int capacity;

    private int modCount = 0;
    transient Object[] elementData;

    public Arreglo()
    {
        //Iniciar los arreglos con 3 debido a que solo inicia con 3 fotos
        this.size = 0;
        this.capacity = 3;
        this.lista = (E[]) new Object[capacity];
    }


    //Exclusive------------------------------------------------------------------------------------------------------------
    
    public boolean isValid(E e)
    {
        return e != null;
    }

    public boolean isFull()
    {
        return size == capacity;
    }

    public void grow()
    {
        capacity = capacity + 20;
        E[] newLista = (E[]) new Object[capacity];
        int pointer = 0;

        for( E ob: this.lista )
        {
            newLista[pointer] = ob;
            pointer += 1;
        }

        this.lista = newLista;
    }

    //Mandatory--------------------------------------------------------------------------------------------------------------
    @Override
    public int size()
    {
        return this.size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public boolean contains(Object o)
    {
        int pointer = 0;

        for( Object ob: this.lista )
        {
            if( ob == o )
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray()
    {
        return this.lista;
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        return null;
    }

    @Override
    public boolean add(E e)
    {
        if( e == null ) return false;

        if( isFull() )
        {
            grow();
        }

        lista[size] = e;
        size++;
        
        return true;
    }

    @Override
    public boolean remove(Object e)
    {
        int pointer = 0;

        if( !isEmpty() )
        {
            for(Object ob: this.lista)
            {
                if( ob == e )
                {
                    lista[pointer] = lista[pointer + 1];
                }
            }
            size--;
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c)
    {
        Object[] a = c.toArray();
        modCount++;
        int numNew = a.length;
        if (numNew == 0)
            return false;
        Object[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size))
            // elementData = grow(s + numNew);
        System.arraycopy(a, 0, elementData, s, numNew);
        size = s + numNew;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c)
    {
        // TODO Auto-generated method stub

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub

        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub

        return false;
    }

    @Override
    public void clear()
    {
        for( int x = 0; x < size; x++ )
        {
            lista[x] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index)
    {
        int pointer = 0;

        for( E ob: this.lista )
        {
            if( pointer == index )
            {
                return ob;
            }
            pointer += 1;
        }
        return null;
    }

    @Override
    public E set(int pointer, E e)
    {
        if( pointer < size && isValid(e) )
        {
            lista[pointer] = e;
            return e;
        }
        else
        {
            System.out.println("El indice " + pointer + " esta fuera de rango");
            System.out.println(e + " no es valido");
        }
        return null;
    }

    @Override
    public void add(int index, E element)
    {
        Object[] tempList = new Object[size - 1];
        int pointer = 0;

        //Copiar los elementos
        for(Object ob: this.lista)
        {
            tempList[pointer] = ob;
            pointer += 1;
        }
    }

    @Override
    public E remove(int index)
    {
        int pointer = 0;

        for( E ob: this.lista )
        {
            if( pointer == index )
            {
                this.lista[index] = this.lista[index + 1];
            }
        }
        return null;
    }

    @Override
    public int indexOf(Object o)
    {
        int pointer = 0;

        for( E ob: this.lista )
        {
            if( ob == o )
            {
                return pointer;
            }
            pointer += 1;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o)
    {

        return 0;
    }

    @Override
    public ListIterator<E> listIterator()
    {

        return null;
    }


    @Override
    public List<E> subList(int fromIndex, int toIndex)
    {
        return null;
    }

    @Override
    public Iterator<E> iterator()
    {
        Iterator<E> it = new Iterator<E>()
        {

            private int pointer = 0;

            @Override
            public boolean hasNext()
            {
                return pointer < size && lista[pointer] != null;
            }

            @Override
            public E next()
            {
                return (E) lista[pointer++];
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException("Chale, no salio");
            }
        };
        return it;
    }

    //InnerClass-----------------------------------------------------------------------------------------------
    
    // @Override
    // public ListIterator<E> listIterator(int index)
    // {
    //     return new ListItr<>(lista, size, index, capacity);
    // }

    @Override
    public ListIterator<E> listIterator(int index)
    {
        return new ListIterator<E>()
        {
            private E[] arreglo = lista;
            private int size = size();
            private int pointer = index;
            private int max = capacity;  
        
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
                    return arreglo[this.pointer-1];
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
                    this.arreglo[ nextIndex() - 1 ] = e;
                }
        
            }
        
            @Override
            public void add(E e)
            {
                // System.out.println("Entro aqui tambien");
        
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
        };

    }
}

package util;

import java.util.Iterator;
import java.util.ListIterator;

public interface ListItrA<E> extends Iterator<E>
{
    boolean hasNext();

    E next();

    boolean hasPrevious();
    
    E previous();

    int nextIndex();

    int previousIndex();

    void remove();
    
    void set(E e);

    void add(E e);

    void grow();

}

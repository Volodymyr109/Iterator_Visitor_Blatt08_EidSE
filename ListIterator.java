package iterator;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListIterator<E> implements Iterator<E>{
    private MyList<E> liste;
    private MyEntry<E> aktuell;
    private MyEntry<E> lastReturned;

    private int modCountAnfang;

    public ListIterator(MyList<E> list, MyEntry<E> begin){
        this.liste = list;
        this.aktuell = begin;
        this.modCountAnfang = list.getModCount();
    }
    @Override
    public boolean hasNext() {
        return aktuell.next != null;
    }

    @Override
    public E next() {
        if(liste.getModCount() != modCountAnfang) {
            throw new ConcurrentModificationException();
        }

        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        MyEntry<E> returnValue = aktuell.next;
        lastReturned = aktuell;
        this.aktuell = this.aktuell.next;
        return aktuell.o;
    }

    @Override
    public void remove() {
        if(lastReturned != null) {
            lastReturned.next = lastReturned.next.next;
            aktuell = lastReturned;
            lastReturned = null;
        }
        else {
            throw new IllegalStateException("Remove has been already called or next has never been called.");
        }
    }

}

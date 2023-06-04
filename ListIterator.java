package iterator;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListIterator<E> implements Iterator<E>{
    //werte:
    private MyList<E> liste; //die durchlaufen werden soll.
    private MyEntry<E> aktuell; //Zeiger auf das aktuelle Element in der Liste.
    private MyEntry<E> lastReturned; //Zeiger auf das zuletzt zurückgegebene Element.
    private int modCountAnfang; // das den modCount zu Beginn der Iteration speichert
    // Methode initialisiert obene Variablen mit Werten
    // UND
    // speichert den Wert von modCount der Liste.
    public ListIterator(MyList<E> list, MyEntry<E> begin){
        this.liste = list;
        this.aktuell = begin;
        this.modCountAnfang = list.getModCount();
    }
    //Methode überprüft, ob es ein nächstes Element in der Iteration gibt,
    @Override
    public boolean hasNext() {
        return aktuell.next != null; // wenn next element nicht null ist, gibt hasNext() true zurück,
    }
    //Methode gibt das nächste Element in der Iteration zurück
    @Override
    public E next() {
        // wenn getModCount != anfang dann ConcurrentModificationException
        if(liste.getModCount() != modCountAnfang) {
            //the next element in the iteration
            throw new ConcurrentModificationException(); //ConcurrentModificationException wenn eine Modifikation festgestellt wird
        }
        // check, ob es ein nächstes Element gibt
        if(!hasNext()) {
            // wenn kein Element dann NoSuchElementException
            throw new NoSuchElementException(); //NoSuchElementException - wenn iterator keine el nehr hat
        }
        // Wo Iterator aktuell ist - next
        MyEntry<E> returnValue = aktuell.next;
        // dann letztes el wird aktuell
        lastReturned = aktuell;
        // dann auf das nächste Element gesetzt
        this.aktuell = this.aktuell.next;
        // dann "o" - unsere gesp. el. zurückgibt
        return aktuell.o;
    }
    //Methode entfernt das zuletzt zurückgegebene Element aus der Liste
    @Override
    public void remove() {
        // ganz leicht: WENN letz. el. != 0 dann next!
        if(lastReturned != null) {
            lastReturned.next = lastReturned.next.next;
            // aktueles el wird als letzts gesetzt
            aktuell = lastReturned;
            // dann letzts el der früher aktuell war, wäre als 0 gesetzt
            lastReturned = null;
        }
        //WENN letz. el. = 0 dann IllegalStateException
        else {
            throw new IllegalStateException("Remove wurde bereits aufgerufen oder next wurde noch nie aufgerufen.");
        }
    }

}

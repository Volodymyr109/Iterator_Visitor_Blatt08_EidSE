package iterator;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorTest {

    public static void main(String[] args) {
        MyList<Integer> liste = new MyList<Integer>(); // unsere liste
        // add el dazu
        liste.add(53);
        liste.add(57);
        liste.add(42);
        liste.add(79);
        liste.add(1);
        // Erstelle Iterator, der durch liste geht liste.iterator
        Iterator<Integer> iterator = liste.iterator();
        //runThroughAllElements übergibt den Iterator als Parameter.
        //Alles solange iterator.hasNext() überprüft und iterator.next() aufruft, um das nächste Element zu erhalten.
        runThroughAllElements(iterator); //durch alle Elemente laufen //funktioniert
        iterator.next();
        iterator.next();
        //test ob methode funkt.
        testRemove(iterator); // 79 entfernt weil 0, 1, 2 durch
        //Test der Liste danach dass testRemove geruft wurde
        System.out.println(iterator.next()); //funktioniert
        testRemove(iterator);
        testRemove(iterator);
        //erstele liste. UND nach dem der Iterator erstellt wurde, wird ein neues Element zur Liste hinzugefügt
        //iterator.next sollte diesen Fehler Exception auslösen
        testConcurrentModificationException();
        //erstele leere liste.
        // Es wird erwartet, dass das Aufrufen von iterator.next() eine NoSuchElementException auslöst
        testNoSuchElementException(); // wenn ja wird eine entsprechende Nachricht auf der Konsole ausgegeben.
    }
    // ruft remove aus ListIterator
    public static void testRemove(Iterator<Integer> iterator) {
        try {
            iterator.remove();
        }
        catch (IllegalStateException e){
            System.out.println("IllegalStateException von remove funktioniert.");
        }
    }
    // testNoSuchElementException ausgelöst wird, wenn....
    // next aufgerufen, obwohl keine weiteren Elemente in der Liste sind
    public static void testNoSuchElementException() {
        MyList<Integer> liste = new MyList<Integer>();// erstelle instanz
        Iterator<Integer> iterator = liste.iterator();// iterator
        try {
            iterator.next(); // "iterator geh next"
        }
        catch(NoSuchElementException e) { // catch NoSuchElementException
            System.out.println("NoSuchElementException von next funktioniert.");
        }
    }
    //ob eine ConcurrentModificationException ausgelöst wird, bzw wenn während der Iteration über die Liste -  eine Änderung an der Liste gegeben wird.
    public static void testConcurrentModificationException() {
        MyList<Integer> liste = new MyList<Integer>();// erstelle instanz
        Iterator<Integer> iterator = liste.iterator();// iterator
        liste.add(13); // z.b add etwas zur Liste

        try {
            iterator.next(); // "iterator geh next"
        }
        catch(ConcurrentModificationException e) { // catch ConcurrentModificationException
            System.out.println("ConcurrentModificationException von next funktioniert.");
        }
    }
    //Alles solange iterator.hasNext() überprüft und iterator.next() aufruft, um das nächste Element zu erhalten.
    public static void runThroughAllElements(Iterator<Integer> iterator) { // OB DER ITERATOR durch all el. geht!
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("Test Bestanden, Iterator ist durch alle El. in Liste durchgegangen"); //bestanden
    }
}

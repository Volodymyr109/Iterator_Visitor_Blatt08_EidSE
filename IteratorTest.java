package iterator;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorTest{

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
        //removeAll
        testRemoveAll(iterator);
        System.out.println(iterator.next());
        //erstele liste. UND nach dem der Iterator erstellt wurde, wird ein neues Element zur Liste hinzugefügt
        //iterator.next sollte diesen Fehler Exception auslösen
        testConcurrentModificationException();
        //erstele leere liste.
        // Es wird erwartet, dass das Aufrufen von iterator.next() eine NoSuchElementException auslöst
        testNoSuchElementException(); // wenn ja wird eine entsprechende Nachricht auf der Konsole ausgegeben.
        //testIllegalStateExceptionInRemove
        testIllegalStateExceptionInRemove();
        // testFailFastInNextAndAdd
        testFailFastInNextAndAdd();
        //testNoSuchElementExceptionInNext
        testNoSuchElementExceptionInNext();

    }
    // Methode überprüft, ob eine IllegalStateException ausgelöst wird, wenn remove() aufgerufen wird, ohne zuvor next() aufgerufen zu haben.
    public static void testIllegalStateExceptionInRemove() {
        MyList<Integer> liste = new MyList<Integer>(); //Die Methode erstellt eine Instanz von MyList<Integer> und einen Iterator.
        Iterator<Integer> iterator = liste.iterator();
        try {
            Iterator<Integer> iter = liste.iterator();
            iter.remove(); //remove
            iterator.next(); // dann next
        } catch (IllegalStateException e) { //dann check ob remove() aufgerufen wird, ohne zuvor next() aufgerufen zu haben.
            //System.out.println("testIllegalStateExceptionInRemove funktioniert.");
        }
        //System.out.println("testIllegalStateExceptionInRemove");
    }
    // check ob während der Iteration über die Liste etwas drinnen geändert wird
    public static void testFailFastInNextAndAdd() {
        MyList<Integer> liste = new MyList<Integer>(); //Die Methode erstellt eine Instanz von MyList<Integer> und einen Iterator.
        Iterator<Integer> iterator = liste.iterator();
        try {
            //Dann wird versucht, etwas zu ändern und den Iterator zu verwenden, um das nächste Element abzurufen.
            Iterator<Integer> iter = liste.iterator();
            liste.add(3);
            iter.next();
            iterator.next();
            // Wenn die ConcurrentModificationException ausgelöst wird, wird eine entsprechende System.out
        } catch (ConcurrentModificationException e) {
           // System.out.println("ConcurrentModificationException funktioniert.");
        }
        //System.out.println("ConcurrentModificationException");
    }
    //Methode überprüft, ob eine NoSuchElementException ausgelöst wird, wenn next() aufgerufen wird, obwohl keine weiteren Elemente in der Liste vorhanden sind.
    public static void testNoSuchElementExceptionInNext() {
        MyList<Integer> liste = new MyList<Integer>(); //Die Methode erstellt eine Instanz von MyList<Integer> und einen Iterator.
        try {
            Iterator<Integer> iterator = liste.iterator();
            while (iterator.hasNext()) { // Iterator geht durch die liste
                iterator.next();
            }
            iterator.next();
        } catch (NoSuchElementException e) { //wenn next() aufgerufen wird, obwohl keine weiteren Elemente in der Liste vorhanden sind dann!
            //System.out.println("NoSuchElementException funktioniert.");
        }
        //System.out.println("testNoSuchElementExceptionInNext");
    }

    // ruft remove aus ListIterator
    public static void testRemove(Iterator<Integer> iterator) {
        try {
            iterator.remove();
        } catch (IllegalStateException e) {
            System.out.println("IllegalStateException von remove funktioniert.");
        }
        //System.out.println("Check: testRemove und IllegalStateException damit");
    }

    // try to remove all
    public static void testRemoveAll(Iterator<Integer> iterator) {
        try {
            for (int i = 0; i < 10; i++) {
                iterator.next();
                iterator.remove();
            }
        } catch (IllegalStateException e) {
            System.out.println("IllegalStateException von RemoveAll funktioniert.");
        }
        //System.out.println("Check: testRemoveAll und IllegalStateException damit");
    }

    // testNoSuchElementException ausgelöst wird, wenn....
    // next aufgerufen, obwohl keine weiteren Elemente in der Liste sind
    public static void testNoSuchElementException() {
        MyList<Integer> liste = new MyList<Integer>();// erstelle instanz
        Iterator<Integer> iterator = liste.iterator();// iterator
        try {
            iterator.next(); // "iterator geh next"
        } catch (NoSuchElementException e) { // catch NoSuchElementException
            System.out.println("NoSuchElementException von next funktioniert.");
        }
        //System.out.println("Check: testNoSuchElementException");
    }

    //ob eine ConcurrentModificationException ausgelöst wird, bzw wenn während der Iteration über die Liste -  eine Änderung an der Liste gegeben wird.
    public static void testConcurrentModificationException() {
        MyList<Integer> liste = new MyList<Integer>();// erstelle instanz
        Iterator<Integer> iterator = liste.iterator();// iterator
        liste.add(13); // z.b add etwas zur Liste
        try {
            iterator.next(); // "iterator geh next"
        } catch (ConcurrentModificationException e) { // catch ConcurrentModificationException
            System.out.println("ConcurrentModificationException von next funktioniert.");
        }
        //System.out.println("Check: testConcurrentModificationException");
    }
    //  testNoSuchElementExceptionInNext, testIllegalStateExceptionInRemove, testFailFastInNextAndAdd aus der MyListTest als myListTest = new MyListTest() oben implementieren
    /*
    public void testFailFastInNextAndAdd() {
        MyList<Integer> liste = new MyList<Integer>();
        Iterator<Integer> iterator = liste.iterator();
        try {
            Iterator<Integer> iter = liste.iterator();
            liste.add(3);
            iter.next();
            iterator.next();
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException funktioniert.");
        }
        System.out.println("Check: ConcurrentModificationException");

    }
    public void testIllegalStateExceptionInRemove() {
        MyList<Integer> liste = new MyList<Integer>();
        Iterator<Integer> iterator = liste.iterator();
        try {
            Iterator<Integer> iter = liste.iterator();
            iter.remove();
            iterator.next();
        } catch (IllegalStateException e) {
            System.out.println("testIllegalStateExceptionInRemove funktioniert.");
        }
        System.out.println("Check: testIllegalStateExceptionInRemove");

    }
    public void testNoSuchElementExceptionInNext() {
        MyList<Integer> liste = new MyList<Integer>();
        try {
            Iterator<Integer> iterator = liste.iterator();
            while (iterator.hasNext()) {
                iterator.next();
            }
            iterator.next();
        }
        catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException funktioniert.");
        }
        System.out.println("Check: testNoSuchElementExceptionInNext");
    }*/

    //Alles solange iterator.hasNext() überprüft und iterator.next() aufruft, um das nächste Element zu erhalten.
    public static void runThroughAllElements(Iterator<Integer> iterator) { // OB DER ITERATOR durch all el. geht!
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Test Bestanden, Iterator ist durch alle El. in Liste durchgegangen"); //bestanden
    }
}

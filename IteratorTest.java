package iterator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorTest {

    public static void main(String[] args) {
        MyList<Integer> liste = new MyList<Integer>();
        liste.add(53);
        liste.add(57);
        liste.add(42);
        liste.add(79);
        liste.add(1);
        Iterator<Integer> iterator = liste.iterator();
        runThroughAllElements(iterator); //durch alle Elemente laufen //funktioniert
        iterator = liste.iterator(); //Loeschen
        iterator.next();
        iterator.next();
        testRemove(iterator); // 79 entfernt
        System.out.println(iterator.next()); //funktioniert
        testRemove(iterator); //IllegalStateException
        testRemove(iterator); //funktioniert
        testConcurrentModificationException(); //funktioniert
        testNoSuchElementException(); //funktioniert
    }
    public static void testRemove(Iterator<Integer> iterator) {
        try {
            iterator.remove();
        }
        catch (IllegalStateException e){
            System.out.println("IllegalStateException von remove funktioniert.");
        }
    }
    public static void testNoSuchElementException() {
        MyList<Integer> liste = new MyList<Integer>();
        Iterator<Integer> iterator = liste.iterator();
        try {
            iterator.next();
        }
        catch(NoSuchElementException e) {
            System.out.println("NoSuchElementException von next funktioniert.");
        }
    }
    public static void testConcurrentModificationException() {
        MyList<Integer> liste = new MyList<Integer>();
        Iterator<Integer> iterator = liste.iterator();
        liste.add(13);

        try {
            iterator.next();
        }
        catch(ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException von next funktioniert.");
        }
    }
    public static void runThroughAllElements(Iterator<Integer> iterator) {
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("-------------------------------------"); //bestanden
    }
}

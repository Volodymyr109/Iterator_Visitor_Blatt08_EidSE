package visitor;

public class VisitorTest {
    public static void main(String[] args) {

        // Test für ganzen Besuch

        //Es wird eine neue MyList<Integer> namens liste erstellt und mit einigen Integer-Werten befüllt.
        MyList<Integer> liste = new MyList<Integer>();
        liste.add(4);
        liste.add(43);
        liste.add(89);
        final MyList<Integer> copy = new MyList<Integer>(); //erstelle copy davon
        Visitor<Integer> visitor = new Visitor<Integer>() {
            @Override
            public boolean visit(Integer o) {
                copy.add(o); //add objekt
                copy.advance(); // weiter
                return true;
            }
        };

        liste.accept(visitor);      //Ein Visitor (visitor) wird erstellt, der die Besuchsmethode implementiert.
        assertEquals(liste, copy);  //Die assertEquals()-Methode wird aufgerufen, um zu überprüfen, ob die liste und die copy identisch sind.

        //Test fuer einen Besuch

        VisitorTest tester = new VisitorTest();
        tester.test1besuchen(); // rufe test1besuchen
        System.out.println("Test beendet");
    }

    public void test1besuchen() { // führt einen Testfall durch, bei dem nur ein Besuch mit dem Visitor stattfindet

        MyList<Integer> liste = new MyList<Integer>(); // liste erstellt + add objekts dazu
        liste.add(4);
        liste.add(43);
        liste.add(89);
        final MyList<Integer> copy = new MyList<Integer>(); // leere liste als copy erstellt
        Visitor<Integer> visitor = new Visitor<Integer>() { // der visitor erstellt, die Besuchsmethode implementiert
            private int i = 0;
            //check ob wert "82" nr 2 erreicht hat
            public boolean visit(Integer o) {
                if (i++ == 2) {
                    copy.add(o); // wenn ja dann wird das aktuelle Element zur copy der Liste hinzugefügt und der Besuch abgebrochen
                    return false; // abgebrochen
                } else { //wenn nein dann true
                    return true;
                }
            }
        };
        liste.accept(visitor);
        //danach wird überprüft, ob das Element 4 in der copy-Liste enthalten ist. Wenn nicht, wird die Fehlermeldung "Fehler bei nur einem Besuch" ausgegeben.
        //if(1 != (int) copy.elem()) { // dann fehler
        if(4 != (int) copy.elem()) {
            System.out.println("Fehler bei nur einem Besuch");
        }
    }
    
    private static <E> void assertEquals(MyList<E> erw, MyList<E> ist) {
        erw.reset(); //reset hier um den Anfangsposition für beide Listen zu setzen
        ist.reset();
        // mit while wird Schleifendurchlauf das aktuelle Element der ERW Liste mit dem aktuellen Element verglichen
        while (!erw.endpos() && !ist.endpos()) {
            if (!erw.elem().equals(ist.elem())) {
                System.out.println("Fehler Elementen nicht übereinstimmen"); //Wenn die Elemente nicht übereinstimmen dann fehler
            }
            // nachdem vergleich wird advance - geh weiter
            erw.advance();
            ist.advance();
        }
    }
}

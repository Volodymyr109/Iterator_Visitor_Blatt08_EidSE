package visitor;
/*
Betrachten Sie die Interfaces Visitor und Visitable und machen Sie sich mit deren Funktionsweise vertraut.
Jede Klasse, die das Interface Visitable implementiert, soll beim Aufruf der
Methode accept(Visitor) all ihre Elemente durchlaufen und für jedes Element die Methode visit(Object) der übergebenen Visitor-Instanz aufrufen. Dies wird so lange gemacht,
bis entweder alle Elemente durchlaufen wurden, oder bis der Visitor false zurück liefert. Ein
Visitor liefert also true, solange er noch weitere Elemente besuchen will.
Implementieren Sie das Interface Visitable in der Klasse MyList, welche Sie im Ordner
visitor dem Aufgabenblatt beigefügt finden. Das Interface soll dabei so implementiert werden,
dass mit einem Aufruf von accept die Liste einmal vollständig durchlaufen wird, wenn der
Visitor dies mit seiner Rückgabe zulässt.


Achtung! Um die Korrektur zu erleichtern, sollen die Implementation des Visitable Interfaces nicht in in derselben Datei erfolgen,
wie bei der Aufgabe „Iterator - Implementierung“. Aus
diesem Grund ist die Klasse MyList auch zweimal dem Aufgabenblatt beigefügt. Einmal im
Ordner visitor und einmal im Ordner iterator, welche jeweils für die respektiven Aufgaben gedacht sind
 */
public class VisitorTest {
    public static void main(String[] args) {
        /**
         * Test fuer ganzen Besuch
         */
        MyList<Integer> liste = new MyList<Integer>();
        liste.add(4);
        liste.add(43);
        liste.add(89);
        final MyList<Integer> copy = new MyList<Integer>();
        Visitor<Integer> visitor = new Visitor<Integer>() {
            @Override
            public boolean visit(Integer o) {
                copy.add(o);
                copy.advance();
                return true;
            }
        };

        liste.accept(visitor);
        assertEquals(liste, copy);
        /**
         * Test fuer einen Besuch
         */
        VisitorTest tester = new VisitorTest();
        tester.test1besuchen();
        System.out.println("Test beendet");
    }

    public void test1besuchen() {

        MyList<Integer> liste = new MyList<Integer>();
        liste.add(4);
        liste.add(43);
        liste.add(89);
        final MyList<Integer> copy = new MyList<Integer>();
        Visitor<Integer> visitor = new Visitor<Integer>() {
            private int i = 0;
            public boolean visit(Integer o) {
                if (i++ == 2) {
                    copy.add(o);
                    return false;
                } else {
                    return true;
                }
            }
        };
        liste.accept(visitor);
        if(4 != (int) copy.elem()) {
            System.out.println("Fehler bei nur einem Besuch");
        }
    }



    private static <E> void assertEquals(MyList<E> erw, MyList<E> ist) {
        erw.reset();
        ist.reset();
        while (!erw.endpos() && !ist.endpos()) {
            if (!erw.elem().equals(ist.elem())) {
                System.out.println("Fehler brudi");
            }
            erw.advance();
            ist.advance();
        }
    }

}

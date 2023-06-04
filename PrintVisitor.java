package visitor;

public class PrintVisitor<E> implements Visitor<E>{

    // implementiert visitor und visit aufgerufen wird, um ein Element auszuführen.
    // bzw jedes Element der Liste auszugeben, wenn es besucht wird
    @Override
    public boolean visit(E o) {
        System.out.println(o); // print der Ausgabe, angenommen: tupel <E> und eine wert "o" drinnen
        return true; // wenn true dann die Schleife in der accept Methode der MyList Klasse fortgesetzt wird und das nächste Element besucht wird
    }
}

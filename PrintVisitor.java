package visitor;

public class PrintVisitor<E> implements Visitor<E>{

    @Override
    public boolean visit(E o) {
        System.out.println(o);
        return true;
    }
}

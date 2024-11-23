package baseNoStates;

/*
Class Visitor implements Visitor pattern. It allows to reduce code in the classes
that form the Composite pattern.
*/
public interface Visitor {

    void visitPartition(Area partition);
    void visitSpace(Area space);

}

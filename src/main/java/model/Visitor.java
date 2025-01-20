package model;

public interface Visitor {

    public Object visit(OthelloBoard board);

    public Object visit(Othello othello);

}

package model;

public class VisitorGetRC implements Visitor{
    private int row;
    private int col;

    public VisitorGetRC(int row, int col) {
        this.col = col;
        this.row = row;
    }

    @Override
    public Object visit(OthelloBoard board) {
        if (0 <= row && row < board.dim && 0 <= col && col < board.dim) {
            return board.board[row][col];
        }
        return OthelloBoard.EMPTY;
    }

    @Override
    public Character visit(Othello othello) {
        return Character.valueOf(othello.board.get(this.row, this.col));
    }

}

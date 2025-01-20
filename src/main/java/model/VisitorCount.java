package model;

public class VisitorCount implements Visitor {

    private char player;

    public VisitorCount(char p) {
        this.player = p;
    }

    public Integer visit(OthelloBoard board) {
        int count = 0;
        for (int row = 0; row < board.dim; row++) {
            for (int col = 0; col < board.dim; col++) {
                if (board.board[row][col] == player)
                    count++;
            }
        }
        return count;
    }

    @Override
    public Integer visit(Othello othello) {
        return othello.board.getCount(this.player);
    }
}

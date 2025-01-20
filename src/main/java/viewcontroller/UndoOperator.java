package viewcontroller;

import java.util.ArrayList;

/**
 * A class the performs the Undo Operation
 *
 * @author Vishrut Kevadiya
 *
 */
public class UndoOperator {
    private ArrayList<Command> moveStack;

    public UndoOperator() {
        moveStack = new ArrayList<Command>();
    }

    /**
     * Add the currently made mode to the stack
     *
     * @param command the move currently made in the game
     */
    public void acceptMove(Command command) {
        this.moveStack.add(command);
    }

    /**
     * Performing the undo operation
     *
     */
    public void undoMove() {
        if (this.moveStack.size() > 0) {
            this.moveStack.remove(moveStack.size() - 1); // Remove the latest move
        }
        for (Command command : this.moveStack) {
            command.execute();
        }

    }

    public boolean hasMoves() {
        return this.moveStack.size() > 0;
    }
}

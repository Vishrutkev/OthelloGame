package viewcontroller;

import javafx.scene.control.Label;
import model.OthelloModel;
import util.Observable;
import util.Observer;

/**
 * A class that represents the result of the game
 *
 * @author Vishrut Kevadiya
 *
 */
public class WinnerLabel extends Label implements Observer {

    @Override
    /**
     * A Method that updates the Label's text when either the game is over
     *
     * @param A concrete Observable
     *
     */
    public void update(Observable o) {
        OthelloModel m = (OthelloModel) o;

        if ( (m.othelloController.othello.isGameOver() && m.othelloController.othello.getWinner() == 'O')) {
            this.setText("Game Over. White token("  + m.getPlayer2() + " Player) won!");
        } else if ((m.othelloController.othello.isGameOver() && m.othelloController.othello.getWinner() == 'X')) {
            this.setText("Game Over. Black token("  + m.getPlayer1() + " Player) won!");
        } else if (m.othelloController.othello.isGameOver() && m.othelloController.othello.getWinner() == ' ') {
            this.setText("Game Over. The game ended in a tie");
        } else if(this.getText()!="Game in Progress"){
            this.setText("Game in Progress");
        }
    }
}
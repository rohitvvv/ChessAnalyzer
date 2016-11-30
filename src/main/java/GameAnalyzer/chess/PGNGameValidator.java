package GameAnalyzer.chess;

import GameAnalyzer.GameValidator;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by rohit on 28/11/16.
 * Parse the Game Notation
 */
final public class PGNGameValidator implements GameValidator{
    /**
     * Validate the algebraic notation of chess
     * @param Game Play in Algebraic Notations
     * @return Boolean True for valid game else false
     */
    public boolean validateGame(String game) {
        checkNotNull(game);
        //Get all the moves in a game
        final String [] moves = game.split("[0-9]*\\.|\n");
        final String moveValidator = "([R|(N|[8|1]|B|Q|K]|[a-h]|x)*([1-8])[+]?|O-O-O|O-O";
        for(String str: moves) {
            if(str.length()!=0){
                String turns[]=str.trim().split(" ");
                for(String turn:turns){
                    boolean validMove = turn.matches(moveValidator);
                    if(Boolean.FALSE.equals(validMove))
                        return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }
}

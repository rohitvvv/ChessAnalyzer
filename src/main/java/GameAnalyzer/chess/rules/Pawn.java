package GameAnalyzer.chess.rules;

import java.util.ArrayList;
import java.util.List;

import GameAnalyzer.Board;
import GameAnalyzer.chess.Side;
import javafx.util.Pair;

/**
 * Responsibility: Find the list of valid moves given a board configuration
 * Encodes the rules of Pawn moves and captures
 * Pawn object. 
 * 
 * @author Rohit_Vaidya
 */
public class Pawn implements ChessPiece{
    boolean available;
    Side side;
    Pawn(String an,Side side){
        available = Boolean.FALSE;
        side = side;
	}
	
	@Override
	public List<Pair<Integer, Integer>> getValidMoves(String an) {
	    List<Pair<Integer,Integer>> moveList = new ArrayList<>();
	    if(side==side.LIGHT){
	    	//Populate the list
	    }
	    else{
	    	
	    }
	 	return null;
	}

	/**
	 * Returns a list of valid moves for a given chess piece with a board configuration
	 * The piece may be blocked
	 * En passant capture
	 * Promotion of peiece
	 */
	@Override
	public List<Pair<Integer, Integer>> getValidMovies(String an, Board board) {
		return null;
	}
	
	@Override
	public void setTaken() {
		available=Boolean.TRUE;
	}
}

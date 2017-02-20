package GameAnalyzer.chess.rules;

import java.util.ArrayList;
import java.util.List;

import GameAnalyzer.Board;
import GameAnalyzer.chess.ANConvertor;
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
    List<Pair<Integer,Integer>> list = null;
    
    //Indicates a two position move of a pawn
    boolean longMove;
    Side side;
    public Pawn(Side side){
    	available= Boolean.TRUE;
    	longMove = Boolean.FALSE;
    	this.side=side;
      	list=new ArrayList<>();
    }
    //Encode Pawn Moves
    //Includes capture moves as
    int []validLightPawnPositions = {
    	0,-2,0,-1,-1,-1,1,-1
    };
    int []validDarkPawnPositions = {
    	0,2,0,1,-1,1,1,1
    };
   
    Pawn(String an,Side side){
        available = Boolean.FALSE;
        this.side = side;
	}
	
	@Override
	public List<Pair<Integer, Integer>> getValidMoves(String an) {
	    List<Pair<Integer,Integer>> moveList = new ArrayList<>();
	    int x,y,i=0;
	    Pair<Integer,Integer> position = ANConvertor.getPosition(an);
	    x=position.getKey();
	    y=position.getValue();
	    if(side==side.LIGHT){
	    	  while(i<validLightPawnPositions.length-4){
	              list.add(new Pair<Integer,Integer>(x+validLightPawnPositions[i],
	           		                              y+validLightPawnPositions[++i]));
	              i++;
	           }
	    }
	    else{
	    	 while(i<validDarkPawnPositions.length-4){
	              list.add(new Pair<Integer,Integer>(x+validDarkPawnPositions[i],
	           		                              y+validDarkPawnPositions[++i]));
	              i++;
	         }
	    }
	 	return list;
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
	
	public void setDouble(){
		longMove=Boolean.TRUE;
	}
}
package GameAnalyzer.chess.rules;

import java.util.*;

import GameAnalyzer.Board;
import GameAnalyzer.chess.ANConvertor;
import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Constants;
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
    	longMove = Boolean.TRUE;
    	this.side=side;
      	list=new ArrayList<>();
    }
    //Encode Pawn Moves
    //Includes capture moves as
    int []validLightPawnPositions = {
		0,-1,
		0,-2,
	   -1,-1,//Capture move
		1,-1 //Capture move
    };
    int []validDarkPawnPositions = {
		0,1,
		0,2,
	   -1,1, //Capture move
		1,1  //Capture move
	};
   
    Pawn(String an,Side side){
        available = Boolean.FALSE;
        this.side = side;
	}

	/**
	 * Returns a list of valid moves on the board
	 * @param an
	 * @return
	 */
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
	 * Blocked peices
	 * Capture moves
	 * TODO En passant capture
	 * TODO Promotion of piece
	 */
	public List<Pair<Integer, Integer>> getValidMovies(String an, ChessBoard board) {
		List<Pair<Integer,Integer>> moveList = new ArrayList<>();
		ChessBoard cboard = (ChessBoard)board;
		Pair<Integer,Integer> position = ANConvertor.getPosition(an);
		int x = position.getKey();
		int y = position.getValue();
		ChessBoard.Cell square ;
		if(side==Side.LIGHT){
            //1. If piece is blocked.
 			square = cboard.getPiece(x,y-1);
			if(square!=null&&!square.isOccupied()){
              	moveList.add(new Pair<>(x,y-1));
			}
			//2. If piece is available for capture
            square = cboard.getPiece(x-1,y-1);
			if(square!=null&&square.isOccupied()&&square.isCapturable(Side.LIGHT)){
				moveList.add(new Pair<>(x-1,y-1));
			}
			square = cboard.getPiece(x+1,y-1);
			if(square!=null&&square.isOccupied()&&square.isCapturable(Side.LIGHT)){
				moveList.add(new Pair<>(x+1,y-1));
			}
			//3. Double move
			square = cboard.getPiece(x,y-2);
			if(square!=null&&!square.isOccupied()&&longMove==Boolean.TRUE){
				moveList.add(new Pair<>(x,y-2));
			}
		}
		else{ //Dark Piece
        	//1. If piece is blocked
			square = cboard.getPiece(x,y-1);
			if(!square.isOccupied()){
				if(y+1<=7){
					moveList.add(new Pair<>(x,y+1));
				}
			}
			//2. If piece is available for capture
			square = cboard.getPiece(x+1,y+1);
			if(square!=null&&square.isOccupied()&&square.isCapturable(Side.DARK)){
				moveList.add(new Pair<>(x+1,y+1));
			}
			square = cboard.getPiece(x-1,y+1);
			if(square!=null&&square.isOccupied()&&square.isCapturable(Side.DARK)){
				moveList.add(new Pair<>(x-1,y+1));
			}
			//3. Double move
			square = cboard.getPiece(x,y+2);
			if(square!=null&&!square.isOccupied()&&longMove==Boolean.TRUE){
				moveList.add(new Pair<>(x,y+2));
			}
		}
		return moveList;
	}
	
	@Override
	public void setTaken() {
		available=Boolean.TRUE;
	}

	@Override
	public Side getSide() {
		return side;
	}

	public void disableLongPawnMove(){
		longMove=Boolean.FALSE;
	}

	@Override
	public String toString(){
		return Constants.Pawn;
	}

//	public static void main(String[] args) {
//		Map<ChessPiece, String> positions = new HashMap<>();
//		Pawn b2Pawn = new Pawn(Side.LIGHT);
//		positions.put(b2Pawn, "b2");
//		positions.put(new Pawn(Side.DARK), "c3");
//		positions.put(new Pawn(Side.DARK), "a3");
//		ChessBoard board = new ChessBoard(positions);
//        List<Pair<Integer,Integer>> moves = b2Pawn.getValidMovies("b2",board);
//        Iterator itr =moves.iterator();
//        while(itr.hasNext())
//			System.out.println(itr.next());
//	}
}

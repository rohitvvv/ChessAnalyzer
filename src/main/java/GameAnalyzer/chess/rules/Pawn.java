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
    Pair<Integer,Integer> position = null;
    Side side;
    public String agn = null;

    public Pawn(Side side){
    	available= Boolean.TRUE;
    	this.side=side;
      	list=new ArrayList<>();
      	this.position=position;
    }

	public void setPosition(Pair<Integer, Integer> position) {
		this.position = position;
	}

	public Pair<Integer,Integer> getPosition(){
    	return position;
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
        this.position = ANConvertor.getPosition(an);
        this.agn=an;
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
		moveList = getValidMoves(x,y);
	    return moveList;
	}

	/**
	 * Given x,y position on the board. Get the valid moves
	 * @param x
	 * @param y
	 * @return
	 */
	public List<Pair<Integer, Integer>> getValidMoves(int x,int y) {
		List<Pair<Integer,Integer>> moveList = new ArrayList<>();
		int i=0;
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
	@Override
	public List<Pair<Integer, Integer>> getValidMoves(String an, ChessBoard board) {
		List<Pair<Integer,Integer>> moveList = new ArrayList<>();
		Pair<Integer,Integer> position = ANConvertor.getPosition(an);
		int x = position.getKey();
		int y = position.getValue();
        return getValidMoves(x,y,board);
	}

	/**
	 * Special method only for pawn as it has a special capture move.
	 * This list is a subset of all valid moves Pawn has
	 * @param x
	 * @param y
	 * @param board
	 * @return
	 */
	public List<Pair<Integer,Integer>> getPawnCaptureMoves(int x,int y,ChessBoard board){
		ChessBoard cboard = (ChessBoard)board;
		List<Pair<Integer,Integer>> moveList = new ArrayList<>();
		ChessBoard.Cell square ;
		if(side==Side.LIGHT) {
			square = cboard.getPiece(x-1,y-1);
			if(square!=null && square.isOccupied() && square.isCapturable(Side.LIGHT)){
				moveList.add(new Pair<>(x-1,y-1));
			}
			square = cboard.getPiece(x+1,y-1);
			if(square!=null && square.isOccupied() && square.isCapturable(Side.LIGHT)){
				moveList.add(new Pair<>(x+1,y-1));
			}
		}
		else{
			square = cboard.getPiece(x+1,y+1);
			if(square!=null && square.isOccupied() && square.isCapturable(Side.DARK)){
				moveList.add(new Pair<>(x+1,y+1));
			}
			square = cboard.getPiece(x-1,y+1);
			if(square!=null && square.isOccupied() && square.isCapturable(Side.DARK)){
				moveList.add(new Pair<>(x-1,y+1));
			}
		}
		return moveList;
	}

	@Override
	public List<Pair<Integer, Integer>> getValidMoves(int x, int y, ChessBoard board) {
		ChessBoard cboard = (ChessBoard)board;
		List<Pair<Integer,Integer>> moveList = new ArrayList<>();
		ChessBoard.Cell square ;
		if(side==Side.LIGHT){
			//1. If piece is not blocked.
			square = cboard.getPiece(x,y-1);
			if(square!=null && !square.isOccupied()){
				moveList.add(new Pair<>(x,y-1));
			}
			//2. If piece is available for capture
			square = cboard.getPiece(x-1,y-1);
			if(square!=null && square.isOccupied() && square.isCapturable(Side.LIGHT)){
				moveList.add(new Pair<>(x-1,y-1));
			}
			square = cboard.getPiece(x+1,y-1);
			if(square!=null && square.isOccupied() && square.isCapturable(Side.LIGHT)){
				moveList.add(new Pair<>(x+1,y-1));
			}
			//3. Double move only if its not blocked
			square = cboard.getPiece(x,y-2);
			if(square!=null && !cboard.getPiece(x,y-1).isOccupied() && !square.isOccupied() && y==6){
				moveList.add(new Pair<>(x,y-2));
			}
		}
		else{ //Dark Piece
			//1. If piece is not blocked
			square = cboard.getPiece(x,y+1);
			if(square!=null && !square.isOccupied()){
				moveList.add(new Pair<>(x,y+1));
			}
			//2. If piece is available for capture
			square = cboard.getPiece(x+1,y+1);
			if(square!=null && square.isOccupied() && square.isCapturable(Side.DARK)){
				moveList.add(new Pair<>(x+1,y+1));
			}
			square = cboard.getPiece(x-1,y+1);
			if(square!=null && square.isOccupied() && square.isCapturable(Side.DARK)){
				moveList.add(new Pair<>(x-1,y+1));
			}
			//3. Double move if piece is not blocked. Only pawn in 2 and 7th rank can make a two step move
			square = cboard.getPiece(x,y+2);
			if(square!=null && !cboard.getPiece(x,y+1).isOccupied() && !square.isOccupied() &&y==1){
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

	@Override
	public String toString(){
		return Constants.Pawn;
	}

}

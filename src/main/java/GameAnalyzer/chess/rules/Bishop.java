package GameAnalyzer.chess.rules;

import java.util.*;

import GameAnalyzer.Board;

import GameAnalyzer.chess.ANConvertor;
import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Constants;
import GameAnalyzer.chess.Side;
import javafx.util.Pair;

public class Bishop implements ChessPiece{
    Side side;
    Pair<Integer,Integer> position;
	List<Pair<Integer,Integer>> list = null;

    public Bishop(Side side){
    	this.side=side;
	}

	@Override
	public List<Pair<Integer, Integer>> getValidMoves(String an){
		List<Pair<Integer,Integer>> moveList = new ArrayList<>();
		int x,y,i=0;
		Pair<Integer,Integer> position = ANConvertor.getPosition(an);
		x=position.getKey();
		y=position.getValue();
		return getValidMoves(x,y);
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
		int xd,yd;
		xd=x;yd=y;  //Reset
		while(RulesUtils.inBounds(--xd,--yd)){
	      moveList.add(new Pair<>(xd,yd));
		}
		xd=x;yd=y; //Reset
		while(RulesUtils.inBounds(--xd,++yd)){
			moveList.add(new Pair<>(xd,yd));
		}
		xd=x;yd=y; //Reset
		while(RulesUtils.inBounds(++xd,--yd)){
			moveList.add(new Pair<>(xd,yd));
		}
		xd=x;yd=y; //Reset
		while(RulesUtils.inBounds(++xd,++yd)){
			moveList.add(new Pair<>(xd,yd));
		}
 		return moveList;
	}

	@Override
	public List<Pair<Integer, Integer>> getValidMoves(String an, ChessBoard board) {
		List<Pair<Integer,Integer>> moveList = new ArrayList<>();
	    Pair<Integer,Integer> position = ANConvertor.getPosition(an);
	    int x = position.getKey();
	    int y = position.getValue();
		int xd,yd;
		xd=x;yd=y;  //Reset
		while(RulesUtils.inBounds(--xd,--yd) && !board.getPiece(xd,yd).isOccupied()){
			moveList.add(new Pair<>(xd,yd));
		}
		xd=x;yd=y; //Reset
		while(RulesUtils.inBounds(--xd,++yd) && !board.getPiece(xd,yd).isOccupied()){
			moveList.add(new Pair<>(xd,yd));
		}
		xd=x;yd=y; //Reset
		while(RulesUtils.inBounds(++xd,--yd) && !board.getPiece(xd,yd).isOccupied()){
			moveList.add(new Pair<>(xd,yd));
		}
		xd=x;yd=y; //Reset
		while(RulesUtils.inBounds(++xd,++yd) && !board.getPiece(xd,yd).isOccupied()){
			moveList.add(new Pair<>(xd,yd));
		}
		return moveList;
	}

	@Override
	public void setTaken() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Side getSide() {
		return side;
	}

	@Override
	public String toString(){
		return Constants.Bishop;
	}

}

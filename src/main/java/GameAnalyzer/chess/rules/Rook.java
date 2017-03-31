package GameAnalyzer.chess.rules;

import java.util.*;

import GameAnalyzer.Board;
import GameAnalyzer.chess.ANConvertor;
import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Constants;
import GameAnalyzer.chess.Side;
import javafx.util.Pair;

public class Rook implements ChessPiece{
	List<Pair<Integer,Integer>> list = null;
	int range=8;
	Side side;

	public  Rook(Side side){
		this.side=side;
		list=new ArrayList<>();
	}

	/**
	 * A rook can move either vertically up and down any number or positions
	 * or horizontally left and right any number of positions
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
		while(RulesUtils.inBounds(--xd,yd)){ //Left
			moveList.add(new Pair<>(xd,yd));
		}
		xd=x;yd=y; //Reset
		while(RulesUtils.inBounds(++xd,yd)){ //Right
			moveList.add(new Pair<>(xd,yd));
		}
		xd=x;yd=y; //Reset
		while(RulesUtils.inBounds(xd,--yd)){ //Up
			moveList.add(new Pair<>(xd,yd));
		}
		xd=x;yd=y; //Reset
		while(RulesUtils.inBounds(xd,++yd)){ //Down
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
		while(RulesUtils.inBounds(--xd,yd) && (!board.getPiece(xd,yd).isOccupied() || board.getPiece(xd,yd).isCapturable(side))){
			moveList.add(new Pair<>(xd,yd));
			if(board.getPiece(xd,yd).isOccupied()&&board.getPiece(xd,yd).isCapturable(side))
				break;
		}
		xd=x;yd=y; //Reset
		while(RulesUtils.inBounds(++xd,yd) && (!board.getPiece(xd,yd).isOccupied() || board.getPiece(xd,yd).isCapturable(side))){
			moveList.add(new Pair<>(xd,yd));
			if(board.getPiece(xd,yd).isOccupied()&&board.getPiece(xd,yd).isCapturable(side))
				break;
		}
		xd=x;yd=y; //Reset
		while(RulesUtils.inBounds(xd,--yd) && (!board.getPiece(xd,yd).isOccupied() || board.getPiece(xd,yd).isCapturable(side))){
			moveList.add(new Pair<>(xd,yd));
			if(board.getPiece(xd,yd).isOccupied()&&board.getPiece(xd,yd).isCapturable(side))
				break;
		}
		xd=x;yd=y; //Reset
		while(RulesUtils.inBounds(xd,++yd) && (!board.getPiece(xd,yd).isOccupied() || board.getPiece(xd,yd).isCapturable(side))){
			moveList.add(new Pair<>(xd,yd));
			if(board.getPiece(xd,yd).isOccupied()&&board.getPiece(xd,yd).isCapturable(side))
				break;
		}
		return moveList;
	}

	@Override
	public void setTaken() {

	}

	@Override
	public Side getSide() {
		return side;
	}

	@Override
	public String toString(){
		return Constants.Rook;
	}

}

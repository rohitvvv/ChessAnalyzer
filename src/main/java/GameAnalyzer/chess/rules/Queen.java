package GameAnalyzer.chess.rules;

import java.util.ArrayList;
import java.util.List;

import GameAnalyzer.Board;
import GameAnalyzer.chess.ANConvertor;
import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Constants;
import GameAnalyzer.chess.Side;
import javafx.util.Pair;

public class Queen implements ChessPiece {
	Side side;

	public Queen(Side side){
      this.side=side;
	}

	@Override
	public List<Pair<Integer, Integer>> getValidMoves(String an) {
		List<Pair<Integer,Integer>> moveList;
		int x,y,i=0;
		Pair<Integer,Integer> position = ANConvertor.getPosition(an);
		x=position.getKey();
		y=position.getValue();
		moveList = new Bishop(side).getValidMoves(x,y);
		moveList.addAll(new Rook(side).getValidMoves(x,y));
		return moveList;
	}

	@Override
	public List<Pair<Integer, Integer>> getValidMoves(String an, ChessBoard board) {
		List<Pair<Integer,Integer>> moveList = new ArrayList<>();
	    moveList = new Bishop(side).getValidMoves(an,board);
	    moveList.addAll(new Rook(side).getValidMoves(an,board));
        return moveList;
	}

	@Override
	public List<Pair<Integer, Integer>> getValidMoves(int x, int y, ChessBoard board) {
		List<Pair<Integer,Integer>> moveList;
		moveList = new Bishop(side).getValidMoves(x,y,board);
		moveList.addAll(new Rook(side).getValidMoves(x,y,board));
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
		return Constants.Queen;
	}

}

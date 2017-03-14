package GameAnalyzer.chess.rules;

import java.util.List;

import GameAnalyzer.Board;
import GameAnalyzer.chess.Side;
import javafx.util.Pair;

public class Knight implements ChessPiece{
    Side side;
	public Knight(Side side){
		this.side=side;

	}
	@Override
	public List<Pair<Integer, Integer>> getValidMoves(String an) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pair<Integer, Integer>> getValidMovies(String an, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTaken() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String toString(){
		return "[N]";
	}

}

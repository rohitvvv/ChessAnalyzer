package GameAnalyzer.chess.rules;

import java.util.List;

import GameAnalyzer.Board;

import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Constants;
import GameAnalyzer.chess.Side;
import javafx.util.Pair;

public class Bishop implements ChessPiece{
    Side side;
    //Pair<Integer,Integer> position;

    public Bishop(Side side){
    	this.side=side;
	}

	@Override
	public List<Pair<Integer, Integer>> getValidMoves(String an) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pair<Integer, Integer>> getValidMovies(String an, ChessBoard board) {
		// TODO Auto-generated method stub
		return null;
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

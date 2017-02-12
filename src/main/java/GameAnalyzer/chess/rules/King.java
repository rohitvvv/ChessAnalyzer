package GameAnalyzer.chess.rules;

import java.util.List;

import GameAnalyzer.Board;
import GameAnalyzer.chess.ANConvertor;
import javafx.util.Pair;

public class King implements ChessPiece{
    
	@Override
	public List<Pair<Integer, Integer>> getValidMoves(String an) {
		// TODO Auto-generated method stub
        Pair<Integer,Integer> position = ANConvertor.getPosition(an);		
		int x=position.getKey();
		int y=position.getValue();
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

}

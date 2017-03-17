package GameAnalyzer.chess.rules;

import java.util.ArrayList;
import java.util.List;

import GameAnalyzer.Board;
import GameAnalyzer.chess.ANConvertor;
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
		Pair<Integer,Integer> position = ANConvertor.getPosition(an);
		int rank = position.getKey();
		int file = position.getValue();

		for(int i=0;i<range;i++) {
			if (i != rank) {
				list.add(new Pair<Integer, Integer>(rank, i));
			}
		    if (i!=file){
				list.add(new Pair<Integer,Integer>(file,i));
			}
		}
     	return list;
	}

	@Override
	public List<Pair<Integer, Integer>> getValidMovies(String an, Board board) {

		return null;
	}

	@Override
	public void setTaken() {

	}

	@Override
	public String toString(){
		return "[R]";
	}
	
}
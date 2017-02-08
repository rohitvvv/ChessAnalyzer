package GameAnalyzer.chess.rules;
import java.util.List;

import GameAnalyzer.Board;
import javafx.util.Pair;

public interface ChessPiece {
    /**
     * Returns a list of valid moves for a given chess piece on a blank board
     * @param an
     * @return
     */
	List<Pair<Integer,Integer>> getValidMoves(String an);
	
	/**
	 * Returns a list of valid moves for a given chess pice with a board configuration
	 */
	List<Pair<Integer,Integer>> getValidMovies(String an,Board board);
}

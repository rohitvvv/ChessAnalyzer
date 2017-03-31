package GameAnalyzer.chess.rules;
import java.util.List;

import GameAnalyzer.Board;
import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Side;
import javafx.util.Pair;

public interface ChessPiece {
    /**
     * Returns a list of valid moves for a given chess piece on a blank board
     * @param an
     * @return
     */
	List<Pair<Integer,Integer>> getValidMoves(String an);
	
	/**
	 * Returns a list of valid moves for a given chess piece with a board configuration
	 */
	List<Pair<Integer,Integer>> getValidMoves(String an,ChessBoard board);

	List<Pair<Integer,Integer>> getValidMoves(int x,int y,ChessBoard board);
	/**
	 * Set the piece is taken from the board
	 */
	void setTaken();

	Side getSide();

}

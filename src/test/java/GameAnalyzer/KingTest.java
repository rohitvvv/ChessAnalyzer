package GameAnalyzer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Side;
import GameAnalyzer.chess.rules.ChessPiece;
import GameAnalyzer.chess.rules.Pawn;
import GameAnalyzer.chess.rules.Rook;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import GameAnalyzer.chess.rules.King;
import javafx.util.Pair;
public class KingTest {
	@Test 
	public void testKingPositions(){
		King king = new King(Side.LIGHT);
	    List<Pair<Integer,Integer>> positions = king.getValidMoves("Kb7");
	    assertAll("King positions",
	    		()->assertEquals(positions.get(0),new Pair<Integer,Integer>(0,0)),
	    		()->assertEquals(positions.get(1),new Pair<Integer,Integer>(1,0)),
	    		()->assertEquals(positions.get(2),new Pair<Integer,Integer>(2,0)),
	    		()->assertEquals(positions.get(3),new Pair<Integer,Integer>(2,1)),
	    		()->assertEquals(positions.get(4),new Pair<Integer,Integer>(2,2)),
	    		()->assertEquals(positions.get(5),new Pair<Integer,Integer>(1,2)),
	    		()->assertEquals(positions.get(6),new Pair<Integer,Integer>(0,2)),
	    		()->assertEquals(positions.get(7),new Pair<Integer,Integer>(0,1)));
	}

	@Test
	public void testKingMate1(){
		Map<ChessPiece, String> piecePositions1 = new HashMap<>();
		King king = new King(Side.LIGHT);
		piecePositions1.put(king, "a1");
		piecePositions1.put(new Rook(Side.DARK), "a6");
		piecePositions1.put(new Rook(Side.DARK),"b6");
		ChessBoard board1 = new ChessBoard(piecePositions1);
		List<Pair<Integer, Integer>> kingValidMoves = king.getValidMoves("a1",board1);
	    assertTrue(kingValidMoves.size()==0);
	}

	@Test
	public void testKingCheque(){
		Map<ChessPiece, String> piecePositions1 = new HashMap<>();
		King king = new King(Side.LIGHT);
		piecePositions1.put(king, "a1");
		piecePositions1.put(new Rook(Side.DARK), "a6");
		ChessBoard board1 = new ChessBoard(piecePositions1);
		List<Pair<Integer, Integer>> kingValidMoves = king.getValidMoves("a1",board1);
		assertTrue(kingValidMoves.size()==2);
	}

	@Test
	public void testPawnAttackingKing(){
		Map<ChessPiece, String> piecePositions = new HashMap<>();
		King king = new King(Side.LIGHT);
		piecePositions.put(king, "a1");
		piecePositions.put(new Pawn(Side.DARK), "b2");
		ChessBoard board1 = new ChessBoard(piecePositions);
		List<Pair<Integer, Integer>> kingValidMoves = king.getValidMoves("a1",board1);
		assertAll( "Available King Positions",
				() -> assertTrue(kingValidMoves.contains(new Pair<>(1,6))),
				() -> assertTrue(kingValidMoves.contains(new Pair<>(1,7))),
				() -> assertTrue(kingValidMoves.contains(new Pair<>(0,6))));
	}
}

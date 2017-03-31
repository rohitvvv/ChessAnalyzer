package GameAnalyzer;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.rules.ChessPiece;
import org.junit.jupiter.api.Test;

import GameAnalyzer.chess.Side;
import GameAnalyzer.chess.rules.Pawn;
import javafx.util.Pair;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnTest {
	@Test
	public void testPawnPositions() {
		Pawn pawn = new Pawn(Side.LIGHT);
		List<Pair<Integer, Integer>> positions = pawn.getValidMoves("b2"); //1,6 -> 1,4 1,5
		assertAll("Pawn positions",
				() -> assertEquals(positions.get(0), new Pair<Integer, Integer>(1, 5)),
				() -> assertEquals(positions.get(1), new Pair<Integer, Integer>(1, 4)));
	}

	@Test
	public void testPawnWithBoardConfiguration() {
		Map<ChessPiece, String> positions = new HashMap<>();
		Pawn b2Pawn = new Pawn(Side.LIGHT);
		positions.put(b2Pawn, "b2");
		positions.put(new Pawn(Side.DARK), "c3");
		positions.put(new Pawn(Side.DARK), "a3");
		ChessBoard board = new ChessBoard(positions);
		List<Pair<Integer, Integer>> moves = b2Pawn.getValidMoves("b2", board);
		assertAll("Valid Pawn Postions",
				() -> assertEquals(moves.get(0), new Pair<>(1, 5)),
				() -> assertEquals(moves.get(1), new Pair<>(0, 5)),
				() -> assertEquals(moves.get(2), new Pair<>(2, 5)),
				() -> assertEquals(moves.get(3), new Pair<>(1, 4)));
	}

	@Test
	public void testLightPawnBlocked() {
		Map<ChessPiece, String> positions = new HashMap<>();
		Pawn b2Pawn = new Pawn(Side.LIGHT);
		positions.put(b2Pawn, "b2");
		positions.put(new Pawn(Side.DARK), "b3");
		ChessBoard board = new ChessBoard(positions);
		List<Pair<Integer, Integer>> moves = b2Pawn.getValidMoves("b2", board);
		assertTrue(moves.size()==0);
	}

	@Test
	public void testDarkPawnBlocked() {
		Map<ChessPiece, String> positions = new HashMap<>();
		Pawn pawn = new Pawn(Side.DARK);
		positions.put(pawn, "a7");
		positions.put(new Pawn(Side.LIGHT), "a6");
		ChessBoard board = new ChessBoard(positions);
		List<Pair<Integer, Integer>> moves = pawn.getValidMoves("a7", board);
		assertTrue(moves.size()==0);
	}


	@Test
	public void testDarkPawnCaptures() {
		Map<ChessPiece, String> positions = new HashMap<>();
		Pawn pawn = new Pawn(Side.DARK);
		positions.put(pawn, "h7");
		positions.put(new Pawn(Side.LIGHT), "g6");
		ChessBoard board = new ChessBoard(positions);
		List<Pair<Integer, Integer>> moves = pawn.getValidMoves("h7", board);
		assertTrue(moves.size()==3);
	}


	@Test
	public void testDarkPawnMove() {
		Map<ChessPiece, String> positions = new HashMap<>();
		Pawn pawn = new Pawn(Side.DARK);
		positions.put(pawn, "e5");
		positions.put(new Pawn(Side.LIGHT), "g6");
		ChessBoard board = new ChessBoard(positions);
		List<Pair<Integer, Integer>> moves = pawn.getValidMoves("e5", board);
		assertTrue(moves.size()==1);
	}

}

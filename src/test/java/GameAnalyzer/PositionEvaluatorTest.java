package GameAnalyzer;

import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Evaluator.PositionEvaluator;
import GameAnalyzer.chess.Side;
import GameAnalyzer.chess.rules.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by vaidyar on 3/23/17.
 */
public class PositionEvaluatorTest {
    @Test
    public void testLightStrongPosition() {
        Map<ChessPiece, String> positions = new HashMap<>();
        positions.put(new Pawn(Side.LIGHT), "a2");
        positions.put(new Pawn(Side.LIGHT), "b2");
        positions.put(new Pawn(Side.LIGHT), "c2");
        positions.put(new Pawn(Side.LIGHT), "d2");
        positions.put(new Pawn(Side.LIGHT), "e2");
        positions.put(new Pawn(Side.LIGHT), "f2");
        positions.put(new Pawn(Side.LIGHT), "g2");
        positions.put(new Pawn(Side.LIGHT), "h2");
        positions.put(new Rook(Side.LIGHT), "a1");
        positions.put(new Rook(Side.LIGHT), "h1");
        positions.put(new Knight(Side.LIGHT), "b1");
        positions.put(new Knight(Side.LIGHT), "g1");
        positions.put(new Bishop(Side.LIGHT), "c1");
        positions.put(new Bishop(Side.LIGHT), "f1");
        positions.put(new Queen(Side.LIGHT), "d1");
        positions.put(new King(Side.LIGHT), "e1");

        positions.put(new Pawn(Side.DARK), "d7");
        positions.put(new Pawn(Side.DARK), "e7");
        positions.put(new Pawn(Side.DARK), "f7");
        positions.put(new Pawn(Side.DARK), "g7");
        positions.put(new Pawn(Side.DARK), "h7");
        positions.put(new Rook(Side.DARK), "a8");
        positions.put(new Rook(Side.DARK), "h8");
        positions.put(new Knight(Side.DARK), "b8");
        positions.put(new Knight(Side.DARK), "g8");
        positions.put(new Bishop(Side.DARK), "c8");
        positions.put(new Bishop(Side.DARK), "f8");
        positions.put(new Queen(Side.DARK), "d8");
        positions.put(new King(Side.DARK), "e8");
        ChessBoard board = new ChessBoard(positions);
        int eval = PositionEvaluator.evaluate(board);
        assertEquals(3, eval);
    }

    public void testEqualPosition() {
        Map<ChessPiece, String> positions = new HashMap<>();
        positions.put(new Pawn(Side.LIGHT), "a2");
        positions.put(new Pawn(Side.LIGHT), "b2");
        positions.put(new Pawn(Side.LIGHT), "c2");
        positions.put(new Pawn(Side.LIGHT), "d2");
        positions.put(new Pawn(Side.LIGHT), "e2");
        positions.put(new Pawn(Side.LIGHT), "f2");
        positions.put(new Pawn(Side.LIGHT), "g2");
        positions.put(new Pawn(Side.LIGHT), "h2");
        positions.put(new Rook(Side.LIGHT), "a1");
        positions.put(new Rook(Side.LIGHT), "h1");
        positions.put(new Knight(Side.LIGHT), "b1");
        positions.put(new Knight(Side.LIGHT), "g1");
        positions.put(new Bishop(Side.LIGHT), "c1");
        positions.put(new Bishop(Side.LIGHT), "f1");
        positions.put(new Queen(Side.LIGHT), "d1");
        positions.put(new King(Side.LIGHT), "e1");

        positions.put(new Pawn(Side.DARK), "a7");
        positions.put(new Pawn(Side.DARK), "b7");
        positions.put(new Pawn(Side.DARK), "c7");
        positions.put(new Pawn(Side.DARK), "d7");
        positions.put(new Pawn(Side.DARK), "e7");
        positions.put(new Pawn(Side.DARK), "f7");
        positions.put(new Pawn(Side.DARK), "g7");
        positions.put(new Pawn(Side.DARK), "h7");
        positions.put(new Rook(Side.DARK), "a8");
        positions.put(new Rook(Side.DARK), "h8");
        positions.put(new Knight(Side.DARK), "b8");
        positions.put(new Knight(Side.DARK), "g8");
        positions.put(new Bishop(Side.DARK), "c8");
        positions.put(new Bishop(Side.DARK), "f8");
        positions.put(new Queen(Side.DARK), "d8");
        positions.put(new King(Side.DARK), "e8");
        ChessBoard board = new ChessBoard(positions);
        int eval = PositionEvaluator.evaluate(board);
        System.out.println(eval);
        assertEquals(0, eval);
    }

    @Test
    public void testDarkStrongPosition() {
        Map<ChessPiece, String> positions = new HashMap<>();
        positions.put(new Pawn(Side.LIGHT), "d2");
        positions.put(new Pawn(Side.LIGHT), "e2");
        positions.put(new Pawn(Side.LIGHT), "f2");
        positions.put(new Pawn(Side.LIGHT), "g2");
        positions.put(new Pawn(Side.LIGHT), "h2");
        positions.put(new Rook(Side.LIGHT), "a1");
        positions.put(new Rook(Side.LIGHT), "h1");
        positions.put(new Knight(Side.LIGHT), "b1");
        positions.put(new Knight(Side.LIGHT), "g1");
        positions.put(new Bishop(Side.LIGHT), "c1");
        positions.put(new Bishop(Side.LIGHT), "f1");
        positions.put(new Queen(Side.LIGHT), "d1");
        positions.put(new King(Side.LIGHT), "e1");

        positions.put(new Pawn(Side.DARK), "a7");
        positions.put(new Pawn(Side.DARK), "b7");
        positions.put(new Pawn(Side.DARK), "c7");
        positions.put(new Pawn(Side.DARK), "d7");
        positions.put(new Pawn(Side.DARK), "e7");
        positions.put(new Pawn(Side.DARK), "f7");
        positions.put(new Pawn(Side.DARK), "g7");
        positions.put(new Pawn(Side.DARK), "h7");
        positions.put(new Rook(Side.DARK), "a8");
        positions.put(new Rook(Side.DARK), "h8");
        positions.put(new Knight(Side.DARK), "b8");
        positions.put(new Knight(Side.DARK), "g8");
        positions.put(new Bishop(Side.DARK), "c8");
        positions.put(new Bishop(Side.DARK), "f8");
        positions.put(new Queen(Side.DARK), "d8");
        positions.put(new King(Side.DARK), "e8");
        ChessBoard board = new ChessBoard(positions);
        int eval = PositionEvaluator.evaluate(board);
        System.out.println(eval);
        assertEquals(-3, eval);
    }

    @Test
    public void testLimitedPiecePostion() {
        Map<ChessPiece, String> positions = new HashMap<>();
        positions.put(new Pawn(Side.LIGHT), "a2");
        positions.put(new King(Side.LIGHT), "b2");
        positions.put(new Pawn(Side.DARK), "c2");
        positions.put(new King(Side.DARK), "d2");
        ChessBoard board = new ChessBoard(positions);
        int eval = PositionEvaluator.evaluate(board);
        assertTrue(eval == 0);
    }

    @Test
    public void testLimitedPieceDarkStrongPostion() {
        Map<ChessPiece, String> positions = new HashMap<>();
        positions.put(new King(Side.LIGHT), "b2");
        positions.put(new Pawn(Side.DARK), "c2");
        positions.put(new King(Side.DARK), "d2");
        ChessBoard board = new ChessBoard(positions);
        int eval = PositionEvaluator.evaluate(board);
        assertTrue(eval<0);
    }

    @Test
    public void testLimitedPieceLightStrongPostion() {
        Map<ChessPiece, String> positions = new HashMap<>();
        positions.put(new King(Side.LIGHT), "b2");
        positions.put(new Pawn(Side.LIGHT), "c2");
        positions.put(new King(Side.DARK), "d2");
        ChessBoard board = new ChessBoard(positions);
        int eval = PositionEvaluator.evaluate(board);
        assertTrue(eval>0);
    }
}
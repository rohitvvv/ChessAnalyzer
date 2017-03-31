package GameAnalyzer;

import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Side;
import GameAnalyzer.chess.rules.ChessPiece;
import GameAnalyzer.chess.rules.Knight;
import GameAnalyzer.chess.rules.Pawn;
import GameAnalyzer.chess.rules.Rook;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * Created by vaidyar on 3/31/17.
 */
public class RookTest {
    @Test
    public void testRookPositions() {
        //Bishop on Blank board
        Rook rook = new Rook(Side.LIGHT);
        List<Pair<Integer, Integer>> positions = rook.getValidMoves("e4"); //1,6 -> 1,4 1,5
        assertAll("Rook positions",
                () -> assertTrue(positions.contains(new Pair<>(3,4))),
                () -> assertTrue(positions.contains(new Pair<>(2,4))),
                () -> assertTrue(positions.contains(new Pair<>(1,4))),
                () -> assertTrue(positions.contains(new Pair<>(0,4))),
                () -> assertTrue(positions.contains(new Pair<>(5,4))),
                () -> assertTrue(positions.contains(new Pair<>(6,4))),
                () -> assertTrue(positions.contains(new Pair<>(7,4))),
                () -> assertTrue(positions.contains(new Pair<>(4,3))),
                () -> assertTrue(positions.contains(new Pair<>(4,2))),
                () -> assertTrue(positions.contains(new Pair<>(4,1))),
                () -> assertTrue(positions.contains(new Pair<>(4,0))),
                () -> assertTrue(positions.contains(new Pair<>(4,5))),
                () -> assertTrue(positions.contains(new Pair<>(4,6))),
                () -> assertTrue(positions.contains(new Pair<>(4,7)))
        );

//      Rook is blocked by own pieces
        Map<ChessPiece, String> piecePositions2 = new HashMap<>();
        Rook rook2 = new Rook(Side.LIGHT);
        piecePositions2.put(rook, "h1");
        piecePositions2.put(new Pawn(Side.LIGHT), "g2");
        piecePositions2.put(new Pawn(Side.LIGHT), "h2");
        piecePositions2.put(new Knight(Side.LIGHT), "g1");
        ChessBoard board2 = new ChessBoard(piecePositions2);
        List<Pair<Integer, Integer>> validRookMoves2 = rook2.getValidMoves("h1", board2);
        assertTrue(validRookMoves2.size() == 0);


//      Rook is surrounded by opposite pieces
        Map<ChessPiece, String> piecePositions1 = new HashMap<>();
        Rook rook1 = new Rook(Side.LIGHT);
        piecePositions1.put(rook, "h1");
        piecePositions1.put(new Pawn(Side.DARK), "g2");
        piecePositions1.put(new Pawn(Side.DARK), "h2");
        piecePositions1.put(new Knight(Side.DARK), "g1");
        ChessBoard board1 = new ChessBoard(piecePositions1);
        List<Pair<Integer, Integer>> validRookMoves1 = rook1.getValidMoves("h1", board1);
        assertTrue(validRookMoves1.size() == 2);

//      Rook is surrounded by opposite pieces with one position to move
        Map<ChessPiece, String> piecePositions3 = new HashMap<>();
        Rook rook3 = new Rook(Side.LIGHT);
        piecePositions3.put(rook3, "h1");
        piecePositions3.put(new Pawn(Side.DARK), "g2");
        piecePositions3.put(new Pawn(Side.DARK), "h2");
        piecePositions3.put(new Knight(Side.DARK), "f1");
        ChessBoard board3 = new ChessBoard(piecePositions3);
        List<Pair<Integer, Integer>> validRookMoves3 = rook1.getValidMoves("h1", board3);
        assertTrue(validRookMoves3.size() == 3);
    }

}

package GameAnalyzer;

import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Side;
import GameAnalyzer.chess.rules.*;
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
public class QueenTest {
    @Test
    public void testQueenPositions() {
        //Queen on Blank board
        Queen queen = new Queen(Side.LIGHT);
        List<Pair<Integer, Integer>> positions = queen.getValidMoves("d4"); //1,6 -> 1,4 1,5
        assertAll("Queen positions",
                () -> assertTrue(positions.contains(new Pair<>(2, 3))),
                () -> assertTrue(positions.contains(new Pair<>(1, 2))),
                () -> assertTrue(positions.contains(new Pair<>(0, 1))),
                () -> assertTrue(positions.contains(new Pair<>(2, 5))),
                () -> assertTrue(positions.contains(new Pair<>(1, 6))),
                () -> assertTrue(positions.contains(new Pair<>(0, 7))),
                () -> assertTrue(positions.contains(new Pair<>(4, 3))),
                () -> assertTrue(positions.contains(new Pair<>(5, 2))),
                () -> assertTrue(positions.contains(new Pair<>(6, 1))),
                () -> assertTrue(positions.contains(new Pair<>(7, 0))),
                () -> assertTrue(positions.contains(new Pair<>(4, 5))),
                () -> assertTrue(positions.contains(new Pair<>(5, 6))),
                () -> assertTrue(positions.contains(new Pair<>(6, 7))),

                () -> assertTrue(positions.contains(new Pair<>(2,4))),
                () -> assertTrue(positions.contains(new Pair<>(1,4))),
                () -> assertTrue(positions.contains(new Pair<>(0,4))),
                () -> assertTrue(positions.contains(new Pair<>(4,4))),
                () -> assertTrue(positions.contains(new Pair<>(5,4))),
                () -> assertTrue(positions.contains(new Pair<>(6,4))),
                () -> assertTrue(positions.contains(new Pair<>(7,4))),
                () -> assertTrue(positions.contains(new Pair<>(3,0))),
                () -> assertTrue(positions.contains(new Pair<>(3,1))),
                () -> assertTrue(positions.contains(new Pair<>(3,2))),
                () -> assertTrue(positions.contains(new Pair<>(3,3))),
                () -> assertTrue(positions.contains(new Pair<>(3,5))),
                () -> assertTrue(positions.contains(new Pair<>(3,6))),
                () -> assertTrue(positions.contains(new Pair<>(3,7)))
        );
        //A queen has three capture moves when surrounded in the corner
        Map<ChessPiece, String> piecePositions1 = new HashMap<>();
        Queen queen1 = new Queen(Side.LIGHT);
        piecePositions1.put(queen1, "h1");
        piecePositions1.put(new Pawn(Side.DARK), "g2");
        piecePositions1.put(new Pawn(Side.DARK), "h2");
        piecePositions1.put(new Knight(Side.DARK), "g1");
        ChessBoard board1 = new ChessBoard(piecePositions1);
        List<Pair<Integer, Integer>> validQueenMoves1 = queen1.getValidMoves("h1", board1);
        assertTrue(validQueenMoves1.size() == 3);

        //A queen has three capture + 1 move when surrounded in the corner
        Map<ChessPiece, String> piecePositions2 = new HashMap<>();
        Queen queen2 = new Queen(Side.LIGHT);
        piecePositions2.put(queen2, "h1");
        piecePositions2.put(new Pawn(Side.DARK), "g2");
        piecePositions2.put(new Pawn(Side.DARK), "h2");
        piecePositions2.put(new Knight(Side.DARK), "f1");
        ChessBoard board2 = new ChessBoard(piecePositions2);
        List<Pair<Integer, Integer>> validQueenMoves2 = queen2.getValidMoves("h1", board2);
        assertTrue(validQueenMoves2.size() == 4);
    }
}

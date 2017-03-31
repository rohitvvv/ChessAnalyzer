package GameAnalyzer;

import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Side;
import GameAnalyzer.chess.rules.Bishop;
import GameAnalyzer.chess.rules.ChessPiece;
import GameAnalyzer.chess.rules.Pawn;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by vaidyar on 3/31/17.
 */
public class BishopTest {
    @Test
    public void testBishopPositions() {
        //Bishop on Blank board
        Bishop pawn = new Bishop(Side.LIGHT);
        List<Pair<Integer, Integer>> positions = pawn.getValidMoves("d4"); //1,6 -> 1,4 1,5
        assertAll("Bishop positions",
                () -> assertTrue(positions.contains(new Pair<>(2,3))),
                () -> assertTrue(positions.contains(new Pair<>(1,2))),
                () -> assertTrue(positions.contains(new Pair<>(0,1))),
                () -> assertTrue(positions.contains(new Pair<>(2,5))),
                () -> assertTrue(positions.contains(new Pair<>(1,6))),
                () -> assertTrue(positions.contains(new Pair<>(0,7))),
                () -> assertTrue(positions.contains(new Pair<>(4,3))),
                () -> assertTrue(positions.contains(new Pair<>(5,2))),
                () -> assertTrue(positions.contains(new Pair<>(6,1))),
                () -> assertTrue(positions.contains(new Pair<>(7,0))),
                () -> assertTrue(positions.contains(new Pair<>(4,5))),
                () -> assertTrue(positions.contains(new Pair<>(5,6))),
                () -> assertTrue(positions.contains(new Pair<>(6,7)))
                );

        //Bishop has some surrounding pices
        Map<ChessPiece, String> piecePositions = new HashMap<>();
        Bishop bishop = new Bishop(Side.LIGHT);
        piecePositions.put(bishop, "d4");
        piecePositions.put(new Pawn(Side.DARK), "b2");
        piecePositions.put(new Pawn(Side.DARK), "b6");
        piecePositions.put(new Pawn(Side.DARK), "e5");
        piecePositions.put(new Pawn(Side.DARK), "g1");
        ChessBoard board = new ChessBoard(piecePositions);

        List<Pair<Integer,Integer>> validBishopMoves = bishop.getValidMoves("d4",board);

        assertAll("Valid Bishop Board positions",
                () -> assertTrue(positions.contains(new Pair<>(2,3))),
                () -> assertTrue(positions.contains(new Pair<>(2,5))),
                () -> assertTrue(positions.contains(new Pair<>(4,5))),
                () -> assertTrue(positions.contains(new Pair<>(5,6))));

        //Bishop is blocked and should not have any valid move
        Map<ChessPiece, String> piecePositions1 = new HashMap<>();
        Bishop bishop1 = new Bishop(Side.LIGHT);
        piecePositions.put(bishop, "c1");
        List<Pair<Integer,Integer>> moves1 = bishop.getValidMoves("c1");
        piecePositions.put(new Pawn(Side.DARK), "b2");
        piecePositions.put(new Pawn(Side.DARK), "c2");
        piecePositions.put(new Pawn(Side.DARK), "d2");
        ChessBoard board1 = new ChessBoard(piecePositions);

        List<Pair<Integer,Integer>> validBishopMoves1 = bishop.getValidMoves("c1",board1);

        assertTrue(validBishopMoves1.size()==0);

    }
}

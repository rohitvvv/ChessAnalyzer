package GameAnalyzer;

import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Side;
import GameAnalyzer.chess.rules.ChessPiece;
import GameAnalyzer.chess.rules.Knight;
import GameAnalyzer.chess.rules.Pawn;
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
public class KnightTest {
    @Test
    public void testKnightPositions() {
        //Knight on Blank board
        Knight knight = new Knight(Side.LIGHT);
        List<Pair<Integer, Integer>> positions = knight.getValidMoves("e4"); //1,6 -> 1,4 1,5
        assertAll("Knight positions",
                () -> assertTrue(positions.contains(new Pair<>(3,2))),
                () -> assertTrue(positions.contains(new Pair<>(5,2))),
                () -> assertTrue(positions.contains(new Pair<>(6,3))),
                () -> assertTrue(positions.contains(new Pair<>(6,5))),
                () -> assertTrue(positions.contains(new Pair<>(5,6))),
                () -> assertTrue(positions.contains(new Pair<>(3,6))),
                () -> assertTrue(positions.contains(new Pair<>(2,5))),
                () -> assertTrue(positions.contains(new Pair<>(2,3)))
                );
     }


    @Test
    public void testKnightEdgePositions() {
        //Knight on Blank board
        Knight knight = new Knight(Side.LIGHT);
        List<Pair<Integer, Integer>> positions = knight.getValidMoves("a1"); //1,6 -> 1,4 1,5
        assertAll("Knight positions",
                () -> assertTrue(positions.contains(new Pair<>(1,5))),
                () -> assertTrue(positions.contains(new Pair<>(2,6)))
        );
    }


    @Test
    public void testKnightAttackPositions() {
        //Knight on Blank board
        Map<ChessPiece, String> piecePositions1 = new HashMap<>();
        Knight knight = new Knight(Side.LIGHT);
        piecePositions1.put(knight, "a1");
        piecePositions1.put(new Pawn(Side.DARK), "b3");
        piecePositions1.put(new Pawn(Side.DARK), "c2");
        ChessBoard board1 = new ChessBoard(piecePositions1);
        List<Pair<Integer, Integer>> knightValidMoves = knight.getValidMoves("a1",board1);
        assertAll("Knight positions",
                () -> assertTrue(knightValidMoves.contains(new Pair<>(1,5))),
                () -> assertTrue(knightValidMoves.contains(new Pair<>(2,6)))
        );
    }

    @Test
    public void testKnightAttackPositionVariation1() {
        //Knight on Blank board
        Map<ChessPiece, String> piecePositions1 = new HashMap<>();
        Knight knight = new Knight(Side.LIGHT);
        piecePositions1.put(knight, "a1");
        piecePositions1.put(new Pawn(Side.LIGHT), "b3");
        piecePositions1.put(new Pawn(Side.DARK), "c2");
        ChessBoard board1 = new ChessBoard(piecePositions1);
        List<Pair<Integer, Integer>> knightValidMoves = knight.getValidMoves("a1",board1);
        assertAll("Knight positions",
                () -> assertTrue(knightValidMoves.contains(new Pair<>(2,6)))
        );
    }


    @Test
    public void testKnightAttackPositionVariation2() {
        Map<ChessPiece, String> piecePositions1 = new HashMap<>();
        Knight knight = new Knight(Side.LIGHT);
        piecePositions1.put(knight, "h8");
        piecePositions1.put(new Pawn(Side.DARK), "f7");
        piecePositions1.put(new Pawn(Side.DARK), "g6");
        ChessBoard board1 = new ChessBoard(piecePositions1);
        List<Pair<Integer, Integer>> knightValidMoves = knight.getValidMoves("h8",board1);
        assertAll("Knight positions",
                  () -> assertTrue(knightValidMoves.contains(new Pair<>(6,2))),
                  ()-> assertTrue(knightValidMoves.contains(new Pair<>(5,1)))
        );
    }
}

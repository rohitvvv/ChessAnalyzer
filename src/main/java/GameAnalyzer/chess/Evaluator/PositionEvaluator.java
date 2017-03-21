package GameAnalyzer.chess.Evaluator;

import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Side;
import GameAnalyzer.chess.rules.*;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vaidyar on 3/20/17.
 * Given a board configuration. Evaluate the position.
 * Eval(pos) = Summation w(i)f(i) +....+ w(n)f(n)
 * f(i) = number of each kind of piece
 * w(i) = Weight of piece
 * This will be heavily used in Alpha Beta Pruning Algorithm
 */
public class PositionEvaluator {
    final int PAWN_WEIGHT = 1;
    final int KNIGHT_WEIGHT = 3;
    final int QUEEN_WEIGHT = 9;
    final int ROOK_WEIGHT = 5;
    final int KING_WEIGHT = 20;
    final int BISHOP_WEIGHT = 3;


    static Pair<Integer,Integer> evaluate(ChessBoard board){
      HashMap<ChessPiece,Integer> map = new HashMap<>();
      for(int i=0;i<7;i++){
          for(int j=0;j<7;j++){
                
          }
      }


      Pair<Integer,Integer> eval=null;


      return eval;
    };

    public static void main(String[] args) {
        Map<ChessPiece,String> positions = new HashMap<>();
        positions.put(new Pawn(Side.LIGHT),"a2");
        positions.put(new Pawn(Side.LIGHT),"b2");
        positions.put(new Pawn(Side.LIGHT),"c2");
        positions.put(new Pawn(Side.LIGHT),"d2");
        positions.put(new Pawn(Side.LIGHT),"e2");
        positions.put(new Pawn(Side.LIGHT),"f2");
        positions.put(new Pawn(Side.LIGHT),"g2");
        positions.put(new Pawn(Side.LIGHT),"h2");
        positions.put(new Rook(Side.LIGHT),"a1");
        positions.put(new Rook(Side.LIGHT),"h1");
        positions.put(new Knight(Side.LIGHT),"b1");
        positions.put(new Knight(Side.LIGHT),"g1");
        positions.put(new Bishop(Side.LIGHT),"c1");
        positions.put(new Bishop(Side.LIGHT),"f1");
        positions.put(new Queen(Side.LIGHT),"d1");
        positions.put(new King(Side.LIGHT),"e1");

        positions.put(new Pawn(Side.DARK),"a7");
        positions.put(new Pawn(Side.DARK),"b7");
        positions.put(new Pawn(Side.DARK),"c7");
        positions.put(new Pawn(Side.DARK),"d7");
        positions.put(new Pawn(Side.DARK),"e7");
        positions.put(new Pawn(Side.DARK),"f7");
        positions.put(new Pawn(Side.DARK),"g7");
        positions.put(new Pawn(Side.DARK),"h7");
        positions.put(new Rook(Side.DARK),"a8");
        positions.put(new Rook(Side.DARK),"h8");
        positions.put(new Knight(Side.DARK),"b8");
        positions.put(new Knight(Side.DARK),"g8");
        positions.put(new Bishop(Side.DARK),"c8");
        positions.put(new Bishop(Side.DARK),"f8");
        positions.put(new Queen(Side.DARK),"d8");
        positions.put(new King(Side.DARK),"e8");
        ChessBoard board = new ChessBoard(positions);

    }
}

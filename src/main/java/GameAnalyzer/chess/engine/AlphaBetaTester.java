package GameAnalyzer.chess.engine;

import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Constants;
import GameAnalyzer.chess.rules.ChessPiece;
import GameAnalyzer.ui.ChessBoardFactory;
import javafx.util.Pair;

/**
 * Created by vaidyar on 4/2/17.
 */
public class AlphaBetaTester {
    public static void main(String[] args) {
        AlphaBeta obj = new AlphaBeta();
        //ChessBoard board = ChessBoardFactory.getPuzzle2();
        ChessBoard board = ChessBoardFactory.getStartPositionChessBoard();
        double score = obj.AlphaBetaMax(Integer.MIN_VALUE,Integer.MAX_VALUE, Constants.DEPTH,board);
//        Pair<Integer,Integer> move = obj.getBestLightMove(score);
//        ChessPiece piece = obj.getBestLightMovePiece(score);
        Pair<ChessPiece,Pair<Integer,Integer>> move =obj.getBestMove(score,obj.lightTable);
        System.out.println(move.getKey());
        System.out.println(move.getValue());

//        AlphaBeta obj = new AlphaBeta();
//        //ChessBoard board = ChessBoardFactory.getPuzzle1();
//        ChessBoard board = ChessBoardFactory.getStartPositionChessBoard();
//        double score = obj.AlphaBetaMin(Integer.MIN_VALUE,Integer.MAX_VALUE, Constants.DEPTH,board);
//        Pair<Integer,Integer> move = obj.getBestDarkMove(score);
//        ChessPiece piece = obj.getBestDarkMovePiece(score);
//        System.out.println(move);
//        System.out.println(piece);

    }
}

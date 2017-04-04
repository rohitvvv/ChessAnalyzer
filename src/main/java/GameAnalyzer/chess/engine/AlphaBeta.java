package GameAnalyzer.chess.engine;

import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Constants;
import GameAnalyzer.chess.Evaluator.PositionEvaluator;
import GameAnalyzer.chess.Side;
import GameAnalyzer.chess.rules.*;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import javafx.util.Pair;

import java.util.*;

/**
 * Created by vaidyar on 4/2/17.
 */

public class AlphaBeta {

   //Start the board with [-∞,+∞]
   //The first argument is the Alpha(Light Pieces) with the worst value. Alpha aims for higher Positive number
   //The second argument is the Beta(Dark Pieces) with the worst value. Beta aims for a smaller Negative number

   static int count = 0;
   static int TOP_LEVEL= Constants.DEPTH;

   public Table<Double,ChessPiece,Pair<Integer,Integer>> lightTable = HashBasedTable.create();
   public Table<Double,ChessPiece,Pair<Integer,Integer>> darkTable = HashBasedTable.create();

   public double AlphaBetaMax(double alpha,double beta,double depthLeft,ChessBoard board){
     List<Pair<Integer,Integer>> lightMoveList;
     double score,alphaScore;
     lightMoveList = board.computeValidLightMoves();
     alphaScore=alpha;
     ChessBoard newBoard=null;
     boolean isCapture=false;
     int x,y;
     if(depthLeft==0) {
        PositionEvaluator.evaluate(board);
        return PositionEvaluator.lightEval;
     }
     //Get all the moves on a chessBoard for an Alpha (Light Pieces)
     for(Pair<Integer,Integer> move:lightMoveList){//For all the moves
         newBoard= new ChessBoard(board);//Create a new board with the old board;
         x = move.getKey();
         y = move.getValue();
         ChessBoard.Cell cell = newBoard.getPiece(x,y);
         ChessPiece piece = findPiece(newBoard,move,Side.LIGHT);
         newBoard.setPiece(x,y,piece,cell.isOccupied()); //If its occupied its a capture move
         count++;
         System.out.println(newBoard.toString());
         System.out.println("BoardCount"+count);
         System.out.println("Depth"+depthLeft);
         //Create a new chessboard with the move.
         score=AlphaBetaMin(alphaScore,beta,depthLeft-1,newBoard);
         if(depthLeft==TOP_LEVEL){ //Record the scores to move mapping at top level
             lightTable.put(score,piece,move);
         }
         if(score>=beta)
             return beta;    //Beta cutoff
         if(score>alphaScore)
           alphaScore=score; //Alpha behaves likes a maximizer in min Max
     }
     return alphaScore;
   }

   public double AlphaBetaMin(double alpha,double beta,double depthLeft,ChessBoard board){
       List<Pair<Integer,Integer>> darkMoveList;
       double score,betaScore;
       darkMoveList=board.computeValidDarkMoves();
       betaScore=beta;
       ChessBoard newBoard=null;
       boolean isCapture=false;
       int x,y;
       if(depthLeft==0) {
           PositionEvaluator.evaluate(board);
           return -PositionEvaluator.darkEval;
       }
        //Get all the moves on a chessBoard for an Beta (Dark Pices)
       for(Pair<Integer,Integer> move:darkMoveList){//For all the moves
           newBoard= new ChessBoard(board);//Create a new board with the old board;
           x = move.getKey();
           y = move.getValue();
           ChessBoard.Cell cell = board.getPiece(x,y);
           ChessPiece piece = findPiece(newBoard,move,Side.DARK);
           newBoard.setPiece(x,y,piece,cell.isOccupied()); //If its occupied its a capture move
           count++;
           System.out.println(newBoard.toString());
           System.out.println("BoardCount"+count);
           System.out.println("Depth"+depthLeft);

           score=AlphaBetaMax(alpha,betaScore,depthLeft-1,newBoard);
           if(depthLeft==TOP_LEVEL){ //Record the scores to move mapping at top level
               darkTable.put(score,piece,move);
           }
           if(score<=alpha)
             return alpha;//Alpha cutoff
           if(score<betaScore)
              betaScore=score;
        }
        return betaScore;
    }

    //BadCode. Should not be responsibility of AlphaBeta.
    //Given a x,y find the piece that can make the move i.e: which piece can move to x y.
    public static ChessPiece findPiece(ChessBoard board,Pair<Integer,Integer> move,Side side) {
        int i,j;
        List<ChessPiece> pieces = new ArrayList<>(); //Multiple pieces can move to a square. Give precedence to major pieces.
        for(i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                ChessBoard.Cell cell = board.getPiece(i,j);
                if(cell.isOccupied()&&cell.getPeice().getSide()==side) {
                    ChessPiece piece = cell.getPeice();
                    List<Pair<Integer,Integer>> validMoves = piece.getValidMoves(i,j,board);
                    if(validMoves.contains(move))
                        pieces.add(cell.getPeice());
                }
            }
        }
        //Give precedence to major
        for(ChessPiece piece: pieces) {
            if (piece instanceof Knight)
                return piece;
        }
        for(ChessPiece piece: pieces) {
            if (piece instanceof Bishop)
                return piece;
        }
        for(ChessPiece piece: pieces) {
            if (piece instanceof Rook)
                return piece;
        }
        for(ChessPiece piece: pieces) {
            if (piece instanceof Queen)
                return piece;
        }
        for(ChessPiece piece: pieces) {
            if (piece instanceof Pawn)
                return piece;
        }
        return null;
    }

     public Pair<ChessPiece,Pair<Integer,Integer>> getBestMove(double score,Table table){
         Map<ChessPiece,Pair<Integer,Integer>> map = table.row(score);
         Pair<ChessPiece,Pair<Integer,Integer>> bestMove=null;
         for(Map.Entry<ChessPiece,Pair<Integer,Integer>> entry : map.entrySet()){
               if(entry.getKey() instanceof Knight) {
                   bestMove = new Pair<>(entry.getKey(), entry.getValue());
                   return bestMove;
               }
               if(entry.getKey() instanceof Bishop) {
                   bestMove = new Pair<>(entry.getKey(), entry.getValue());
                   return bestMove;
               }
         }
         for(Map.Entry<ChessPiece,Pair<Integer,Integer>> entry : map.entrySet()){
             if(entry.getKey() instanceof Queen) {
                 bestMove = new Pair<>(entry.getKey(), entry.getValue());
                 return bestMove;
             }

             if(entry.getKey() instanceof Rook) {
                 bestMove = new Pair<>(entry.getKey(), entry.getValue());
                 return bestMove;
             }
         }
         for(Map.Entry<ChessPiece,Pair<Integer,Integer>> entry : map.entrySet()){
             if(entry.getKey() instanceof Pawn) {
                 bestMove = new Pair<>(entry.getKey(), entry.getValue());
                 return bestMove;
             }
         }
        return bestMove;
     }
}

package GameAnalyzer.chess.engine;

import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Constants;
import GameAnalyzer.chess.Evaluator.PositionEvaluator;
import GameAnalyzer.chess.Side;
import GameAnalyzer.chess.rules.ChessPiece;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vaidyar on 4/2/17.
 */

public class AlphaBeta {

   //Start the board with [-∞,+∞]
   //The first argument is the Alpha(Light Pieces) with the worst value. Alpha aims for higher Positive number
   //The second argument is the Beta(Dark Pieces) with the worst value. Beta aims for a smaller Negative number

   static int count = 0;
   static int TOP_LEVEL= Constants.DEPTH;
   Map<Double,Pair<Integer,Integer>> scoreToMoveMapLight = new HashMap<>();
   Map<Double,Pair<Integer,Integer>> scoreToMoveMapDark = new HashMap<>();
   Map<Double,ChessPiece> scoreToPieceLight = new HashMap<>();
   Map<Double,ChessPiece> scoreToPieceDark = new HashMap<>();

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
             scoreToMoveMapLight.put(score,move);
             scoreToPieceLight.put(score,piece);
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
               scoreToMoveMapDark.put(score,move);
               scoreToPieceDark.put(score,piece);
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
        for(i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                ChessBoard.Cell cell = board.getPiece(i,j);
                if(cell.isOccupied()&&cell.getPeice().getSide()==side) {
                    ChessPiece piece = cell.getPeice();
                    List<Pair<Integer,Integer>> validMoves = piece.getValidMoves(i,j,board);
                    if(validMoves.contains(move))
                        return cell.getPeice();
                }
            }
        }
      return null;
    }

    public Pair<Integer,Integer> getBestLightMove(double score){
       return scoreToMoveMapLight.get(score);
    }

    public Pair<Integer,Integer> getBestDarkMove(double score){
        return scoreToMoveMapDark.get(score);
    }

    public ChessPiece getBestLightMovePiece(double score){
        return scoreToPieceLight.get(score);
    }

    public ChessPiece getBestDarkMovePiece(double score){
        return scoreToPieceDark.get(score);
    }

}

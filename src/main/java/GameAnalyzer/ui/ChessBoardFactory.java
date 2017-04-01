package GameAnalyzer.ui;

import GameAnalyzer.chess.ANConvertor;
import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Side;
import GameAnalyzer.chess.rules.*;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rohit_Vaidya on 3/24/2017.
 */

public class ChessBoardFactory {
    static Side sideToggle = Side.DARK;
    /**
     * Construct Board Configurations depending on the length of the game
     * @return
     */
    public static ChessBoard[] getChessBoardFromPGN(String pgn){
      String []moves = pgn.split(" ");
      for(int i=0;i<moves.length;i++){
          int index = moves[i].indexOf('.');
          if(index>0)
              moves[i]=moves[i].substring(index+1);
      }
      //Additional space for the initial configuration
      ChessBoard[] boards = new ChessBoard[moves.length+1];

      boards[0] = getStartPositionChessBoard();
      //Construct Chess Boards based on configuration
      /**
        1.e4 d6 2.d4 Nf6 3.Nc3 g6 4.f4 Bg7 5.Nf3 O-O 6.e5 Nfd7 7.h4 c5 8.e6 fxe6
        9.h5 gxh5 10.Rxh5 Nf6 11.Rh4 Nc6 12.Be3 Qa5 13.Bd3 cxd4 14.Nxd4 Nxd4 15.Bxd4 e5
        16.fxe5 dxe5 17.Be3 e4 18.Bc4+ Kh8 19.Qd4 Bg4 20.Bb3 Rac8 21.Bd2 Qa6 22.Qe3 Nd5
      */
      for(int i=1;i<moves.length+1;i++){
          //Copy the previous board
          ChessBoard board = new ChessBoard(boards[i-1]);
          // Map<ChessPiece,String> positions = boards[i-1].getPosistions();
          // ChessBoard board = new ChessBoard(positions);
          //Mutate the board based on the move
          mutateBoard(board,moves[i-1]);
          boards[i]=board;
      }
      return boards;
    };

    //Mutate the board and return the new chessboard based on the new move
    private static ChessBoard mutateBoard(ChessBoard board,String move){
        boolean captureMove = false;
        if(move.contains("O-O-O")){
            return board.queenSideCaste(toggleSide());
        }
        if(move.contains("O-O")){
            return board.kingSideCastle(toggleSide());
        }
        Pair<Integer,Integer> toPosition = ANConvertor.getPosition(move);
        int x=toPosition.getKey();
        int y=toPosition.getValue();
        ChessPiece piece = getChessPiece(move);
        if(move.contains("x")||move.contains("X"))
            captureMove=true;
        board.setPiece(x,y,piece,captureMove);
        System.out.println(board.toString());
        return board;
    }

    private static ChessPiece getChessPiece(String move){
        //Pawn move
        if(move.length()==2){
            return new Pawn(toggleSide());
        }
        //Major piece movement  Nf3 Nf6
        if(move.length()==3||move.indexOf("x")>0||move.indexOf("X")>0){
            //Knight
            if(move.contains("N")){
                return new Knight(toggleSide());
            }
            if(move.contains("B")){
                return new Bishop(toggleSide());
            }
            if(move.contains("Q")){
                return new Queen(toggleSide());
            }
            if(move.contains("K")){
                return new King(toggleSide());
            }
            if(move.contains("R")){
                return new Rook(toggleSide());
            }
            if(move.contains("a")||move.contains("b")||move.contains("c")||move.contains("d")||move.contains("e")||
               move.contains("f")||move.contains("g")||move.contains("h"))
                return new Pawn(toggleSide());
            }
        return null;
    }

    private static Side toggleSide() {
         if(sideToggle == Side.DARK) {
             sideToggle=Side.LIGHT;
             return Side.LIGHT;
         }
         else {
             sideToggle=Side.DARK;
             return Side.DARK;
         }
    }

    /**
     * Initial configuration of the board
     * @return
     */
    public static ChessBoard getStartPositionChessBoard(){
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
        return board;
    }
}

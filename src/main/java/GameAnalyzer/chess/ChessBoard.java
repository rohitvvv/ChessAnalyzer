package GameAnalyzer.chess;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import GameAnalyzer.chess.rules.*;
import javafx.util.Pair;

/**
 * ChessBoard of Cells
 */

public class ChessBoard{

	Cell [][]board;
	int dimension=8;

	/**
	 * Initialize the board
	 * @param positions is a mapping for chess piece to position on board
	 */
	public ChessBoard(Map<ChessPiece,String> positions){
		board= new Cell[dimension][dimension];
		initializeBoard(dimension);
		populateBoard(positions);
	}

	/**
	 * The initial configuration of a chess board
	 * @param dimension
	 * @return
	 */
	public void initializeBoard(int dimension) {
		this.dimension=dimension;
        for(int i=0;i<dimension;i++){
        	for(int j=0;j<dimension;j++){
        		board[i][j]= new Cell(null);
			}
		}
	}

	public void populateBoard(Map<ChessPiece,String> positions) {
        Iterator itr = positions.keySet().iterator();
        int x,y;
        while(itr.hasNext()){
			ChessPiece piece = (ChessPiece)itr.next();
			Pair<Integer,Integer> location = ANConvertor.getPosition(positions.get(piece));
			x=location.getKey();
			y=location.getValue();
		    board[x][y]= new Cell(piece);
        }
	}

    //Cell has a chess piece
	class Cell{
		boolean occupied=Boolean.TRUE;
		ChessPiece piece;
		Cell(ChessPiece piece){
			this.piece=piece;
			Optional<ChessPiece> myPeice = Optional.ofNullable(piece);
			if(myPeice.isPresent()){
				occupied=Boolean.FALSE;
			}
		}
		ChessPiece getPeice(){
			return piece;
		}
		@Override
		public String toString(){
			if(Optional.ofNullable(piece).isPresent())
			  return piece.toString();
			else
			  return "[ ]";
		}
	}
	@Override
	public String toString(){
		StringBuffer boardBuffer = new StringBuffer();
		for(int i=0;i<dimension;i++) {
			for (int j = 0; j < dimension; j++) {
				boardBuffer.append(board[i][j].toString()).append(" ");
			}
			boardBuffer.append("\n");
		}
		return boardBuffer.toString();
	}

}

package GameAnalyzer.chess;

import java.util.*;

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

	public Cell getPiece(int x,int y){
		if(x<0||x>7||y<0||y>7){
	        //TODO Use Optional
			return null;
		}
		return board[x][y];
	}


    //Cell has a chess piece
	public class Cell{
		boolean occupied=Boolean.FALSE;
		ChessPiece piece;
		Cell(ChessPiece piece){
			this.piece=piece;
			Optional<ChessPiece> myPeice = Optional.ofNullable(piece);
			if(myPeice.isPresent()){
				occupied=Boolean.TRUE;
			}
		}
		public ChessPiece getPeice(){
			return piece;
		}

		public boolean isOccupied(){
			return occupied;
		}

		public boolean isCapturable(Side side){
             if(piece.getSide()!=side)
             	return true;
             else
             	return false;
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
				boardBuffer.append(board[j][i].toString()).append(" ");
			}
			boardBuffer.append("\n");
		}
		return boardBuffer.toString();
	}

	/**
	 * Useful for Position Evaluation
 	 * @param
	 * @return Pair <LightPieceCount, DarkPieceCount>
	 */
	public Pair<HashMap<String, Integer>, HashMap<String, Integer>> getPieceCount(){
		HashMap<String,Integer> lightPieceCount = new HashMap<>();
		HashMap<String,Integer> darkPieceCount = new HashMap<>();
        initialize(lightPieceCount);
        initialize(darkPieceCount);
		for(int i=0;i<8;i++) {
			for (int j = 0; j < 8; j++) {
				Cell cellObject = board[i][j];
				if(cellObject.isOccupied()){
					ChessPiece piece= cellObject.piece;
  				    switch (cellObject.piece.toString()){
						case "[Q]":  switch (piece.getSide()){
							case DARK: updateCount(darkPieceCount,Constants.Queen);
							           break;
							case LIGHT: updateCount(lightPieceCount,Constants.Queen);
										break;
						}
						break;
						case "[R]":  switch (piece.getSide()){
							case DARK: updateCount(darkPieceCount,Constants.Rook);
										break;
							case LIGHT:updateCount(lightPieceCount,Constants.Rook);
										break;
						}
						break;
						case "[N]": switch (piece.getSide()){
							case DARK: updateCount(darkPieceCount,Constants.Knight);
										break;
							case LIGHT: updateCount(lightPieceCount,Constants.Knight);
										break;
						}break;
						case "[P]": switch (piece.getSide()){
							case DARK: updateCount(darkPieceCount,Constants.Pawn);
										break;
							case LIGHT: updateCount(lightPieceCount,Constants.Pawn);
										break;
						}break;
						case "[B]": switch (piece.getSide()){
							case DARK: updateCount(darkPieceCount,Constants.Bishop);
										break;
							case LIGHT: updateCount(lightPieceCount,Constants.Bishop);
										break;
						}break;
						case "[K]": switch (piece.getSide()){
							case DARK: updateCount(darkPieceCount,Constants.King);
								break;
							case LIGHT: updateCount(lightPieceCount,Constants.King);
								break;
						}break;
					}
				}
			}
		}
        return new Pair<>(lightPieceCount,darkPieceCount);
	}

    void updateCount(HashMap map,String chessPiece){
		Object count = map.get(chessPiece);
		if(count==null){
			map.put(chessPiece,1);
		}
		else{
			map.put(chessPiece,((Integer)count)+1);
		}
	}

	/**
	 * Initialize all map enteries to zero
	 * @param map
	 */
	void initialize(HashMap map){
       map.put(Constants.Knight,0);
       map.put(Constants.Bishop,0);
       map.put(Constants.Pawn,0);
       map.put(Constants.Queen,0);
       map.put(Constants.Rook,0);
       map.put(Constants.King,0);
	}

}

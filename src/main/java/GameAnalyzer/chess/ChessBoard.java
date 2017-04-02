package GameAnalyzer.chess;

import java.util.*;

import GameAnalyzer.chess.rules.*;
import GameAnalyzer.ui.UIConstants;
import javafx.util.Pair;

/**
 * ChessBoard of Cells
 */

public class ChessBoard{

	int dimension=8;
	Cell [][]board = new Cell[dimension][dimension];

	public ChessBoard(ChessBoard board){
	   //Explicit copy as reference copy will not work
	   for(int i=0;i<dimension;i++) {
		   for (int j = 0; j < dimension; j++) {
		   	   if((board.board[i][j]).isOccupied())
                 this.board[i][j] = new Cell(board.board[i][j].piece);
		   	   else
		   	   	 this.board[i][j]= new Cell(null);
		   }
	   }
	}

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

	/**
	 * This method sets a piece on the board and resets the previous position of the chess piece
	 * Follow the following strategy
	 * 1. Find the piece which can reach x,y
	 * 2. Move the piece
	 * 3. Reset the old position
	 * @param x
	 * @param y
	 * @param piece
	 * @return
	 */
	public boolean setPiece(int x,int y,ChessPiece piece,boolean captureMove){
		if(RulesUtils.inBounds(x,y) && piece!=null){
		  	erasePreviousPosition(x,y,piece,captureMove);
			board[x][y].piece = piece;
			board[x][y].occupied=true; //Mark as occupied
			return true;
		}
		return true;
	}

	public void erasePreviousPosition(int x,int y,ChessPiece piece,boolean captureMove){
		Side side = piece.getSide();
		Pair<Integer,Integer> position= null;
		int xc,yc;
		position = findPiece(piece,x,y,captureMove);
		xc=position.getKey();
		yc=position.getValue();
		board[xc][yc].reset();
		board[xc][yc].piece=null;
	}

	public Pair<Integer,Integer> findPiece(ChessPiece piece,int x,int y,boolean captureMove) {
	    Pair<Integer,Integer> position=null;
		int i,j;
		String cpiece = piece.toString();
		//Side side = piece.getSide();
		Pair<Integer,Integer> checkLocation = new Pair<>(x,y);
	   	for (i = 0; i < dimension; i++) {
				for (j = 0; j < dimension; j++) {
					switch (cpiece) { //find the piece and return that

						case "[P]":  if(!captureMove && (board[i][j].isOccupied()) && board[i][j].piece instanceof Pawn){
										 List<Pair<Integer,Integer>> possibleMoves = ((Pawn) board[i][j].piece).getValidMoves(i,j);
										 if(possibleMoves.contains(checkLocation)&&(piece.getSide()==board[i][j].piece.getSide()))
											return new Pair<>(i,j);
									 }
									 if(captureMove && board[i][j].piece instanceof Pawn ){
										List<Pair<Integer,Integer>> possibleMoves = ((Pawn) board[i][j].piece).getPawnCaptureMoves(i,j,this);
										if(possibleMoves.contains(checkLocation))
											return new Pair<>(i,j);
						             }
						break;

    					case "[N]":  if((captureMove==true || board[i][j].isOccupied()) && board[i][j].piece instanceof Knight){
										List<Pair<Integer,Integer>> possibleMoves = ((Knight) board[i][j].piece).getValidMoves(i,j);
										if(possibleMoves.contains(checkLocation)&&piece.getSide()==board[i][j].piece.getSide())
											return new Pair<>(i,j);
						             }
						break;

						case "[B]":  if((captureMove==true || board[i][j].isOccupied()) && board[i][j].piece instanceof Bishop){
										List<Pair<Integer,Integer>> possibleMoves = ((Bishop) board[i][j].piece).getValidMoves(i,j);
										if(possibleMoves.contains(checkLocation)&&piece.getSide()==board[i][j].piece.getSide())
											return new Pair<>(i,j);
									 }
						break;

						case "[Q]":  if((captureMove==true || board[i][j].isOccupied()) && board[i][j].piece instanceof Queen){
										List<Pair<Integer,Integer>> possibleMoves = ((Queen) board[i][j].piece).getValidMoves(i,j);
										if(possibleMoves.contains(checkLocation)&&piece.getSide()==board[i][j].piece.getSide())
											return new Pair<>(i,j);
						             }
						break;
						case "[K]":  if((captureMove==true || board[i][j].isOccupied()) && board[i][j].piece instanceof King){
										List<Pair<Integer,Integer>> possibleMoves = ((King) board[i][j].piece).getValidMoves(i,j);
										if(possibleMoves.contains(checkLocation)&&piece.getSide()==board[i][j].piece.getSide())
											return new Pair<>(i,j);
						             }
						break;
						case "[R]":  if((captureMove==true || board[i][j].isOccupied()) && board[i][j].piece instanceof Rook){
										List<Pair<Integer,Integer>> possibleMoves = ((Rook) board[i][j].piece).getValidMoves(i,j);
										if(possibleMoves.contains(checkLocation)&&piece.getSide()==board[i][j].piece.getSide())
											return new Pair<>(i,j);
									 }
						break;
					}
				}
			}
		return position;
	}

	public ChessBoard queenSideCaste(Side side){
		switch (side){
			case DARK:
				    board[2][0] = new Cell(new King(Side.DARK));
				    board[3][0] = new Cell(new Rook(Side.DARK));
				    board[4][0].reset();
				    board[4][0].piece=null;
				    board[0][0].reset();
				    board[0][0].piece=null;
				    break;
			case LIGHT:
				    board[2][7]= new Cell(new King(Side.LIGHT));
				    board[3][7]= new Cell(new Rook(Side.LIGHT));
				    board[4][7].reset();
				    board[4][7].piece=null;
				    board[0][7].reset();
				    board[0][7].piece=null;
				    break;
		}
		System.out.println(this.toString());
		return this;
	}

	public ChessBoard kingSideCastle(Side side){
		switch (side){
			case DARK:
                   board[6][0] = new Cell(new King(Side.DARK));
                   board[5][0] = new Cell(new Rook(Side.DARK));
                   board[4][0].reset();
				   board[4][0].piece=null;
                   board[7][0].reset();
				   board[7][0].piece=null;
                   break;
			case LIGHT:
				   board[6][7] = new Cell(new King(Side.LIGHT));
				   board[5][7] = new Cell(new Rook(Side.LIGHT));
				   board[4][7].reset();
				   board[4][7].piece=null;
				   board[7][7].reset();
				   board[7][7].piece=null;
				break;
		}
		return this;
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

		public void reset(){ occupied = false;}

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
							           darkPieceCount.put(UIConstants.QueenD,piece.getValidMoves(i,j,this).size());
							           break;
							case LIGHT: updateCount(lightPieceCount,Constants.Queen);
                                        lightPieceCount.put(UIConstants.QueenL,piece.getValidMoves(i,j,this).size());
										break;
						}
						break;
						case "[R]":  switch (piece.getSide()){
							case DARK: updateCount(darkPieceCount,Constants.Rook);
                                        darkPieceCount.put(UIConstants.RookD,piece.getValidMoves(i,j,this).size());
										break;
							case LIGHT:updateCount(lightPieceCount,Constants.Rook);
                                        lightPieceCount.put(UIConstants.RookL,piece.getValidMoves(i,j,this).size());
							            break;
						}
						break;
						case "[N]": switch (piece.getSide()){
							case DARK: updateCount(darkPieceCount,Constants.Knight);
                                       darkPieceCount.put(UIConstants.KnightD,piece.getValidMoves(i,j,this).size());
									   break;
							case LIGHT:updateCount(lightPieceCount,Constants.Knight);
                                       lightPieceCount.put(UIConstants.KnightL,piece.getValidMoves(i,j,this).size());
                                       break;
						}break;
						case "[P]": switch (piece.getSide()){
							case DARK: updateCount(darkPieceCount,Constants.Pawn);
                                       darkPieceCount.put(UIConstants.PawnD,piece.getValidMoves(i,j,this).size());
										break;
							case LIGHT: updateCount(lightPieceCount,Constants.Pawn);
                                        lightPieceCount.put(UIConstants.PawnL,piece.getValidMoves(i,j,this).size());
										break;
						}break;
						case "[B]": switch (piece.getSide()){
							case DARK: updateCount(darkPieceCount,Constants.Bishop);
                                       darkPieceCount.put(UIConstants.BishopD,piece.getValidMoves(i,j,this).size());
										break;
							case LIGHT: updateCount(lightPieceCount,Constants.Bishop);
                                        lightPieceCount.put(UIConstants.BishopL,piece.getValidMoves(i,j,this).size());
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
	 * Useful for Alpha Beta
	 */
	public List<Pair<Integer,Integer>> computeValidLightMoves(){
		List<Pair<Integer,Integer>> validLightMoves = new ArrayList<>();
		Map<String,List<Pair<Integer,Integer>>> moveMapping = new HashMap<>();
		for(int i=0;i<8;i++) {
			for (int j = 0; j < 8; j++) {
				Cell cellObject = board[i][j];
				if(cellObject.isOccupied()){
					ChessPiece piece= cellObject.piece;
					if(piece.getSide()==Side.LIGHT) {
					   validLightMoves.addAll(piece.getValidMoves(i,j,this));
					}
				}
			}
		}
		return validLightMoves;
	}

	/**
	 * Useful for Alpha Beta
	 */
	public List<Pair<Integer,Integer>> computeValidDarkMoves(){
		List<Pair<Integer,Integer>> validDarkMoves = new ArrayList<>();
		Map<String,List<Pair<Integer,Integer>>> moveMapping = new HashMap<>();
		for(int i=0;i<8;i++) {
			for (int j = 0; j < 8; j++) {
				Cell cellObject = board[i][j];
				if(cellObject.isOccupied()){
					ChessPiece piece= cellObject.piece;
					if(piece.getSide()==Side.DARK) {
						validDarkMoves.addAll(piece.getValidMoves(i,j,this));
					}
				}
			}
		}
		return validDarkMoves;
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
       //Use this constants for mobility count
	   map.put(UIConstants.BishopD,0);
	   map.put(UIConstants.KnightD,0);
	   map.put(UIConstants.RookD,0);
	   map.put(UIConstants.QueenD,0);
	   map.put(UIConstants.PawnD,0);
	   map.put(UIConstants.KnightD,0);
       map.put(UIConstants.BishopL,0);
       map.put(UIConstants.KnightL,0);
       map.put(UIConstants.RookL,0);
       map.put(UIConstants.QueenL,0);
       map.put(UIConstants.PawnL,0);
       map.put(UIConstants.KnightL,0);
	}


}

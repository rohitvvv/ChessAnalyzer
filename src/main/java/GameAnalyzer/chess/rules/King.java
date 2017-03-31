package GameAnalyzer.chess.rules;

import java.util.*;

import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Constants;
import GameAnalyzer.chess.Side;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import GameAnalyzer.Board;
import GameAnalyzer.chess.ANConvertor;
import javafx.util.Pair;

public class King implements ChessPiece{
    List<Pair<Integer,Integer>> list = null;
    Side side;
    static Logger logger = LoggerFactory.getLogger(ANConvertor.class.getName());
    boolean isMate = Boolean.FALSE;

    public King(Side side){
    	list=new ArrayList<>();
    	this.side=side;
    }

    //Encoding of possible positions
    //(--x,--y)  (x,--y)   (++x,y--)
    //(--x,y)    (x,y)     (++x,y)
  	//(--x,++y)  (x,++y)   (++x,++y)
    
    int []validKingPositions = {
    		 -1,-1,0,-1,1,-1,1,0,1,1,0,1,-1,1,-1,0
    };
    
    @Override
	public List<Pair<Integer, Integer>> getValidMoves(String an) {
		//A king can move freely in all adjacent squares
        Pair<Integer,Integer> position = ANConvertor.getPosition(an);		
		int x=position.getKey();
		int y=position.getValue();
		int i=0;
	    while(i<validKingPositions.length){
           list.add(new Pair<Integer,Integer>(x+validKingPositions[i],
        		                              y+validKingPositions[++i]));
           i++;
        }
	    return list;
	}

	/**
	 * This will be an expensive and important operation. Any King movement should evaluate attack from enemy pieces
	 * @param an
	 * @param board
	 * @return
	 */
	@Override
	public List<Pair<Integer, Integer>> getValidMoves(String an, ChessBoard board) {
		Pair<Integer,Integer> position = ANConvertor.getPosition(an);
		int x=position.getKey();
		int y=position.getValue();
		return getValidMoves(x,y,board);
	}

	@Override
	public List<Pair<Integer, Integer>> getValidMoves(int x, int y, ChessBoard board) {
		Set<Pair<Integer,Integer>> attackSquares;
		Set<Pair<Integer,Integer>> kingSquares = new HashSet<>();
		int i=0;
		if(side==Side.LIGHT){
			attackSquares=getEnemyAttackedSqaures(Side.DARK,board);
		}
		else{
			attackSquares=getEnemyAttackedSqaures(Side.LIGHT,board);
		}
		while(i<validKingPositions.length) {
			if (RulesUtils.inBounds(x + validKingPositions[i], y + validKingPositions[i + 1]) &&
					(!board.getPiece(x + validKingPositions[i], y + validKingPositions[i + 1]).isOccupied() ||
							board.getPiece(x + validKingPositions[i], y + validKingPositions[i + 1]).isCapturable(side))) {
				kingSquares.add(new Pair<Integer, Integer>(x + validKingPositions[i],
						y + validKingPositions[++i]));
				i++;
			}
			else{
				i=i+2;
			}
		}
		kingSquares.removeAll(attackSquares);
		list.addAll(kingSquares);
		if(list.isEmpty())
			isMate=Boolean.TRUE;
		return list;
	}

	/**
	 * Get Attacked squares by [side] pieces.
	 * @param side
	 * @param board
	 * @return
	 */
	public Set<Pair<Integer,Integer>> getEnemyAttackedSqaures(Side side, ChessBoard board){
		Set<Pair<Integer,Integer>> attackedSquares = new HashSet<>();
		for(int i=0;i<7;i++) {
			for (int j = 0; j < 7; j++) {
				boolean occupied = board.getPiece(i,j).isOccupied();
				ChessPiece piece = board.getPiece(i,j).getPeice();
				if(occupied && piece.getSide()==side){
					if(piece instanceof Pawn)
						attackedSquares.addAll(((Pawn) piece).getPawnCaptureMoves(i,j,board));
					else
						attackedSquares.addAll(piece.getValidMoves(i,j,board));
 				}
			}
		}
		return attackedSquares;
	}

	@Override
	public void setTaken() {
		// TODO Auto-generated method stub
	}

	@Override
	public Side getSide() {
		return side;
	}

	@Override
	public String toString(){
    	return Constants.King;
	}

}

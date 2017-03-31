package GameAnalyzer.chess.rules;

import java.util.*;

import GameAnalyzer.Board;
import GameAnalyzer.chess.ANConvertor;
import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Constants;
import GameAnalyzer.chess.Side;
import javafx.util.Pair;

public class Knight implements ChessPiece{
    Side side;

    int []knightMoves = {
       -1,-2,
	    1,-2,
		2,-1,
		2, 1,
		1, 2,
	   -1, 2,
	   -2, 1,
	   -2,-1
	};

    public Knight(Side side){
		this.side=side;
    }

	@Override
	public List<Pair<Integer, Integer>> getValidMoves(String an){
		List<Pair<Integer,Integer>> moveList = new ArrayList<>();
		int x,y,i=0;
		Pair<Integer,Integer> position = ANConvertor.getPosition(an);
		x=position.getKey();
		y=position.getValue();
		return getValidMoves(x,y);
	}

	/**
	 * Given x,y position on the board. Get the valid moves
	 * @param x
	 * @param y
	 * @return
	 */
	public List<Pair<Integer, Integer>> getValidMoves(int x,int y) {
		List<Pair<Integer,Integer>> moveList = new ArrayList<>();
		int i=0,count=0;
		while(count<8){
            if(RulesUtils.inBounds(x+knightMoves[i],y+knightMoves[i+1])) {
                moveList.add(new Pair<>(x + knightMoves[i], y + knightMoves[++i]));
                i++;
            }
            else {
                i = i + 2;
            }
			count++;
		}
		return moveList;
	}

	@Override
	public List<Pair<Integer, Integer>> getValidMoves(String an, ChessBoard board) {
        Pair<Integer,Integer> position = ANConvertor.getPosition(an);
        int x = position.getKey();
        int y = position.getValue();
        List<Pair<Integer,Integer>> moveList = new ArrayList<>();
        int i=0,count=0;
        while(count<8){
            if(RulesUtils.inBounds(x+knightMoves[i],y+knightMoves[i+1]) &&
                  (!board.getPiece(x+knightMoves[i],y+knightMoves[i+1]).isOccupied() ||
                    board.getPiece(x+knightMoves[i],y+knightMoves[i+1]).isCapturable(side))) {
                moveList.add(new Pair<>(x + knightMoves[i], y + knightMoves[++i]));
                i++;
            }else{
                i=i+2;
            }
            count++;
        }
        return moveList;
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
		return Constants.Knight;
	}

}

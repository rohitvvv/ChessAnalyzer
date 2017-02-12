package GameAnalyzer.chess.rules;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import GameAnalyzer.Board;
import GameAnalyzer.chess.ANConvertor;
import javafx.util.Pair;

public class King implements ChessPiece{
    List<Pair<Integer,Integer>> list = null;
    
    static Logger logger = LoggerFactory.getLogger(ANConvertor.class.getName());
    public King(){
    	list=new ArrayList<>();
    }
    //Encoding of possible positions
    //(--x,--y)  (x,--y)   (++x,y--)
    //(--x,y)    (x,y)     (++x,y)
  	//(--x,++y)  (x,++y)   (++x,++y)
    
    int []validKingPositions = {
    		 -1,-1,0,-1,1,-1,1,0,1,1,0,1,-1,1,-1,0
    };
    
    @SuppressWarnings("restriction")
	@Override
	public List<Pair<Integer, Integer>> getValidMoves(String an) {
		//A king can move freely in all adjacent squares
        Pair<Integer,Integer> position = ANConvertor.getPosition(an);		
		int x=position.getKey();
		int y=position.getValue();
		logger.info("x y "+x+" "+y);
		int i=0;
	    while(i<validKingPositions.length){
           list.add(new Pair<Integer,Integer>(x+validKingPositions[i],
        		                              y+validKingPositions[++i]));
           i++;
        }
	    logger.info(list.toString());
	    return list;
	}

	@Override
	public List<Pair<Integer, Integer>> getValidMovies(String an, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTaken() {
		// TODO Auto-generated method stub
		
	}

}

package GameAnalyzer;

import java.util.List;

import GameAnalyzer.chess.Side;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import GameAnalyzer.chess.rules.King;
import javafx.util.Pair;
public class KingTest {
	@Test 
	public void testKingPositions(){
		King king = new King(Side.LIGHT);
	    List<Pair<Integer,Integer>> positions = king.getValidMoves("Kb7");
	    assertAll("King positions",
	    		()->assertEquals(positions.get(0),new Pair<Integer,Integer>(0,0)),
	    		()->assertEquals(positions.get(1),new Pair<Integer,Integer>(1,0)),
	    		()->assertEquals(positions.get(2),new Pair<Integer,Integer>(2,0)),
	    		()->assertEquals(positions.get(3),new Pair<Integer,Integer>(2,1)),
	    		()->assertEquals(positions.get(4),new Pair<Integer,Integer>(2,2)),
	    		()->assertEquals(positions.get(5),new Pair<Integer,Integer>(1,2)),
	    		()->assertEquals(positions.get(6),new Pair<Integer,Integer>(0,2)),
	    		()->assertEquals(positions.get(7),new Pair<Integer,Integer>(0,1)));
	}
}

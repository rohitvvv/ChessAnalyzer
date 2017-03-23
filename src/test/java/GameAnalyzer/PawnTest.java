package GameAnalyzer;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import GameAnalyzer.chess.Side;
import GameAnalyzer.chess.rules.Pawn;
import javafx.util.Pair;

public class PawnTest {
	@Test 
	public void testPawnPositions(){
		Pawn pawn = new Pawn(Side.LIGHT);  
	    List<Pair<Integer,Integer>> positions = pawn.getValidMoves("b2"); //1,6 -> 1,4 1,5 
	    assertAll("Pawn positions",
	    		()->assertEquals(positions.get(0),new Pair<Integer,Integer>(1,5)),
	    		()->assertEquals(positions.get(1),new Pair<Integer,Integer>(1,4)));
	}
}

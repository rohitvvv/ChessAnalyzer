package GameAnalyzer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GameAnalyzer.chess.ANConvertor;
import javafx.util.Pair;

public class ANConverterTest {
	/** (0,0)(0,1)(0,2)(0,7)
	  * 8 . . . . . . . . 
	  * . . . . . . . . .
	  * . . . . . . . . .
	  * 3 . . . . . . . .
	  * 2 . . . . . . . .
	  * 1 . . . . . . . . 
	  *   A B C D E F G H
	  * (0,7)         (7,7)  
	 */
	
	@Test
	public void testANConvertor() {
		//Pair <rank,file>
		final Pair<Integer, Integer> pair1 = new Pair<Integer, Integer>(7,0);
		assertAll("Assert A File",
				() -> assertEquals(pair1, ANConvertor.getPosition("Ra1")),
				() -> assertEquals(pair1, ANConvertor.getPosition("RA1")),
				() -> assertEquals(pair1, ANConvertor.getPosition("ra1")));

		final Pair<Integer, Integer> pair2 = new Pair<Integer, Integer>(3,7);

		assertAll("Assert H File",
				() -> assertEquals(pair2, ANConvertor.getPosition("kH5")),
				() -> assertEquals(pair2, ANConvertor.getPosition("qh5")),
				() -> assertEquals(pair2, ANConvertor.getPosition("rh5")));

		final Pair<Integer, Integer> pair3 = new Pair<Integer, Integer>(5,2);

		assertAll("Assert C File", 
				() -> assertEquals(pair3, ANConvertor.getPosition("kc3")),
				() -> assertEquals(pair3, ANConvertor.getPosition("Kc3")),
				() -> assertEquals(pair3, ANConvertor.getPosition("qc3")));

		//final Pair<Integer, Integer> pair4 = new Pair<Integer, Integer>();
		//assertAll("Assert C File", 
			//	() -> assertEquals(pair3, ANConvertor.getPosition("b2")));
		
	
	}
}
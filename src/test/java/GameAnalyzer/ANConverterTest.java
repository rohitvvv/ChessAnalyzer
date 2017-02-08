package GameAnalyzer;

//import static org.junit.jupiter.api.Assertions
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GameAnalyzer.chess.ANConvertor;
import javafx.util.Pair;

public class ANConverterTest {
	@Test
	public void testANConvertor() {
		final Pair<Integer, Integer> pair1 = new Pair<Integer, Integer>(0, 0);
		assertAll("Assert A File",
				() -> assertEquals(pair1, ANConvertor.getMatrix("Ra1")),
				() -> assertEquals(pair1, ANConvertor.getMatrix("RA1")),
				() -> assertEquals(pair1, ANConvertor.getMatrix("ra1")));

		final Pair<Integer, Integer> pair2 = new Pair<Integer, Integer>(7, 4);

		assertAll("Assert H File",
				() -> assertEquals(pair2, ANConvertor.getMatrix("kH5")),
				() -> assertEquals(pair2, ANConvertor.getMatrix("qh5")),
				() -> assertEquals(pair2, ANConvertor.getMatrix("rh5")));

		final Pair<Integer, Integer> pair3 = new Pair<Integer, Integer>(2, 2);

		assertAll("Assert C File", 
				() -> assertEquals(pair3, ANConvertor.getMatrix("kc3")),
				() -> assertEquals(pair3, ANConvertor.getMatrix("Kc3")),
				() -> assertEquals(pair3, ANConvertor.getMatrix("qc3")));
	}
}
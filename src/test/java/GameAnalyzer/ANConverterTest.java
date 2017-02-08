package GameAnalyzer;

//import static org.junit.jupiter.api.Assertions
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GameAnalyzer.chess.ANConvertor;
import javafx.util.Pair;
public class ANConverterTest{
    @Test
	public void testANConvertor(){
        final Pair<Integer,Integer> pair = new Pair<Integer, Integer>(0, 0);;
        assertAll("Assert",
        		() -> assertEquals(pair, ANConvertor.getMatrix("Ra1")),
                () -> assertEquals(pair, ANConvertor.getMatrix("RA1")),
                () -> assertEquals(pair, ANConvertor.getMatrix("ra1")));
    	assertEquals(pair,ANConvertor.getMatrix("Ra1"));
	}
}
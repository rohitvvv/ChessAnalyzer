package GameAnalyzer;

import GameAnalyzer.chess.PGNHeaderValidator;
import junit.framework.TestCase;

/**
 * Created by rohit on 28/11/16.
 * Test case which tests the Header Validator
 */
public class PGNHeaderValidatorTest extends TestCase {
    public void testHeaderValidator(){
       HeaderValidator headerValidator = new PGNHeaderValidator();
       assertTrue(headerValidator.validateHeader("  [Event \"Wch U20\"]\n" +
               "[Site \"Kiljava\"]\n" +
               "[Date \"1984.??.??\"]\n" +
               "[Round \"?\"]\n" +
               "[White \"Anand, Viswanathan\"]\n" +
               "[Black \"Wolff, Patrick G\"]\n" +
               "[Result \"0-1\"]\n" +
               "[WhiteElo \"2285\"]\n" +
               "[BlackElo \"2225\"]\n" +
               "[ECO \"B09\"]\n"));

        assertTrue(headerValidator.validateHeader("[Event \"Madrid\"]\n" +
                "[Site \"Madrid\"]\n" +
                "[Date \"1993.??.??\"]\n" +
                "[Round \"8\"]\n" +
                "[White \"Rivas Pastor, Manuel\"]\n" +
                "[Black \"Anand, Viswanathan\"]\n" +
                "[Result \"0-1\"]\n" +
                "[WhiteElo \"2515\"]\n" +
                "[BlackElo \"2710\"]\n" +
                "[ECO \"A25\"]"));

        assertTrue(headerValidator.validateHeader("[Event \"Amber-rapid 2nd\"]\n" +
                "[Site \"Monte Carlo\"]\n" +
                "[Date \"1993.??.??\"]\n" +
                "[Round \"3\"]\n" +
                "[White \"Piket, Jeroen\"]\n" +
                "[Black \"Anand, Viswanathan\"]\n" +
                "[Result \"1/2-1/2\"]\n" +
                "[WhiteElo \"2245\"]\n" +
                "[BlackElo \"2710\"]\n" +
                "[ECO \"D18\"]"));
    }
}

package GameAnalyzer;

import GameAnalyzer.chess.PGNGameValidator;
import junit.framework.TestCase;

/**
 * Created by rohit on 28/11/16.
 */
public class PGNGameValidatorTest extends TestCase {
    public void testPGNGame(){
        PGNGameValidator gameValidator = new PGNGameValidator();
        assertTrue(gameValidator.validateGame("1.e4 c5 2.Nf3 Nc6 3.d4 cxd4 4.Nxd4 Nf6 5.Nc3 d6 6.Bg5 e6 7.Qd2 a6 8.O-O-O h6\n" +
                "9.Be3 Be7 10.Be2 Bd7 11.f4 b5 12.Bf3 Rc8 13.Nb3 Na5 14.Nxa5 Qxa5 15.Kb1 b4\n" +
                "16.Ne2 Bc6 17.Ng3 O-O 18.Bd4 d5 19.e5 Ne4 20.Bxe4 dxe4 21.Qe2 Bd5 22.b3 f5\n" +
                "23.exf6 Bxf6 24.Bxf6 Rxf6 25.Rhf1 Rcf8 26.Qe3 Qc7 27.f5 Qb7 28.fxe6 Rxf1\n" +
                "29.Nxf1 Bxe6 30.Ng3 Bd5 31.Qc5 Bf7 32.Nf5 Qb5 33.Qxb5 axb5 34.Rd4 Re8 35.Ne3 Bg6\n" +
                "36.Rxb4 Rf8 37.Rxb5 Rf2 38.Rc5  1-0"));
    }
}

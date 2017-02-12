package GameAnalyzer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import GameAnalyzer.chess.PGNGameValidator;

/**
 * Created by rohit on 28/11/16.
 */
public class PGNGameValidatorTest {
    @Test
	public void testPGNGame(){
        PGNGameValidator gameValidator = new PGNGameValidator();
        assertTrue(gameValidator.validateGame("1.e4 c5 2.Nf3 Nc6 3.d4 cxd4 4.Nxd4 Qc7 5.Nc3 e6 6.Be2 Nf6 7.f4 d6 8.Be3 Be7\n" +
                "9.Qd2 a6 10.O-O-O O-O 11.g4 b5 12.g5 Nd7 13.Bf3 Na5 14.Nf5 exf5 15.Nd5 Qd8\n" +
                "16.Nxe7+ Qxe7 17.Qxa5 fxe4 18.Bg2 Qe6 19.Rhf1 d5 20.Qc7 f5 21.gxf6 Nxf6 22.Bd4 Bd7\n" +
                "23.f5 Qc6 24.Qg3 Rac8 25.Rd2 Kh8 26.Kb1 Rce8 27.Bh3 Rf7 28.Qe3 a5 29.Rg2 b4\n" +
                "30.Bg4 Rg8 31.Be2 a4 32.h4 Re8 33.Bd1 b3 34.cxb3 Qa6 35.Be2 Bb5 36.Bxb5 Qxb5\n" +
                "37.Rfg1 axb3 38.Rxg7 Qa6 "));
        assertTrue(gameValidator.validateGame("1.d4 d5 2.c4 dxc4 3.e3 e6 4.Bxc4 Nf6 5.Nf3 a6 6.O-O c5 7.Qe2 b5 8.Bb3 Bb7\n" +
                "9.Rd1 Nbd7 10.Nc3 Bd6 11.e4 cxd4 12.Rxd4 Bc5 13.Rd3 Qc7 14.Bg5 O-O 15.a3 Ng4\n" +
                "16.Bh4 Bd6 17.h3 Nge5 18.Rd2 Nc5 19.Ba2 Ng6 20.Rad1 Bf4 21.Rc2 Nxh4 22.Nxh4 Rac8\n" +
                "23.Bb1 Qe7 24.Nf3 Rcd8 25.g3 Bh6 26.Nd4 g6 27.e5 Rd7 28.f4 Rfd8 29.Rcd2 Na4\n" +
                "30.Nxa4 bxa4 31.Be4 Bxe4 32.Qxe4 Qc5 33.Kg2 Qc4 34.Qd3 Qa2 35.Qc3 Bf8 36.b4 Qd5+\n" +
                "37.Nf3 Qb7 38.Rxd7 Rxd7 39.Rxd7 Qxd7 40.g4 Qd5 41.Kg3 h6 42.h4 Qe4 43.Qd4 Qc2\n" +
                "44.Qe3 h5 45.gxh5 gxh5 46.Ng5 Be7 47.Qf3 Bxg5 48.fxg5 Qb3 49.Kf4 Qc4+ 50.Kg3 Qd4\n" +
                "51.Qe2 Qc3+ 52.Kg2 Qxa3 53.Qd2 Qb3 54.Qd8+"));
        assertTrue(gameValidator.validateGame("1.e4 Nf6 2.e5 Nd5 3.d4 d6 4.Nf3 Bg4 5.Be2 e6 6.O-O Be7 7.c4 Nb6 8.Nc3 O-O\n" +
                "9.Be3 N8d7 10.b3 dxe5 11.Nxe5 Bxe2 12.Qxe2 Nxe5 13.dxe5 Nd7 14.Rad1 Qc8 15.f4 Bc5\n" +
                "16.Ne4 Bxe3+ 17.Qxe3 Rd8 18.h4 Nf8 19.h5 h6 20.Qc5 Rd7 21.Qb4 b6 22.Qa4 Qd8\n" +
                "23.Rxd7 Qxd7 24.Qxd7 Nxd7 25.Rd1 Nc5 26.Nc3 a6 27.b4 Nb7 28.Rd7 Rc8 29.f5 Nd8\n" +
                "30.b5 axb5 31.cxb5 Kf8 32.f6 Nb7 33.Ne4 Na5 34.Rd4 c5 35.Rd7 Nc4 36.Nd6 Nxe5\n" +
                "37.Rxf7+ Nxf7 38.Nxc8 gxf6 39.Nxb6 Ke7 40.Na4 Kd6 41.Nc3 c4 42.a4 Kc5 43.a5 Nd6\n" +
                "44.b6 Kc6 45.g4 Kb7 46.Kf2 Ka6 47.Ke3 f5 48.g5 hxg5 49.h6 e5 50.h7 Nf7 51.Na4 Kb7\n" +
                "52.Nc5+ Kc6 53.b7 Kc7 54.a6"));
        assertTrue(gameValidator.validateGame("1.e4 c5 2.Nf3 Nc6 3.d4 cxd4 4.Nxd4 e5 5.Nb5 d6 6.c4 Be7 7.N1c3 a6 8.Na3 Be6\n" +
                "9.Nc2 Rc8 10.Be3 Bg5 11.Bxg5 Qxg5 12.Qd2 Qxd2+ 13.Kxd2 g6 14.Bd3 f5 15.f3 Nf6\n" +
                "16.Nd5 Bxd5 17.cxd5 fxe4 18.Bxe4 Nb8 19.Ne3 Nxe4+ 20.fxe4 Nd7 21.Rac1 Ke7\n" +
                "22.b4 Rhf8 23.Kd3 a5 24.a3 Ra8 25.Rc7 axb4 26.axb4 Ra3+ 27.Rc3 Rxc3+ 28.Kxc3 Rf4\n" +
                "29.Kd3 Nf6 30.Rc1 Kd7 31.Rf1 Nxe4 32.Rxf4 exf4 33.Ng4 Ng5 34.h4 f3 35.g3 Nf7"));
    }
}

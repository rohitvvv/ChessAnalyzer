    package GameAnalyzer.chess.Evaluator;

import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Constants;
import GameAnalyzer.chess.Side;
import GameAnalyzer.chess.rules.*;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import javafx.geometry.Pos;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by vaidyar on 3/20/17.
 * Given a board configuration. Evaluate the position.
 * Eval(pos) = Summation w(i)f(i) +....+ w(n)f(n)
 * f(i) = number of each kind of piece
 * w(i) = Weight of piece
 * This will be heavily used in Alpha Beta Pruning Algorithm
 */
public class PositionEvaluator {
    static final int PAWN_WEIGHT = 1;
    static final int KNIGHT_WEIGHT = 3;
    static final int QUEEN_WEIGHT = 9;
    static final int ROOK_WEIGHT = 5;
    static final int KING_WEIGHT = 200;
    static final int BISHOP_WEIGHT = 3;

    /**
     * Evalue a given chess position
     *
     * @param board
     * @return f(p) =  200(K-K')
     * + 9(Q-Q')
     * + 5(R-R')
     * + 3(B-B' + N-N')
     * + 1(P-P')
     * - 0.5(D-D' + S-S' + I-I')
     * + 0.1(M-M') + ...
     * KQRBNP = number of kings, queens, rooks, bishops, knights and pawns
     * D,S,I = doubled, blocked and isolated pawns
     * M = Mobility (the number of legal moves)
     */
    public static Integer evaluate(ChessBoard board) {
        Integer value;
        Pair<HashMap<String, Integer>, HashMap<String, Integer>> pair;
        pair = board.getPieceCount();
        HashMap lightPieceCount = pair.getKey();
        HashMap darkPieceCount = pair.getValue();
        Iterator itr = lightPieceCount.keySet().iterator();

        int K, Kd, N, Nd, R, Rd, B, Bd, P, Pd, Q, Qd;

        K = (Integer) lightPieceCount.get(Constants.King);
        Kd = (Integer) darkPieceCount.get(Constants.King);

        N = (Integer) lightPieceCount.get(Constants.Knight);
        Nd = (Integer) darkPieceCount.get(Constants.Knight);

        B = (Integer) lightPieceCount.get(Constants.Bishop);
        Bd = (Integer) darkPieceCount.get(Constants.Bishop);

        P = (Integer) lightPieceCount.get(Constants.Pawn);
        Pd = (Integer) darkPieceCount.get(Constants.Pawn);

        R = (Integer) lightPieceCount.get(Constants.Rook);
        Rd = (Integer) darkPieceCount.get(Constants.Rook);

        Q = (Integer) lightPieceCount.get(Constants.Queen);
        Qd = (Integer) darkPieceCount.get(Constants.Queen);


        value = KING_WEIGHT * (K - Kd) +
                  QUEEN_WEIGHT * (Q - Qd) +
                  ROOK_WEIGHT * (R - Rd) +
                  BISHOP_WEIGHT * (B - Bd) +
                  KNIGHT_WEIGHT * (K - Kd) +
                  PAWN_WEIGHT * (P - Pd);

        return value;
    }

}

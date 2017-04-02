package GameAnalyzer.chess.Evaluator;

import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Constants;
import GameAnalyzer.chess.rules.Pawn;
import GameAnalyzer.ui.UIConstants;
import javafx.util.Pair;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by vaidyar on 3/20/17.
 * Given a board configuration. Evaluate the position.
 * Eval(pos) = Summation w(i)f(i) +....+ w(n)f(n)
 * f(i) = number of each kind of piece
 * w(i) = Weight of piece
 * This will be heavily used in Alpha Beta Pruning Algorithm
 */
public class PositionEvaluator {
    static final double PAWN_WEIGHT = 1;
    static final double KNIGHT_WEIGHT = 3;
    static final double QUEEN_WEIGHT = 9;
    static final double ROOK_WEIGHT = 5;
    static final double KING_WEIGHT = 200;
    static final double BISHOP_WEIGHT = 3;
    static final double MOBILITY = 0.1;

    public static double lightEval = 0;
    public static double darkEval = 0;
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
    public static Double evaluate(ChessBoard board) {
        Double value;
        Pair<HashMap<String, Integer>, HashMap<String, Integer>> pair;
        pair = board.getPieceCount();
        HashMap lightPieceCount = pair.getKey();
        HashMap darkPieceCount = pair.getValue();
        Iterator itr = lightPieceCount.keySet().iterator();

        int K, Kd, N, Nd, R, Rd, B, Bd, P, Pd, Q, Qd;

        int KLm, KDm,NLm,NDm,RDm,RLm, BDm,BLm,PDm,PLm, QDm,QLm;

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


        NLm = (Integer)lightPieceCount.get(UIConstants.KnightL);
        NDm = (Integer)darkPieceCount.get(UIConstants.KnightD);

        RDm = (Integer)darkPieceCount.get(UIConstants.RookD);
        RLm = (Integer)lightPieceCount.get(UIConstants.RookL);

        BDm =  (Integer)darkPieceCount.get(UIConstants.BishopD);
        BLm =  (Integer)lightPieceCount.get(UIConstants.BishopL);

        QDm =  (Integer)darkPieceCount.get(UIConstants.QueenD);
        QLm =  (Integer)lightPieceCount.get(UIConstants.QueenL);

        PDm =(Integer)darkPieceCount.get(UIConstants.PawnD);
        PLm =(Integer)lightPieceCount.get(UIConstants.PawnL);

        int lightMobility = NLm + BLm + PLm + RLm + QLm;
        int darkMobility =  NDm + BDm + PDm + RDm + QDm;

        value = KING_WEIGHT * (K - Kd) +
                  QUEEN_WEIGHT * (Q - Qd) +
                  ROOK_WEIGHT * (R - Rd) +
                  BISHOP_WEIGHT * (B - Bd) +
                  KNIGHT_WEIGHT * (N- Nd) +
                  PAWN_WEIGHT * (P - Pd) +
                  MOBILITY * (lightMobility - darkMobility);

        lightEval = K * KING_WEIGHT + Q *QUEEN_WEIGHT + R * ROOK_WEIGHT + B *BISHOP_WEIGHT  +
                    N* KNIGHT_WEIGHT + P* PAWN_WEIGHT + lightMobility * MOBILITY;
        darkEval = Kd * KING_WEIGHT+ Qd * QUEEN_WEIGHT + Rd * ROOK_WEIGHT + Bd * BISHOP_WEIGHT +
                   Nd * KNIGHT_WEIGHT + Pd * PAWN_WEIGHT + darkMobility * MOBILITY;
        return value;
    }

}

package GameAnalyzer.ui;

import GameAnalyzer.chess.Side;
import GameAnalyzer.chess.rules.ChessPiece;
import GameAnalyzer.chess.rules.Rook;
import javafx.scene.image.Image;
import static GameAnalyzer.ui.UIConstants.*;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by Rohit_Vaidya on 3/24/2017.
 */
public class PieceMapping {
    static HashMap<String,URL>  mapping = new HashMap<>();

    static {
        mapping.put(UIConstants.KingL,PieceMapping.class.getResource("/images/king-w.png"));
        mapping.put(UIConstants.QueenL,PieceMapping.class.getResource("/images/queen-w.png"));
        mapping.put(UIConstants.BishopL,PieceMapping.class.getResource("/images/bishop-w.png"));
        mapping.put(UIConstants.KnightL,PieceMapping.class.getResource("/images/knight-w.png"));
        mapping.put(UIConstants.RookL,PieceMapping.class.getResource("/images/rook-w.png"));
        mapping.put(UIConstants.PawnL,PieceMapping.class.getResource("/images/pawn-w.png"));
        mapping.put(UIConstants.KingD,PieceMapping.class.getResource("/images/king-b.png"));
        mapping.put(UIConstants.QueenD,PieceMapping.class.getResource("/images/queen-b.png"));
        mapping.put(UIConstants.BishopD,PieceMapping.class.getResource("/images/bishop-b.png"));
        mapping.put(UIConstants.KnightD,PieceMapping.class.getResource("/images/knight-b.png"));
        mapping.put(UIConstants.RookD,PieceMapping.class.getResource("/images/rook-b.png"));
        mapping.put(UIConstants.PawnD,PieceMapping.class.getResource("/images/pawn-b.png"));
     }

    public static URL getResource(Side side, ChessPiece piece){
        String cpiece = piece.toString();
        switch (cpiece) {
            case "[P]":
                switch (side) {
                    case DARK:  return mapping.get(PawnD);
                    case LIGHT: return mapping.get(PawnL);
                }
                break;
            case "[Q]":
                switch (side) {
                    case DARK:  return mapping.get(QueenD);
                    case LIGHT: return mapping.get(QueenL);
                }
                break;
            case "[R]":
                switch (side) {
                    case DARK:  return mapping.get(RookD);
                    case LIGHT: return mapping.get(RookL);
                }
                 break;

            case "[B]":
                switch (side) {
                    case DARK:
                        return mapping.get(BishopD);
                    case LIGHT:
                        return mapping.get(BishopL);
                }
                break;

            case "[N]":
                switch (side) {
                    case DARK:  return mapping.get(KnightD);
                    case LIGHT: return mapping.get(KnightL);
                }
                break;

            case "[K]":
                switch (side) {
                    case DARK:  return mapping.get(KingD);
                    case LIGHT: return mapping.get(KingL);
                }
                break;
        }
       return null;//TODO: Consider Optional
    }
}

package GameAnalyzer.chess;

import GameAnalyzer.GameParser;

import java.io.File;
import java.util.IllegalFormatException;

/**
 * Created by Rohit_Vaidya on 11/28/2016.
 */
public interface ChessParser extends GameParser {
   boolean validatePGN(File file) throws IllegalFormatException;
}

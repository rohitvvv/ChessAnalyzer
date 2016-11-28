package GameAnalyzer.chess;

import GameAnalyzer.GameValidator;
import GameAnalyzer.HeaderValidator;
import com.google.inject.Inject;
import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;

import java.io.File;
import java.util.IllegalFormatException;

/**
 * Created by Rohit_Vaidya on 11/28/2016.
 * Responsibility: Parse the PGN file for validity
 * Uses services of ChessGame Object to verify validity of the game
 */

public class PGNParser implements ChessParser {
    /**
     * Validates the input chess Portable Game Notation file.
     */
    public boolean validatePGN(File file) throws IllegalFormatException {
        return false;
    }

    /**
     * Pipe the method to use validatePGN
     */
    public boolean parseGame(File file) throws IllegalFormatException {
        return validatePGN(file);
    }
}


/**
 * This one reads the Header for the PGN file. Validates it.
 */
class PGNHeaderValidator implements HeaderValidator{

    public boolean validateHeader(String header) {
        return false;
    }
}
/**
 * Parse the Game notations
 */
class PGNGameValidator implements GameValidator{

    public boolean validateGame(String game) {
        return false;
    }
}
/**
 * Composition of PGNHeaders and PGNGame
 */
class ChessGameValidator{
    GameValidator pgnGameValidator;
    HeaderValidator headerValidator;
    @Inject
    ChessGameValidator(HeaderValidator headerValidator, GameValidator gameValidator){
               pgnGameValidator= gameValidator;
               headerValidator = headerValidator;
    }
}





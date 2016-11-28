package GameAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.IllegalFormatException;

/**
 * Created by Rohit_Vaidya on 11/28/2016.
 * Parse the game for validity
 *
 */

public interface GameParser {
    boolean parseGame(File file) throws IllegalFormatException;
}

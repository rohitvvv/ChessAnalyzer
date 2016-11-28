package GameAnalyzer;

import java.util.IllegalFormatException;

/**
 * Created by Rohit_Vaidya on 11/28/2016.
 * HeaderValidator validates the Header of the game
 * @return Boolean
 * True: Indicates the header is valid
 * False: Header format is incorrect
 */

public interface HeaderValidator {
    boolean validateHeader(String header);
}

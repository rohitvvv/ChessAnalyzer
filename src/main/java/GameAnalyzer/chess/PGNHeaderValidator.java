package GameAnalyzer.chess;

import GameAnalyzer.HeaderValidator;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Date;

/**
 * Created by rohit on 28/11/16.
 * Validate the Header of PGN Files
 */
final public class PGNHeaderValidator implements HeaderValidator{

    /**
     * Validates the header values
     * @param header of a games in PGN file
     * @return Boolean if the header field is valid field
     */
    public boolean validateHeader(String header) {
        checkNotNull(header);
        String[] fields = header.split("\n");
        String fieldMatcher = "[\\[[A-Z|a-z]*(\\s)+\"\\[A-Z|a-z|0-9|.|,|[0|1|1/2]-[0|1|1/2]|?|[\\s]*]*\"]";
        for(String field:fields){
            boolean matches = field.trim().matches(fieldMatcher);
            if(Boolean.FALSE.equals(matches))
                return matches;
        }
        return Boolean.TRUE;
    }
}
/**
 * Class which holds the PGNHeader in memory
 * Some ORM solution needs to be used to create object from PGN files
 */

class PGNHeader{
   String event;
   String site;
   Date dateTime;
   int round;
   String whitePlayer;
   String blackPlayer;
   String result;
   int whiteELO;
   int BlackELO;
   //Encyclopedia of Chess Openings
   String ECO;
}

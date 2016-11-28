package GameAnalyzer.chess;

import GameAnalyzer.HeaderValidator;

import java.util.Date;

/**
 * Created by rohit on 28/11/16.
 * Validate the Header of PGN Files
 */
final public class PGNHeaderValidator implements HeaderValidator{
    //Stub Header. To be latter replaced.
    public String theader="[Event \"Wch U20\"]\n" +
            "[Site \"Kiljava\"]\n" +
            "[Date \"1984.??.??\"]\n" +
            "[Round \"?\"]\n" +
            "[White \"Anand, Viswanathan\"]\n" +
            "[Black \"Wolff, Patrick G\"]\n" +
            "[Result \"0-1\"]\n" +
            "[WhiteElo \"2285\"]\n" +
            "[BlackElo \"2225\"]\n" +
            "[ECO \"B09\"]";

    public boolean validateHeader(String header) {

        return false;
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

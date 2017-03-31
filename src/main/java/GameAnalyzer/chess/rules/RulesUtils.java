package GameAnalyzer.chess.rules;

/**
 * Created by vaidyar on 3/31/17.
 */
public class RulesUtils {
    public static boolean inBounds(int x,int y){
        if(x>=0&&y>=0&&x<=7&&y<=7)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }
}

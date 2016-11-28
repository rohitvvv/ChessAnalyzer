package GameAnalyzer.chess;

import GameAnalyzer.GameValidator;
import GameAnalyzer.HeaderValidator;
import com.google.inject.AbstractModule;

/**
 * Created by Rohit_Vaidya on 11/28/2016.
 * Establish the mapping for the DI
 */
public class GameValidatorModule extends AbstractModule {
    protected void configure() {
        bind(GameValidator.class).to(PGNGameValidator.class);
        bind(HeaderValidator.class).to(PGNHeaderValidator.class);
    }
}

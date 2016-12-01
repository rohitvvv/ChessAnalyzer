package GameAnalyzer.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Chess analyzer
 *
 */
public class App extends Application{

    Logger logger = LoggerFactory.getLogger(App.class.getName());
    private Stage stage;

    public static void main( String[] args ) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        logger.debug("Application Starting...");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Board.fxml"));
        Parent root = loader.load();
        this.stage = stage;
        Scene scene = new Scene(root);
        stage.setResizable(true);
        stage.setTitle("Chess Analyzer");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return stage;
    }
}

package GameAnalyzer.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ROHIT on 12/1/2016.
 */
public class AppController implements Initializable {

    @FXML
    private GridPane chessBoard;
    final int boardSize = 8 ;
    Logger logger = LoggerFactory.getLogger(AppController.class.getName());

    public void initialize(URL location, ResourceBundle resources) {
       logger.info("Initializing Controller");
       initializeChessBoard();
    }

    void initializeChessBoard(){
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col ++) {
                ImageView pawn = new ImageView(new Image("images/pawn-w.png"));
                StackPane square = new StackPane();
                pawn.setFitWidth(50);
                pawn.setFitHeight(50);
                String color ;
                if ((row + col) % 2 == 0) {
                    color = "#F2D8B5";
                } else {
                    color = "#B58763";
                }
                square.setStyle("-fx-background-color: "+color+";");
                chessBoard.add(square, col, row);
            }
        }
    }
}

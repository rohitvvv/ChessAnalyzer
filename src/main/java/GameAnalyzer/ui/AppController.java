package GameAnalyzer.ui;

import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Side;
import GameAnalyzer.chess.rules.ChessPiece;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by ROHIT on 12/1/2016.
 */
public class AppController implements Initializable {

    @FXML
    private GridPane chessBoard;

    @FXML
    private MenuItem loadGame;

    @FXML
    private Button end;

    @FXML
    private Button start;

    @FXML
    private Button previousMove;

    @FXML
    private Button nextMove;

    final int boardSize = 8;
    int gameIndex=0;

    Logger logger = LoggerFactory.getLogger(AppController.class.getName());

    boolean gameLoaded=Boolean.FALSE;

    ChessBoard[] chessBoards;
    int gameSize=0;

    public void initialize(URL location, ResourceBundle resources) {
       logger.info("Initializing Controller");
       initializeChessBoard();
    }

    void initializeChessBoard(){
        gameIndex=0;
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col ++) {
                String color ;
                StackPane square = new StackPane();
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

    void loadChessBoard(ChessBoard board){
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col ++) {
                StackPane square = new StackPane();
                String color ;
                ChessBoard.Cell cell = board.getPiece(col,row);
                URL image = null;
                if(cell.isOccupied()) {
                    ChessPiece piece = cell.getPeice();
                    Side side = piece.getSide();
                    image = PieceMapping.getResource(side, piece);
                }
                if(image!=null) {
                    Image fxIamge = new Image(image.toString());
                    ImageView pieceView = new ImageView(fxIamge);
                    pieceView.setFitHeight(50);
                    pieceView.setFitWidth(50);
                    square.getChildren().add(pieceView);
                }
                if ((row + col) % 2 == 0) {
                    color = "#F2D8B5";
                } else {
                    color = "#B58763";
                }
                square.setStyle("-fx-background-color: "+color+";");
                chessBoard.add(square, col, row);
                square.requestLayout();
                chessBoard.requestLayout();
            }
        }
        chessBoard.requestLayout();
    }

    @FXML
    public void onLoadGameClicked(){
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Load Game Dialog");
        dialog.setHeaderText("Load Game");
        dialog.setContentText("PGN");
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()){
            //Do something.
            gameLoaded=Boolean.TRUE;
            System.out.println(result.get());
            chessBoards = ChessBoardFactory.getChessBoardFromPGN(result.get());
            gameSize=chessBoards.length;
        }
    }

    @FXML
    public void onEndClicked(){
        if(!gameLoaded) {
            gameNotLoadedErrorDialog();
        }
        else{

        }
    }
    @FXML
    public void onStartClicked(){
        if(!gameLoaded) {
            gameNotLoadedErrorDialog();
        }
        else{

        }
    }

    @FXML
    public void onPreviousMoveClicked(){
        if(!gameLoaded) {
            gameNotLoadedErrorDialog();
        }
        else{
            if(gameIndex<gameSize&&gameIndex>=0)
                loadChessBoard(chessBoards[--gameIndex]);
        }
    }
    @FXML
    public void onNextMoveClicked(){
        if(!gameLoaded) {
           gameNotLoadedErrorDialog();
        }
        else{
          if(gameIndex<gameSize)
            loadChessBoard(chessBoards[gameIndex++]);
        }
    }

    private void gameNotLoadedErrorDialog(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Game Load Error");
        alert.setHeaderText("Game not yet loaded");
        alert.setContentText("Load the game from the file menu");
        alert.showAndWait();
    }
}

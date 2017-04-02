package GameAnalyzer.ui;

import GameAnalyzer.chess.ANConvertor;
import GameAnalyzer.chess.ChessBoard;
import GameAnalyzer.chess.Constants;
import GameAnalyzer.chess.Evaluator.PositionEvaluator;
import GameAnalyzer.chess.Side;
import GameAnalyzer.chess.engine.AlphaBeta;
import GameAnalyzer.chess.rules.ChessPiece;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;
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

    @FXML
    private Label score;

    @FXML
    private Label darkScore;

    @FXML
    private Label lightScore;

    @FXML
    private TextInputControl moveBox;

    @FXML
    private Button playClicked;

    @FXML
    private MenuItem playMode;

    final int boardSize = 8;
    int gameIndex=0;

    Logger logger = LoggerFactory.getLogger(AppController.class.getName());

    boolean gameLoaded=Boolean.FALSE;

    ChessBoard[] chessBoards;

    ChessBoard playChessBoard;
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
                double scoreValue  = PositionEvaluator.evaluate(board);
                if(scoreValue<0) {
                    score.setStyle("-fx-background-color: red");
                    darkScore.setStyle("-fx-background-color: green");
                    lightScore.setStyle("-fx-background-color: red");
                }
                else{
                    score.setStyle("-fx-background-color: green");
                    darkScore.setStyle("-fx-background-color: red");
                    lightScore.setStyle("-fx-background-color: green");
                }
                score.setText(Double.toString(scoreValue));
                darkScore.setText(Double.toString(PositionEvaluator.darkEval));
                lightScore.setText(Double.toString(PositionEvaluator.lightEval));
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

    /**
     * TODO: This is buggy
     */
    @FXML
    public void onPreviousMoveClicked(){
        if(!gameLoaded) {
            gameNotLoadedErrorDialog();
        }
        else{
            if(gameIndex<gameSize&&gameIndex-2>=0)
                loadChessBoard(chessBoards[gameIndex-2]);
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

    @FXML
    private void onPlayModeClicked(){
        initializeChessBoard();
        loadChessBoard(ChessBoardFactory.getStartPositionChessBoard());
        playChessBoard = ChessBoardFactory.getStartPositionChessBoard();
    }

    @FXML
    private void onPlayClicked(){
        String move = moveBox.getText();
        Pair<Integer,Integer> position = ANConvertor.getPosition(move);
        ChessPiece piece = ChessBoardFactory.getChessPiece(move,Side.LIGHT);
        int x = position.getKey();
        int y = position.getValue();
        playChessBoard.setPiece(x,y,piece,playChessBoard.getPiece(x,y).isOccupied());
        loadChessBoard(playChessBoard);

        AlphaBeta obj = new AlphaBeta();
        double score = obj.AlphaBetaMin(Integer.MIN_VALUE,Integer.MAX_VALUE, Constants.DEPTH,playChessBoard);
        Pair<Integer,Integer> computerMove = obj.getBestDarkMove(score);
        ChessPiece darkMovePiece = obj.getBestDarkMovePiece(score);
        x = computerMove.getKey();
        y = computerMove.getValue();
        playChessBoard.setPiece(x,y,darkMovePiece,playChessBoard.getPiece(x,y).isOccupied());
        loadChessBoard(playChessBoard);

    }

}

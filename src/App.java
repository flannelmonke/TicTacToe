import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    private static boolean playerXTurn = true;
    private static Button[][] board = new Button[3][3];
    private static int counter = 1;
    private static Text resText = new Text();
    private static Text turn = new Text("             Player turn: X");
    public static int xScore = 0;
    public static int oScore = 0;
    public static int draw = 0;
    public static Text score = new Text("X score: " + xScore + "  || O score: " + oScore + "   || Draws: " + draw);

    @Override
    public void start(Stage primaryStage) {
        BorderPane border = new BorderPane();
        HBox bot = new HBox();
        GridPane top = new GridPane();
        top.addRow(0, turn);
        top.addRow(1, score);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        resText.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 40));
        BackgroundFill[] backgroundFill = new BackgroundFill[] {
                new BackgroundFill(Color.BLACK, null, null),
                new BackgroundFill(Color.WHITE, null, null),
                new BackgroundFill(Color.AQUAMARINE, null, null) };

        Background back = new Background(backgroundFill);
        grid.setBackground(back);

        int row = 0;
        int col = 0;
        for (row = 0; row < board.length; row++) {
            for (col = 0; col < board.length; col++) {
                Button cell = new Button();
                cell.setPrefSize(100, 100);

                board[row][col] = cell;
                int currentRow = row;
                int currentCol = col;
                cell.setOnAction(event -> {
                    onButtonClick(cell, currentRow, currentCol);
                    counter++;
                });
                grid.add(cell, col, row);

            }
        }
        Button clear = new Button("Clear");
        clear.setPrefSize(100, 50);
        clear.setOnAction(event -> {
            for (int i = 0; i < board.length; i++) {
                board[i][0].setText("");
                board[i][0].setStyle("-fx-background-color: #FFFFFF;");
                board[i][1].setText("");
                board[i][1].setStyle("-fx-background-color: #FFFFFF;");
                board[i][2].setText("");
                board[i][2].setStyle("-fx-background-color: #FFFFFF;");
                board[0][i].setText("");
                board[0][i].setStyle("-fx-background-color: #FFFFFF;");
                board[1][i].setText("");
                board[1][i].setStyle("-fx-background-color: #FFFFFF;");
                board[2][i].setText("");
                board[2][i].setStyle("-fx-background-color: #FFFFFF;");
                counter = 1;
                resText.setText("");
            }
        });
        bot.getChildren().addAll(resText, clear);
        border.setTop(top);
        top.setAlignment(Pos.CENTER);
        border.setCenter(grid);
        grid.setAlignment(Pos.CENTER);
        border.setBottom(bot);
        bot.setAlignment(Pos.CENTER);
        Scene scene = new Scene(border, 500, 500);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static String checkWin(boolean playerXTurn, Button Board[][]) {

        String result = "";
        String player = "";
        if (playerXTurn) {
            player = "X";

            result = "X wins";
        } else {
            player = "O";
            result = "O wins";
        }

        for (int i = 0; i < 3; i++) {
            // check rows
            if (Board[i][0].getText().equals(player) && Board[i][1].getText().equals(player)
                    && Board[i][2].getText().equals(player)) {
                System.out.println("x won");
                Board[i][0].setStyle("-fx-background-color: #FF0000; -fx-font-size:47");
                Board[i][1].setStyle("-fx-background-color: #FF0000; -fx-font-size:47");
                Board[i][2].setStyle("-fx-background-color: #FF0000; -fx-font-size:47");
                if (player == "X") {
                    xScore++;
                    score.setText(("X score: " + xScore + "  || O score: " + oScore + "   || Draws: " + draw));
                } else {
                    oScore++;
                    score.setText(("X score: " + xScore + "  || O score: " + oScore + "   || Draws: " + draw));
                }
                return result;
            }
            // check columns
            if (Board[0][i].getText().equals(player) && Board[1][i].getText().equals(player)
                    && Board[2][i].getText().equals(player)) {
                Board[0][i].setStyle("-fx-background-color: #FF0000; -fx-font-size:47");
                Board[1][i].setStyle("-fx-background-color: #FF0000; -fx-font-size:47");
                Board[2][i].setStyle("-fx-background-color: #FF0000; -fx-font-size:47");
                if (player == "X") {
                    xScore++;
                    score.setText(("X score: " + xScore + "  || O score: " + oScore + "   || Draws: " + draw));
                } else {
                    oScore++;
                    score.setText(("X score: " + xScore + "  || O score: " + oScore + "   || Draws: " + draw));
                }
                return result;
            }
        }

        // check diagonals
        if (Board[0][0].getText().equals(player) && Board[1][1].getText().equals(player)
                && Board[2][2].getText().equals(player)) {
            Board[0][0].setStyle("-fx-background-color: #FF0000; -fx-font-size:47");
            Board[1][1].setStyle("-fx-background-color: #FF0000; -fx-font-size:47");
            Board[2][2].setStyle("-fx-background-color: #FF0000; -fx-font-size:47");
            if (player == "X") {
                xScore++;
                score.setText(("X score: " + xScore + "  || O score: " + oScore + "   || Draws: " + draw));
            } else {
                oScore++;
                score.setText(("X score: " + xScore + "  || O score: " + oScore + "   || Draws: " + draw));
            }
            return result;
        }
        if (Board[0][2].getText().equals(player) && Board[1][1].getText().equals(player)
                && Board[2][0].getText().equals(player)) {
            Board[0][2].setStyle("-fx-background-color: #FF0000; -fx-font-size:47");
            Board[1][1].setStyle("-fx-background-color: #FF0000; -fx-font-size:47");
            Board[2][0].setStyle("-fx-background-color: #FF0000; -fx-font-size:47");
            if (player == "X") {
                xScore++;
                score.setText(("X score: " + xScore + "  || O score: " + oScore + "   || Draws: " + draw));
            } else {
                oScore++;
                score.setText(("X score: " + xScore + "  || O score: " + oScore + "   || Draws: " + draw));
            }
            return result;
        } else if (counter == 9) {
            draw++;
            score.setText(("X score: " + xScore + "  || O score: " + oScore + "   || Draws: " + draw));
            resText.setText("DRAW!");
            return "Draw! ";
        }
        return "";
    }

    public static void onButtonClick(Button cell, int row, int col) {
        System.out.println(counter);
        if (!cell.getText().isEmpty()) {
            return;
        }
        if (playerXTurn) {
            cell.setText("X");
            cell.setStyle("-fx-font-size:47");
        } else {
            cell.setText("O");
            cell.setStyle("-fx-font-size:47");
        }
        resText.setText(checkWin(playerXTurn, board));

        playerXTurn = !playerXTurn;
        if (playerXTurn) {
            turn.setText("             Player turn: X");
        } else {
            turn.setText("             Player turn: O");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

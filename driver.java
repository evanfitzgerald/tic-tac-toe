
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane; 
import java.applet.*;

public class driver extends Application
{
   private Button key;
   private Button restart;
   private Button X;
   private Button O;
   private Button[][] keyArray;
   private boolean turn;
   private boolean isWinner;
   private Label message;
   private String P1;
   private String P2;
   private String empty;
   private int count;

   public void start(Stage primaryStage)
   {     
      /////////////////////////////////////////////
      // initial set-up
      /////////////////////////////////////////////
      empty = " "; 
      P1 = "X";
      P2 = "O";
      keyArray = new Button[3][3];
      BorderPane border = new BorderPane();
      GridPane pane = new GridPane();
      GridPane pane2 = new GridPane();
      GridPane pane3 = new GridPane();
      pane.setHgap(5);
      pane.setVgap(5);
      pane2.setHgap(9);
      pane2.setVgap(10);
      pane3.setHgap(330);
      pane3.setVgap(15);
      Font mainFont = new Font("Courier New", 40);
      Font otherFont = new Font("Courier New", 26);
      
      /////////////////////////////////////////////
      // setting up rest of pane
      /////////////////////////////////////////////

      message = new Label();
      message.setFont(otherFont);
      pane3.add(message, 1, 2);

      restart = new Button("Restart");
      pane3.add(restart, 1, 1);
      restart.setOnAction(this::restartButton); 
      restart.setFont(otherFont);

      X = new Button(P1);
      pane2.add(X, 37, 1);
      X.setFont(otherFont);

      O = new Button(P2);
      pane2.add(O, 41, 1);
      O.setFont(otherFont);

      /////////////////////////////////////////////
      // initial values of the buttons
      /////////////////////////////////////////////
      for(int row = 0; row < keyArray.length; row++)
      {
         for(int col = 0; col < keyArray[0].length; col++)
         {
               key = new Button(empty);
               keyArray[row][col] = key;
               pane.add(key, col+56, row+4);
               key.setOnAction(this::processButton); 
               key.setFont(mainFont);
         }
      }

      startGame();

      /////////////////////////////////////////////
      // setting up the stage
      /////////////////////////////////////////////
      border.setTop(pane2);
      border.setCenter(pane);
      border.setBottom(pane3);
      VBox panes = new VBox(border);
      Scene game = new Scene(panes, 800, 500);
      primaryStage.setTitle("Tic Tac Toe");
      primaryStage.setScene(game);
      primaryStage.show();
    }

   ////////////////////////////////////////////////
   // process button action (when button clicked)
   ////////////////////////////////////////////////
   public void processButton(ActionEvent event)
   {
      Button button = (Button) event.getSource();
      int row = GridPane.getRowIndex(button) - 4;
      int col = GridPane.getColumnIndex(button) - 56;
      if (button.getText().equals(empty) && !isWinner)
      {  
         keyArray[row][col] = button;

         if (turn)
         { 
            button.setText(P1);
            keyArray[row][col] = button;
            O.setStyle("-fx-background-color: Red");
            X.setStyle("-fx-background-color: Light Grey"); 
            turn = !turn;
         }
         
         else if (!turn)
         {
            button.setText(P2);
            keyArray[row][col] = button;
            X.setStyle("-fx-background-color: Red");
            O.setStyle("-fx-background-color: Light Grey");  
            turn = !turn;
         }

         winCheck();
      }
   }
              
   /////////////////////////////////////////////
   // Starts the game
   /////////////////////////////////////////////
   public void startGame()
   {
      turn = true;
      isWinner = false;
      message.setText(empty);
      X.setStyle("-fx-background-color: Red");
      O.setStyle("-fx-background-color: Light Grey"); 
      count = 0;
      for(int i = 0; i < 3; i++)
      {
         for(int j = 0; j < 3; j++)
         {
               keyArray[j][i].setText(empty);
         }
      }
   }
   
   ////////////////////////////////////////////
   // Restarts the current game mode
   ////////////////////////////////////////////
   public void restartButton(ActionEvent event)
   { 
      startGame();
   }
   ////////////////////////////////////////////
   // Method to check if there is a isWinner
   ////////////////////////////////////////////
   public void winCheck()
   {
      if ((keyArray[0][0].getText() == P1 && keyArray[1][0].getText() == P1 && keyArray[2][0].getText() == P1) ||
         (keyArray[0][1].getText() == P1 && keyArray[1][1].getText() == P1 && keyArray[2][1].getText() == P1)  ||
         (keyArray[0][2].getText() == P1 && keyArray[1][2].getText() == P1 && keyArray[2][2].getText() == P1)  || 
         (keyArray[0][0].getText() == P1 && keyArray[0][1].getText() == P1 && keyArray[0][2].getText() == P1)  || 
         (keyArray[1][0].getText() == P1 && keyArray[1][1].getText() == P1 && keyArray[1][2].getText() == P1)  ||
         (keyArray[2][0].getText() == P1 && keyArray[2][1].getText() == P1 && keyArray[2][2].getText() == P1)  ||
         (keyArray[0][0].getText() == P1 && keyArray[1][1].getText() == P1 && keyArray[2][2].getText() == P1)  ||
         (keyArray[0][2].getText() == P1 && keyArray[1][1].getText() == P1 && keyArray[2][0].getText() == P1))

            {
               message.setText("Team " + P1 + " wins!");
               isWinner = true;
            }
         
      else if ((keyArray[0][0].getText() == P2 && keyArray[1][0].getText() == P2 && keyArray[2][0].getText() == P2) ||
               (keyArray[0][1].getText() == P2 && keyArray[1][1].getText() == P2 && keyArray[2][1].getText() == P2) ||
               (keyArray[0][2].getText() == P2 && keyArray[1][2].getText() == P2 && keyArray[2][2].getText() == P2) || 
               (keyArray[0][0].getText() == P2 && keyArray[0][1].getText() == P2 && keyArray[0][2].getText() == P2) ||
               (keyArray[1][0].getText() == P2 && keyArray[1][1].getText() == P2 && keyArray[1][2].getText() == P2) ||
               (keyArray[2][0].getText() == P2 && keyArray[2][1].getText() == P2 && keyArray[2][2].getText() == P2) ||
               (keyArray[0][0].getText() == P2 && keyArray[1][1].getText() == P2 && keyArray[2][2].getText() == P2) ||
               (keyArray[0][2].getText() == P2 && keyArray[1][1].getText() == P2 && keyArray[2][0].getText() == P2))

               {  
                  message.setText("Team " + P2 + " wins!");
                  isWinner = true;
               }
      else 
      {
         count++;
         if(count == 9)
         {
            message.setText("Tie");
            isWinner = true;
         }
      }
   }

   public static void main(String[] args)
   {
      launch(args);
   }

}


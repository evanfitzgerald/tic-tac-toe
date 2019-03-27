
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane; 
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.*;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class driver extends Application {
   private Button key, restart, X, O;
   private Button[][] keyArray;
   private boolean turn, isWinner;
   private Label message;
   private String P1, P2, empty;
   private int count, num1, num2, but1, but2;
   private Random rand;

   public void start(Stage primaryStage) 
   {
      // initial set-up
      empty = " ";
      P1 = "X";
      P2 = "O";
      rand = new Random(); 
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
      
      // setting up other buttons
      message = new Label();
      message.setFont(otherFont);
      pane3.add(message, 1, 2);

      restart = new Button("Restart");
      pane3.add(restart, 1, 1);
      restart.setOnAction(this::processRestart); 
      restart.setFont(otherFont);

      X = new Button(P1);
      pane2.add(X, 37, 1);
      X.setFont(otherFont);

      O = new Button(P2);
      pane2.add(O, 41, 1);
      O.setFont(otherFont);

      // setting up the menu
      MenuBar menuBar = new MenuBar(); 
      
      Menu gameMenu = new Menu("New Game");      
      MenuItem pvpMenuItem = new MenuItem("Player vs Player");
      pvpMenuItem.setOnAction(this::processPVP);
      gameMenu.getItems().addAll(pvpMenuItem);

      Menu colourMenu = new Menu("Set Colour");
      MenuItem setColourP1 = new MenuItem("P1");
      MenuItem setColourP2 = new MenuItem("P2");
      setColourP1.setOnAction(this::setNewColourP1);
      setColourP2.setOnAction(this::setNewColourP2);
      colourMenu.getItems().addAll(setColourP1, setColourP2);

      menuBar.getMenus().addAll(gameMenu, colourMenu);

      // initial values of the buttons
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

      //sets up rest of stage
      border.setTop(pane2);
      border.setCenter(pane);
      border.setBottom(pane3);
      VBox panes = new VBox(menuBar, border);
      Scene game = new Scene(panes, 800, 500);
      primaryStage.setTitle("Tic Tac Toe");
      primaryStage.setScene(game);
      primaryStage.show();
      startGame();
    }

   //what happens when a playbutton is hit
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
            P1butcolour(row, col);
            turn = !turn;
         }
         
         else if (!turn)
         {
            button.setText(P2);
            keyArray[row][col] = button;
            P2butcolour(row, col);
            turn = !turn;
         }
         winCheck();
      }
   }
              
   //starts the game
   public void startGame()
   {
      turn = true;
      isWinner = false;
      message.setText(empty);
      count = 0;
      for(int i = 0; i < 3; i++)
      {
         for(int j = 0; j < 3; j++)
         {
            keyArray[j][i].setText(empty);
            keyArray[j][i].setStyle("-fx-background-color: #808080");
         }
      }
   }

   //new pvp game
   public void processPVP(ActionEvent event)
   {
      startGame();
      setColourX(num1);
      setColourO(num2);
   }

   //restart game
   public void processRestart(ActionEvent event)
   {
      startGame();
   }

   //new colour for P1
   public void setNewColourP1(ActionEvent event)
   {
      setColourX(num1);
      processRestart(event);

   }
   //new colour for P2
   public void setNewColourP2(ActionEvent event)
   {
      setColourO(num2);
      processRestart(event);
   }

   //sets a player1 to have a new colour
   public void setColourX(int num1)
   {
      int temp = num1;
      while (temp == num1)
         num1 = rand.nextInt(6)+1;

        switch (num1) 
        {
            case 1:  X.setStyle("-fx-background-color: #FF0000"); 
                     break;
            case 2:  X.setStyle("-fx-background-color: #DDDD02"); 
                     break;
            case 3:  X.setStyle("-fx-background-color: #00FF00"); 
                     break;
            case 4:  X.setStyle("-fx-background-color: #00FFFF"); 
                     break;
            case 5:  X.setStyle("-fx-background-color: #A05BF2"); 
                     break;
            case 6:  X.setStyle("-fx-background-color: #E355E3");
                     break;
        } 
        but1 = num1;
        
   }

   //sets a player2 to have a new colour
   public void setColourO(int num2)
   {
      int temp = num2;
      while (temp == num2)
         num2 = rand.nextInt(6)+1; 

        switch (num2) 
        {
            case 1:  O.setStyle("-fx-background-color: #04FFAD"); 
                     break;
            case 2:  O.setStyle("-fx-background-color: #FFAE04"); 
                     break;
            case 3:  O.setStyle("-fx-background-color: #0FB7ED"); 
                     break;
            case 4:  O.setStyle("-fx-background-color: #5454FA"); 
                     break;
            case 5:  O.setStyle("-fx-background-color: #E5574C"); 
                     break;
            case 6:  O.setStyle("-fx-background-color: #F7F96A"); 
                     break;
        } 
        but2 = num2;
   }

   public void P1butcolour(int row, int col)
   {
      if (but1 == 1)
      keyArray[row][col].setStyle("-fx-background-color: #FF0000");
      else if (but1 == 2)
      keyArray[row][col].setStyle("-fx-background-color: #DDDD02");
      else if (but1 == 3)
      keyArray[row][col].setStyle("-fx-background-color: #00FF00");
      else if (but1 == 4)
      keyArray[row][col].setStyle("-fx-background-color: #00FFFF");
      else if (but1 == 5)
      keyArray[row][col].setStyle("-fx-background-color: #A05BF2");
      else 
      keyArray[row][col].setStyle("-fx-background-color: #E355E3");
   }

   public void P2butcolour(int row, int col)
   {
      if (but2 == 1)
      keyArray[row][col].setStyle("-fx-background-color: #04FFAD");
      else if (but2 == 2)
      keyArray[row][col].setStyle("-fx-background-color: #FFAE04");
      else if (but2 == 3)
      keyArray[row][col].setStyle("-fx-background-color: #0FB7ED");
      else if (but2 == 4)
      keyArray[row][col].setStyle("-fx-background-color: #5454FA");
      else if (but2 == 5)
      keyArray[row][col].setStyle("-fx-background-color: #E5574C");
      else 
      keyArray[row][col].setStyle("-fx-background-color: #F7F96A");
   }

   //checks board for a winner
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
               
               for (int i = 0; i < 3; i++)
               {
                  for (int j = 0; j < 3; j++)
                  {
                     if (but1 == 1)
                     keyArray[i][j].setStyle("-fx-background-color: #FF0000");
                     else if (but1 == 2)
                     keyArray[i][j].setStyle("-fx-background-color: #DDDD02");
                     else if (but1 == 3)
                     keyArray[i][j].setStyle("-fx-background-color: #00FF00");
                     else if (but1 == 4)
                     keyArray[i][j].setStyle("-fx-background-color: #00FFFF");
                     else if (but1 == 5)
                     keyArray[i][j].setStyle("-fx-background-color: #A05BF2");
                     else 
                     keyArray[i][j].setStyle("-fx-background-color: #E355E3");

                     RotateTransition rT = new RotateTransition(Duration.seconds(1.5), keyArray[i][j]);
                     rT.setFromAngle(0);
                     rT.setToAngle(360);
                     rT.setCycleCount(3);
                     rT.play();
                  }
               }
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
                  for (int i = 0; i < 3; i++)
                  {
                     for (int j = 0; j < 3; j++)
                     {
                        if (but2 == 1)
                        keyArray[i][j].setStyle("-fx-background-color: #04FFAD");
                        else if (but2 == 2)
                        keyArray[i][j].setStyle("-fx-background-color: #FFAE04");
                        else if (but2 == 3)
                        keyArray[i][j].setStyle("-fx-background-color: #0FB7ED");
                        else if (but2 == 4)
                        keyArray[i][j].setStyle("-fx-background-color: #5454FA");
                        else if (but2 == 5)
                        keyArray[i][j].setStyle("-fx-background-color: #E5574C");
                        else 
                        keyArray[i][j].setStyle("-fx-background-color: #F7F96A");

                        RotateTransition rT = new RotateTransition(Duration.seconds(1.5), keyArray[i][j]);
                        rT.setFromAngle(0);
                        rT.setToAngle(360);
                        rT.setCycleCount(3);
                        rT.play();
                     }
                  }
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
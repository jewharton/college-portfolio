/* rockPaperScissors.java
Jeremy Wharton
Programming Fundamentals II
COSC 1337 2801
1/27/2018

Declare the package rockPaperScissors. (5pts)

For this review activity write a program that simulates Rock, Paper, Scissors. Rules of the game are as follows:

* Rock smashes Scissors 
* Paper covers Rock
* Scissors cuts Paper

You are to write a program that plays the game against the computer (10pts).  

Allow the user to input their name (5pts).

The computer should choose an object randomly and your program should prompt the user to enter his/her choice. (5pts)  OR with a *GUI (10pts)

You are at liberty to have the user enter a number or the string.

Use a decision structure of your choice to determine a winner (15pts).

Allow the user to play a set amount of times (5pts) OR allow the user to determine the number of times they will play (15pts).

The program should then declare the winner, whether user or CPU and how the winner won the game. (5pts) OR with GUI (10pts)

Your program should keep track of the number of wins and losses for each and also the number of ties. (15pts) OR display the results GUI (20pts)

Output their name when giving them the summary of their games (5pts) OR GUI (15pts).

Anything unique after completing all the requirements or a more creative approach (5pts).

*GUI - Graphical User Interface, any one of your choice (JOptionPane)

Simplest Input and Output (you are encouraged to make it more interesting)

Sample Output: Please enter your name.

Sample Input: Marquez

Sample Output: Please choose, Rock, Paper, or Scissors (Explain the game to them here)

Sample Input: Either a 1, 2 or 3  OR the words "Rock", "Paper", "Scissors"

Sample Output: The computer chose Rock and You chose Paper, you win.

Sample Output: Would you like to play again?

Sample Output: Marquez, You won 1 of 1 games.
*/

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

//A class based on the Button class that has a GameValue property
class GameButton extends Button {
	//Specifies whether rock (0), paper (1) or scissors (2)
	int gameValue;
	
	//Constructor - calls the Button constructor with the text you passed
	GameButton(String text) {
		super(text);
	}
}

//Main class
public class rockPaperScissors extends Application {
	//The main stage
	Stage mainStage;
	
	//Width and height of the game window
	int gameWidth = 600;
	int gameHeight = 320;
	
	//Player's name
	String playerName;
	
	//How many rounds have been played
	int timesPlayed;
	
	//How many rounds to play
	int timesToPlay;
	
	//Number of wins and losses
	int wins = 0;
	int losses = 0;
	
	/* MakeTitle creates a large label to be placed at the
	 * top of the screen and returns the label */
	public Label MakeTitle(StackPane pane) {
		Label title = new Label("Rock Paper Scissors");
		title.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 32));
		title.setTranslateY(-gameHeight/2 + 30);
		pane.getChildren().add(title);
		return title;
	}
	
	/* MakeSubtitle creates a medium-sized label to be placed at the
	 * top of the screen and returns the label */
	public Label MakeSubtitle(StackPane pane, String text) {
		Label subtitle = new Label(text);
		subtitle.setFont(Font.font("Segoe UI",16));
		subtitle.setTranslateY(-gameHeight/2 + 60);
		pane.getChildren().add(subtitle);
		return subtitle;
	}
	
	//EnterName is the menu that appears when you start the game
	public void EnterName() {
		//Create the pane
		StackPane pane = new StackPane();
		
		//Make title and subtitle
		MakeTitle(pane);
		MakeSubtitle(pane, "Enter your name to begin.");
		
		//Name field
		TextField name = new TextField(System.getProperty("user.name"));
		name.setFont(Font.font("Segoe UI",32));
		name.setMaxWidth(300);
		name.setAlignment(Pos.BASELINE_CENTER);
		pane.getChildren().add(name);
		
		//Enter button
		Button enterButton = new Button("Enter");
		enterButton.setPadding(new Insets(10, 15, 10, 15));
		enterButton.setFont(Font.font("Segoe UI",18));
		enterButton.setTranslateY(gameHeight/2 - 60);
		pane.getChildren().add(enterButton);
		
		//On enterButton click
		enterButton.setOnAction((ActionEvent e) -> {
			//Set the player name
		    playerName = name.getText();
		    if (name.getLength() == 0) {playerName = "You";}
		    
		    //Switch to next menu
		    PlayTimesSelector();
		    
		    return;
		});
		
		//Display it
		Scene scene = new Scene(pane, gameWidth, gameHeight);
		mainStage.setScene(scene);
		mainStage.show();
	}
	
	/* PlayTimesSelector is the menu that allows you
	 * to choose how many times you play */
	public void PlayTimesSelector() {
		//Create the pane
		StackPane pane = new StackPane();
		
		//Make title and subtitle
		MakeTitle(pane);
		MakeSubtitle(pane, "How many rounds would you like to play?");
		
		//Enter number of times to play
		TextField timesPlaying = new TextField("3");
		timesPlaying.setFont(Font.font("Segoe UI",32));
		timesPlaying.setMaxWidth(100);
		timesPlaying.setAlignment(Pos.BASELINE_CENTER);
		pane.getChildren().add(timesPlaying);
		
		//Enter button
		Button enterButton = new Button("Enter");
		enterButton.setPadding(new Insets(10, 15, 10, 15));
		enterButton.setFont(Font.font("Segoe UI",18));
		enterButton.setTranslateY(gameHeight/2 - 60);
		pane.getChildren().add(enterButton);
		
		//On enterButton click
		enterButton.setOnAction((ActionEvent e) -> {
			//Only allow numbers
		    if (!timesPlaying.getText().matches("[0-9]")) {
		    	//Show an error
		    	Alert alert = new Alert(AlertType.ERROR);
		    	alert.setTitle("Error");
		    	alert.setHeaderText(null);
		    	alert.setContentText("Input must be numerical.");
		    	alert.showAndWait();
		    } else {
		    	//Set the number of times left to play
		    	timesToPlay = Integer.parseInt(timesPlaying.getText());
		    	
		    	//Play the game
		    	PlayGame();
		    	
		    	return;
		    }
		});
		
		//Display it
		Scene scene = new Scene(pane, gameWidth, gameHeight);
		mainStage.setScene(scene);
		mainStage.show();
	}
	
	/* PlayGame is the method that allows you to
	 * choose rock, paper, or scissors */
	public void PlayGame() {
		//Show the results if you're finished playing
		if (timesPlayed == timesToPlay) {
			ShowResults();
			return;
		}
		
		//Create the pane, make the title, and make the subtitle
		StackPane pane = new StackPane();
		MakeTitle(pane);
		MakeSubtitle(pane, "Round " + (timesPlayed+1) + "/" + timesToPlay);
		
		//Make the "Rock", "Paper", and "Scissors" buttons
		for (int buttonNumber=0; buttonNumber<3; buttonNumber++) {
			GameButton thisButton = new GameButton(buttonNumber==0 ? "  Rock  " : buttonNumber==1 ? " Paper " : "Scissors");
			thisButton.setPadding(new Insets(10, 20, 10, 20));
			thisButton.setFont(Font.font("Segoe UI",24));
			thisButton.setTranslateX((buttonNumber-1)*150);
			thisButton.gameValue = buttonNumber;
			
			//On button click
			thisButton.setOnAction((ActionEvent e) -> {
				//Get the button that fired the event
				GameButton button = ((GameButton)e.getSource());
				
				//Get your choice
				int yourChoice = button.gameValue;
				
				//Create a new pane, make the title, and make the subtitle
				StackPane resultsPane = new StackPane();
				MakeTitle(resultsPane);
				MakeSubtitle(resultsPane, "Round " + (timesPlayed+1) + "/" + timesToPlay);
				
				//Get the CPU's choice
				int cpuChoice = (int)(Math.random()*2 + 0.1);
				
				//Determine if you won or not ------------
				//1 = Won, 0 = Tie, -1 = Lost
				int result = 0;
				
				switch (button.gameValue) {
					//You chose rock
					case 0:
						switch (cpuChoice) {
							//CPU chose rock
							case 0:
								result = 0; break;
							//CPU chose paper
							case 1:
								result = -1; break;
							//CPU chose scissors
							case 2:
								result = 1; break;
						}
						break;
					
					//You chose paper
					case 1:
						switch (cpuChoice) {
							//CPU chose rock
							case 0:
								result = 1; break;
							//CPU chose paper
							case 1:
								result = 0; break;
							//CPU chose scissors
							case 2:
								result = -1; break;
						}
						break;
					
					//You chose scissors
					case 2:
						switch (cpuChoice) {
							//CPU chose rock
							case 0:
								result = -1; break;
							//CPU chose paper
							case 1:
								result = 1; break;
							//CPU chose scissors
							case 2:
								result = 0; break;
						}
						break;
				}
				// ---------------------------------
				
				//Show your win/lost/tied status
				Label status = new Label(playerName + " " + (result == 1 ? "won!" : result == -1 ? "lost." : "tied."));
				status.setFont(Font.font("Segoe UI", FontWeight.BOLD, 36));
				status.setTranslateY(-42);
				resultsPane.getChildren().add(status);
				
				//Show what you chose
				Label youChose = new Label(playerName + " chose " + (yourChoice==0 ? "Rock." : yourChoice==1 ? "Paper." : "Scissors."));
				youChose.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 20));
				youChose.setTranslateY(15);
				resultsPane.getChildren().add(youChose);
				
				//Show what the CPU chose
				Label cpuChose = new Label("CPU chose " + (cpuChoice==0 ? "Rock." : cpuChoice==1 ? "Paper." : "Scissors."));
				cpuChose.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 20));
				cpuChose.setTranslateY(50);
				resultsPane.getChildren().add(cpuChose);
				
				//Increment timesPlayed
				timesPlayed++;
				
				//Calculate wins or losses
				if (result < 0)
					losses++;
				else if (result > 0)
					wins++;
				
				//Make a "Next" button to play the next round
				Button nextButton = new Button("Next");
				nextButton.setPadding(new Insets(10, 15, 10, 15));
				nextButton.setFont(Font.font("Segoe UI",18));
				nextButton.setTranslateY(gameHeight/2 - 60);
				resultsPane.getChildren().add(nextButton);
				
				//on nextButton click
				nextButton.setOnAction((ActionEvent event) -> {
					PlayGame();
				});
				
				//Display it
				Scene thisScene = new Scene(resultsPane, gameWidth, gameHeight);
				mainStage.setScene(thisScene);
				mainStage.show();
			});
			
			pane.getChildren().add(thisButton);
		}
		
		//Display it
		Scene scene = new Scene(pane, gameWidth, gameHeight);
		mainStage.setScene(scene);
		mainStage.show();
	}
	
	/* ShowResults is the menu that is responsible
	 * for showing the game results */
	public void ShowResults() {
		//Create the pane, make the title, and make the subtitle
		StackPane pane = new StackPane();
		MakeTitle(pane);
		MakeSubtitle(pane, "Game Summary");
		
		//Display the final winner
		String winnerText = (wins > losses) ? (playerName + " is the final winner.") :
							(losses > wins) ? ("CPU is the final winner") :
											  ("The game was tied.");
		Label finalWinner = new Label(winnerText);
		finalWinner.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
		finalWinner.setTranslateY(-60);
		pane.getChildren().add(finalWinner);
		
		//Make a list of results
		for (int listLine = 0; listLine < 4; listLine++) {
			//Text in the label
			String text = (
				listLine == 0 ? ("Wins: " + wins) :
				listLine == 1 ? ("Losses: " + losses) :
				listLine == 2 ? ("Ties: " + (timesPlayed - wins - losses)) :
								("Total rounds played: " + timesPlayed)
			);
			
			//Make the label
			Label listLabel = new Label(text);
			listLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 18));
			listLabel.setTranslateY(45 - (listLine*24));
			pane.getChildren().add(listLabel);
		}
		
		//Make an exit button
		Button exitButton = new Button("  Exit  ");
		exitButton.setPadding(new Insets(10, 15, 10, 15));
		exitButton.setFont(Font.font("Segoe UI",18));
		exitButton.setTranslateY(gameHeight/2 - 60);
		exitButton.setTranslateX(-60);
		pane.getChildren().add(exitButton);
		
		//on exitButton click
		exitButton.setOnAction((ActionEvent event) -> {
			System.exit(0);
		});
		
		//Make a restart button
		Button restartButton = new Button("Restart");
		restartButton.setPadding(new Insets(10, 15, 10, 15));
		restartButton.setFont(Font.font("Segoe UI",18));
		restartButton.setTranslateY(gameHeight/2 - 60);
		restartButton.setTranslateX(60);
		pane.getChildren().add(restartButton);
		
		//on restartButton click
		restartButton.setOnAction((ActionEvent event) -> {
			wins = 0;
			losses = 0;
			timesPlayed = 0;
			EnterName();
		});
		
		//Display it
		Scene scene = new Scene(pane, gameWidth, gameHeight);
		mainStage.setScene(scene);
		mainStage.show();
	}
	
	//Main JavaFX method
	@Override
	public void start(Stage primaryStage) {
		//Set the main stage
		mainStage = primaryStage;
		
		//Set the main stage title
		mainStage.setTitle("Rock, Paper, Scissors!");
		
		//Do not let the stage be resized
		mainStage.setResizable(false);
		
		//Make a dummy pane first; this is required
		//to apply the gameWidth and gameHeight to primaryStage
		StackPane pane = new StackPane();
		
		//Make a scene and display it
		Scene scene = new Scene(pane, gameWidth, gameHeight);
		mainStage.setScene(scene);
		mainStage.show();
		
		//Show the menu to enter your name
		EnterName();
	}
	
	//Main method
	public static void main(String[] args) {
		Application.launch(args);
	}
}
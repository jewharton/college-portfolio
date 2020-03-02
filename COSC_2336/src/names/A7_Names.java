/*A7_Names.java
Jeremy Wharton
Programming Fundamentals III
COSC 2336 2801
11/18/2018
Your task in this assignment is to implement three sorting methods of your choice to sort the women's names in the file names.dat.
The binary file has a list of names that your program is to sort and display in ascending alphabetical order.
You are to also implement the binary search algorithm and allow the user to search for a particular name in the list.

The file accompanying the problem is a binary file.
Make sure you create a method to open the file correctly.
You can use a sample names.txt to get you started but make sure you are able to open a binary file before you turn it in.
I will be grading the assignments using a binary file. (15 pts)
You are to read the contents of the binary file into either a string array or an ArrayList.
If you are using an array, you will have to determine the size of the list to create the correct array. (10 pts)
Your program should be able to sort out the names in the file using three algorithms.
For example: make a method to perform selection sort and another method to perform insertion sort and bubble sort.
You choose which sorting algorithm will be used for the binary search but I need to see three sorting algorithms implemented in your program. (30 pts)
Your program should then allow users to input a name and get the index of where the name is found in the file.
For example: if the user types in Mary into the search dialog box, then they should get a message indicating "The name Mary was found at index 1". (15 pts)
Use binary search algorithm to conduct the search. (15 pts)
 Use of dialog boxes is encouraged for a nicer looking user interface (UI). (10 pts)

The package name should be names.   (5 pts) 
 */

package names;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class A7_Names extends Application {
	Stage mainStage;								//The main stage
	StackPane mainPane;								//The main pane
	ArrayList<String> names = new ArrayList<>();	//ArrayList containing names
	int windowWidth = 600;							//Window width and height
	int windowHeight = 320;
	File dataFile;									//File with names data
	
	/** Repeatedly move the minimum value to the lowest end */
	public static ArrayList<String> SelectionSort(ArrayList<String> array){
		ArrayList<String> lower = new ArrayList<>();	//Lower end
		int minIndex = 0;								//Index of the minimum value
		if (array.size()<2) return array;				//If it's too small to sort, return it
		
		for (int i=0; i<array.size(); i++) {			//Obtain index of minimum value
			String item = array.get(i);
			if (item.compareTo(array.get(minIndex)) < 0)
				minIndex = i;
		}
		
		lower.add(array.get(minIndex));			//Add minimum value to lower end
		array.remove(minIndex);					//Remove minimum value from original array
		lower.addAll(SelectionSort(array));		//Add lower end to sorted upper end
		return lower;
	}
	
	/** Repeatedly move values to their proper spots backwards in the list */
	public static ArrayList<String> InsertionSort(ArrayList<String> array){
		if (array.size()<2) return array;			//If it's too small to sort, return it
		
		for (int i=1; i<array.size(); i++) {		//Iterate through all values in the array
			String item = array.get(i);
			int desiredIndex = i;					//Index in which item should be placed
			
			for (int i2=i; i2>=0; i2--)				//Obtain desired index
				if (item.compareTo(array.get(i2)) < 0)
					desiredIndex = i2;
			
			if (desiredIndex != i) {				//Move the item over to the desired index
				array.remove(i);
				array.add(desiredIndex, item);
			}
		}
		return array;
	}
	
	/** Repeatedly compare once value to the next and swap their positions when needed */
	public static ArrayList<String> BubbleSort(ArrayList<String> array){
		if (array.size()<2) return array;			//If it's too small to sort, return it
		
		for (int i=0; i<array.size()-1; i++) {		//Iterate through all values in the array
			String item = array.get(i);
			if (item.compareTo(array.get(i+1)) > 0) {	//If the next value is smaller, swap positions
				array.remove(i);
				array.add(i+1, item);
			}
		}
		return array;
	}
	
	/** Perform a binary search */
	public static int BinarySearch(String str, ArrayList<String> array, boolean caseSensitive) {
		if (array.size() == 1)
			if ((array.get(0).compareTo(str) != 0) &&
				(caseSensitive==false && array.get(0).toUpperCase().compareTo(str.toUpperCase()) != 0))
				return -1000000;
			else
				return 0;
		
		int mid = array.size()/2;
		int index = 0;
		if ((str.compareTo(array.get(mid)) < 0) ||
			(caseSensitive==false && str.toUpperCase().compareTo(array.get(mid).toUpperCase()) != 0)) {	//If this value is less than middle value
			ArrayList<String> halfArray = new ArrayList<>();
			for (int i=0; i<mid; i++)
				halfArray.add(array.get(i));
			
			return BinarySearch(str, halfArray, caseSensitive);
		} else {									//If this value is greater than middle value
			ArrayList<String> halfArray = new ArrayList<>();
			for (int i=mid; i<array.size(); i++)
				halfArray.add(array.get(i));
			
			return mid + BinarySearch(str, halfArray, caseSensitive);
		}
	}
	
	/** Parse a file of names */
	public void ParseFile() throws IOException {
		//Clear the names
		names.clear();
		
		//Stringify the file
		String fileString = new String(Files.readAllBytes(dataFile.toPath()));
		
		//Turn everything that isn't a letter or space into a space
		fileString = fileString.replaceAll("[^A-Za-z0-9\\s]", " ");
		ArrayList<String> stringArray = new ArrayList<String>(Arrays.asList(fileString.split("\\s+")));
		
		//Add all non-empty items to the names array
		for (int i=0; i<stringArray.size(); i++) {
			String item = stringArray.get(i);
			if (!item.isEmpty())
				names.add(item);
		}
								
		names = SelectionSort(names);		//Sort the array
	}
	
	/** Set up the stage. Create UI elements. */
	public void SetupStage() {
		//Create the title
		Label title = new Label("Name Index Locator");
		title.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 28));
		title.setTranslateY(-windowHeight/2 + 20);
		mainPane.getChildren().add(title);
		
		//Result of search
		Label result = new Label();
		result.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 28));
		mainPane.getChildren().add(result);
		
		//Enter Name box
		TextField nameBox = new TextField();
		nameBox.setFont(Font.font("Segoe UI",28));
		nameBox.setMaxWidth(200);
		nameBox.setAlignment(Pos.BASELINE_CENTER);
		nameBox.setTranslateY(-windowHeight/2 + 80);
		nameBox.setTranslateX(-70);
		nameBox.setDisable(true);
		nameBox.setPromptText("Enter Name");
		mainPane.getChildren().add(nameBox);
		
		//Enter button for the Enter Name box
		Button enterButton = new Button("Enter");
		enterButton.setFont(Font.font("Segoe UI",28));
		enterButton.setTranslateY(-windowHeight/2 + 80);
		enterButton.setTranslateX(100);
		enterButton.setDisable(true);
		mainPane.getChildren().add(enterButton);
		
		//On click for above button
		enterButton.setOnAction((ActionEvent e) -> {
			if (nameBox.getText().isEmpty()) return;
			int index = BinarySearch(nameBox.getText(), names, false);
			if (index < 0)
				result.setText("The name '"+nameBox.getText()+"' was not found.");
			else
				result.setText("The name '"+nameBox.getText()+"' is at index "+index+".");
		    return;
		});
		
		//Filepath box
		TextField pathBox = new TextField();
		pathBox.setFont(Font.font("Segoe UI",14));
		pathBox.setMaxWidth(300);
		pathBox.setAlignment(Pos.BASELINE_CENTER);
		pathBox.setTranslateY(windowHeight/2 - 20);
		pathBox.setTranslateX(-50);
		pathBox.setEditable(false);
		mainPane.getChildren().add(pathBox);
		
		//Choose File button
		Button chooseFile = new Button("Choose File");
		chooseFile.setFont(Font.font("Segoe UI",14));
		chooseFile.setTranslateY(windowHeight/2 - 20);
		chooseFile.setTranslateX(150);
		mainPane.getChildren().add(chooseFile);
		
		//On Choose File click, open a file chooser
		chooseFile.setOnAction((ActionEvent e) -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));	//Default directory
			File file = fileChooser.showOpenDialog(mainStage);							//Show chooser
			if (file == null) return;
			dataFile = file;
			try {
				ParseFile();
			} catch (Exception e1) {
				e1.printStackTrace();
			}									//Parse the file
			pathBox.setText(file.getAbsolutePath());		//Set the pathBox text
			enterButton.setDisable(false);					//Enable the Enter button
			nameBox.setDisable(false);						//Enable the Name field
		    return;
		});
	}
	
	//Main JavaFX method
	@Override
	public void start(Stage primaryStage) {
		mainStage = primaryStage;
		mainStage.setTitle("Name Index Locator");
		mainStage.setResizable(false);
		mainPane = new StackPane();		//Make a pane to apply width and height to
		
		//Make a scene and display it
		Scene scene = new Scene(mainPane, windowWidth, windowHeight);
		mainStage.setScene(scene);
		mainStage.show();
		
		SetupStage();	//Set up the main stage
	}
	
	//Main method
	public static void main(String[] args) {
		Application.launch(args);
	}
}

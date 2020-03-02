/*A5_StacksAndQueues.java
Jeremy Wharton
Programming Fundamentals III
COSC 2336 2801
10/28/2018
You are to write a program that gives its users three basic options: reverse, convert, and compare.
The program should ask the user to input the file name for the file in question (including the extension, .txt). (20pts)
If the user selects reverse the program should open the text file and read its content into a stack of characters (10pts).
The program should then pop the characters from the top of the stack and save them in a second text file(10pts).
The order in the second file should be the reverse of the order in the first file.  
If the user selects convert, the program should open a text file and read its content into as queue of characters(10pts).
The program should then dequeue the characters, convert it to uppercase, and store it in a second file (10pts)
If the user selects compare, the program ask the user for two files and should then open the two text files and read their contents into two separate queues (10pts).
The program should determine if the files are identical by comparing characters in the queues(10pts)
When two nonidentical characters are encountered, the program should display a message indicating that the files are not the same(5pts)
Otherwise let the user know that the files are identical(5pts).
You should use your own test files to make sure the program performs as specified but you do not have to include them in your submission (5pts).
You are at liberty to ask the user for the file name to be examined.
Be sure to include the package declaration [package stacksAndQueues;] (5pts)
 */

package stacksAndQueues;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

//Stack class
class Stack<T> extends ArrayList<T> {
	/** Pop a value off the stack.
	 * @return The popped value. */
	public T pop() throws IndexOutOfBoundsException {
		//Throw an error if the list is empty
		if (this.isEmpty())
			throw new IndexOutOfBoundsException();
		T elem = this.get(this.size()-1);
		this.remove(this.size()-1);
		return elem;
	}
}


//Queue class
class Queue<T> extends ArrayList<T> {
	/** Pop a value off the queue.
	 * @return The popped value. */
	public T pop() throws IndexOutOfBoundsException {
		//Throw an error if the list is empty
		if (this.isEmpty())
			throw new IndexOutOfBoundsException();
		T elem = this.get(0);
		this.remove(0);
		return elem;
	}
}


public class A5_StacksAndQueues {
	static String sourceName;		//Original file
	static String destName;			//Destination file
	
	/** Reverse the file. */
	private static void reverse(Scanner input) throws IOException {
		//Prompt user for file path
		System.out.println("Enter destination file path (including file name): ");
		destName = input.nextLine();
		
		File destFile = new File(destName);
		try {
			if (destFile.isFile())
				destFile.delete();				//Delete the dest file if it exists
			destFile.createNewFile();			//Re-make it
		} catch (IOException error) {
			System.err.println("IOException: "+error.getMessage());
			System.exit(0);
		}
			
		//Put all characters from source file into stack
		Stack<String> sourceStack = new Stack<String>();
		String fileString = new String(Files.readAllBytes(Paths.get(sourceName)));	//Stringify the file
		fileString = fileString.replace("\r\n", "\n\r");		//Reverse newlines so they are proper when put into the file
		
		for (int i=0; i<fileString.length(); i++) {			//Add every character to the sourceStack
			sourceStack.add(fileString.substring(i, i+1));
		}
		
		//Pop all characters from stack into destination
		PrintWriter destWriter = new PrintWriter(destFile);
		while (sourceStack.size()>0)
			destWriter.print(sourceStack.pop());
		destWriter.close();
		
		System.out.println("File has been reversed.");
	}
	
	/** Convert the file to uppercase. */
	private static void convert(Scanner input) throws IOException {
		//Prompt user for file path
		System.out.println("Enter destination file path (including file name): ");
		destName = input.nextLine();
		
		File destFile = new File(destName);
		try {
			if (destFile.isFile())
				destFile.delete();				//Delete the dest file if it exists
			destFile.createNewFile();			//Re-make it
		} catch (IOException error) {
			System.err.println("IOException: "+error.getMessage());
			System.exit(0);
		}
		
		//Put all characters from source file into queue
		Queue<String> sourceQueue = new Queue<String>();
		String fileString = new String(Files.readAllBytes(Paths.get(sourceName)));	//Stringify the file
		
		for (int i=0; i<fileString.length(); i++)			//Add every character to the sourceQueue
			sourceQueue.add(fileString.substring(i, i+1));
		
		//Pop and uppercase all characters from queue into destination
		PrintWriter destWriter = new PrintWriter(destFile);
		while (sourceQueue.size()>0)
			destWriter.print(sourceQueue.pop().toUpperCase());
		destWriter.close();
		
		System.out.println("File has been converted.");
	}
	
	/** Compare two files. */
	private static void compare(Scanner input) throws IOException {
		//Prompt user for file path
		System.out.println("Enter second file path (including file name): ");
		destName = input.nextLine();
		
		//Check if second file exists
		File secondFile = new File(destName);
		if (!secondFile.exists()) {
			System.out.println(destName+" does not exist.");
			System.exit(0);
		}
		if (!secondFile.isFile()) {
			System.out.println(destName+" is not a file.");
			System.exit(0);
		}
		
		//Put all characters from first file into first queue
		Queue<String> firstQueue = new Queue<String>();
		String firstString = new String(Files.readAllBytes(Paths.get(sourceName)));	//Stringify the file
		for (int i=0; i<firstString.length(); i++)			//Add every character to the firstQueue
			firstQueue.add(firstString.substring(i, i+1));
		
		//Put all characters from second file into second queue
		Queue<String> secondQueue = new Queue<String>();
		String secondString = new String(Files.readAllBytes(Paths.get(destName)));	//Stringify the file
		for (int i=0; i<secondString.length(); i++)			//Add every character to the firstQueue
			secondQueue.add(secondString.substring(i, i+1));
		
		//Compare the queues
		if (firstQueue.size() != secondQueue.size()) {
			System.out.println("Files are not equal.");
		} else {
			for (int i=0; i<firstQueue.size(); i++) {
				if (!firstQueue.pop().equals(secondQueue.pop())) {
					System.out.println("Files are not equal.");
					return;
				}
			}
			System.out.println("Files are equal.");
		}
	}
	
	public static void main(String[] args) throws IOException {
		//Initialize input
		Scanner input = new Scanner(System.in);
		
		//Prompt user for file path
		System.out.println("Enter source file path (including file name): ");
		sourceName = input.nextLine();
		
		//Check if file exists
		File namesFile = new File(sourceName);
		if (!namesFile.exists()) {
			System.out.println(sourceName+" does not exist.");
			System.exit(0);
		}
		if (!namesFile.isFile()) {
			System.out.println(sourceName+" is not a file.");
			System.exit(0);
		}
		
		//Prompt the user
		System.out.println("Choose (1) Reverse, (2) Convert, (3) Compare:");
		int choice = input.nextInt();
		input.nextLine();				//Flush the trailing newline
		
		//Execute the choice
		switch(choice) {
			case 1: reverse(input);break;
			case 2: convert(input);break;
			case 3: compare(input);break;
		}
	}
}

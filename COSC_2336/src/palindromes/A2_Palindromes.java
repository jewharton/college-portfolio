/*A2_Palindromes.java
Jeremy Wharton
Programming Fundamentals III
COSC 2336 2801
9/16/2018

A palindrome is a group of characters that read the same forward or backwards.  The goal of this assignment is to write a recursive method that detects whether the String parameter it receives is a palindrome or not.
The method should be a boolean-returning method called isPalindrome. (30 pts)
This method should be demonstrated in a main method. (30 pts)
Input should be user input through console or GUI (graphical user interface using JavaFX or JOptionPane. (30 pts).
The program package should be called palindromes. (10 pts)
*/

package palindromes;

import java.util.Scanner;

public class A2_Palindromes {
	/** isPalindrome - determines whether a string is a palindrome.
	@param str - The string to test.
	@return This method returns true if str contains a palindrome; else, returns false.
	*/
	public static boolean isPalindrome(String str) {
		str = str.toUpperCase().replaceAll(" ", "");	//Turn uppercase and replace all spaces
		return (
			str.length()<=1 ? true :						//True if str length is <= 1
			str.charAt(0)==str.charAt(str.length()-1) ?		//Else if beginning and ending char match
			isPalindrome(str.substring(1, str.length()-1)) : false	//Check if trimmed str is palindrome;
		);
	}
	
	/** checkLoop - Constantly prompts the user to input a string to check.
	@param input - Scanner
	*/
	public static void checkLoop(Scanner input) {
		//String to check
		System.out.print("Enter text to test palindromeness: ");
		String str = input.nextLine();
		
		//Output result
		System.out.println(str+" is "+(isPalindrome(str) ? "" : "not ")+"a palindrome.");
		System.out.println("---------------------");
		checkLoop(input);
	}
	
	public static void main(String[] args) {
		//Initialize the input
		Scanner input = new Scanner(System.in);
		checkLoop(input);
	}
}

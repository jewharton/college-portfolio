/* Strong.java
Jeremy Wharton
Programming Fundamentals II
COSC 1337 2801
4/21/2018

Program Name: Strong.java          Input File: strong.dat
The technology department requires that all teacher passwords be “strong” passwords. For a password to be considered “strong”, it must:
Contain at least one uppercase letter or a special character
Special characters are: ! @ # $ % ^ & * ( ) _ + =
Contain at least one digit: 0 1 2 3 4 5 6 7 8 9
Must be at least 8 characters long
Can contain only upper case and lower case alphabetic characters, special characters as defined above, and digits
It is your job to write a program to see if a given password is a “strong” password.
Input
The first line of input will contain a single integer n that indicates the number of passwords to follow. Each of the following n lines will contain a single string of characters with no spaces that represents one password.
Output
For each password, on a single line print “STRONG” if the password meets the criteria listed above or print “INVALID” if it does not.
Example Input File
4
MARY_HADaLITTLElamb
MARY_HAD1LITTLElamb
{RogerRabbit2}
R0gerR@bbit
Example Output to Screen
INVALID
STRONG
INVALID
STRONG
*/

import java.io.*;
import java.util.*;

public class Strong {
	//Check if a string is a valid password
	public static boolean isValidPassword(String pass) {
		if (
			pass.length() < 8 ||	//Must be longer than 8 characters
			!pass.replaceAll("[A-Za-z0-9!@#$%^&*()_+=]", "").equals("") ||	//Only allowed characters
			pass.replaceAll("[0-9]", "").equals(pass) ||	//Must have at least 1 number
			(	//Must contain at least 1 uppercase or 1 special character
				pass.replaceAll("[A-Z]", "").equals(pass) &&
				pass.replaceAll("[!@#$%^&*()_+=]", "").equals(pass)
			)
		)
			return false;
		return true;
	}
	
	//Main method
	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.out.println("Usage: java Strong passwordList");
			System.exit(1);
		}
		
		//Check if password file exists
		File passFile = new File(args[0]);
		if (!passFile.exists()) {
			System.out.println("Password list "+args[0]+" does not exist.");
			System.exit(2);
		}
		
		//Initialize input
		try (Scanner input = new Scanner(passFile)) {
			//Number of passwords left
			int passwordsLeft = -1;
			
			while (true) {
				if (passwordsLeft == 0) break;
				//If there are no more lines
				if (!input.hasNext()) {
					//If passwordsLeft hasn't been initiated yet
					if (passwordsLeft == -1)
						System.out.println("No passwords found.");
					//If there are still passwords remaining
					else if (passwordsLeft != 0)
						System.out.println(passwordsLeft+" passwords missing.");
					System.exit(3);
				}
				String s1 = input.nextLine();
				//Initialize passwordsLeft
				if (passwordsLeft == -1) {
					if (s1.charAt(0) < '0' || s1.charAt(0) > '9') {
						System.out.print("First line of "+args[0]+" must be a digit signifying the number of passwords.");
						System.exit(4);
					}
					passwordsLeft = s1.charAt(0)-'0';
					if (!input.hasNext()) {
						System.out.println("No passwords found ("+passwordsLeft+" expected).");
						System.exit(5);
					}
					continue;
				}
				//Show if the password is strong or not
				System.out.println(isValidPassword(s1) ? "STRONG" : "INVALID");
				passwordsLeft--;
			}
		}
	}

}

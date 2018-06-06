/* Ch13_Telephone.java
Jeremy Wharton
Programming Fundamentals II
COSC 1337 2801
4/8/2018

Most of you have seen commercials for various companies where the phone numbers are exactly, numbers.  Stanley Steamers (1-800-STEAMER) or AT&T (1-800-CALL-ATT) for example.
In this simple assignment your job is to help the less adept phone users to figure out what the actual numbers are that they should be dialing.
I have included a picture of the phone keypad. 
Inform the user of the program to input the numbers in the format xxx-xxx-xxxx.  Your code should output the number either using (xxx) xxx-xxxx or xxx-xxx-xxxx. However, the code output should be a completely numeric conversion of the user's lettered entry.
My preference is the use of objects for this program.
*/

import java.util.Scanner;

//Telephone number class
class PhoneNumber {
	//Raw unformatted number
	private String number;
	
	//No-arg constructor
	PhoneNumber() {
		this("5555555555");
	}
	
	//String constructor
	PhoneNumber(String value) {
		setNumber(value);
	}
	
	//Set the phone number
	public void setNumber(String value) {
		//Converted number
		String tempString = "";
		
		//Remove all non-alphanumeric characters and capitalize
		value = value.replaceAll("[^A-Za-z0-9]", "").toUpperCase();
		
		/*Convert string to fully numeric string
		 * by iterating through the string and
		 * converting alphabetical characters
		 */
		for (int i=0; i<value.length(); i++) {
			char c = value.charAt(i);
			
			//If character at this position is alphanumeric
			if (c >= 'A' && c <= 'Z') {
				/*Keypad array - contains first and
				 * last letter of each key.
				 */
				char[][] keymap = {
					{'A','C'}, {'D','F'}, {'G','I'},
					{'J','L'}, {'M','O'}, {'P','S'},
					{'T','V'}, {'W','Z'}
				};
				
				//Identify the keypad number based on the current character
				for (int x=0; x<keymap.length; x++) {
					if (c >= keymap[x][0] && c <= keymap[x][1]) {
						tempString += x+2;
						break;
					}
				}
			} else {
				tempString += c;
			}
		}
		number = tempString;
	}
	
	//Get the unformatted number
	public String getNumber() {
		return number;
	}
	
	//Get the formatted number
	@Override
	public String toString() {
		//Formatted string
		String tempString = "";
		
		/* Offset the dashes if the number is long enough
		 * and starts with a 1.
		 * numdashes is the number of dashes to insert.
		 */
		int offset = (number.length()>10 && number.charAt(0) == '1') ? 1 : 0;
		int numDashes = (offset > 0) ? 3 : 2;
		for (int i=0; i<number.length(); i++) {
			/* Insert a dash every 3 characters as long
			 * as you haven't used up all of your dashes
			 * and you aren't at the very first position
			 * in the string
			 */
			if ((i-offset)%3 == 0 && numDashes > 0 && i != 0) {
				tempString += '-';
				numDashes--;
			}
			tempString += number.charAt(i);
		}
		return tempString;
	}
}

//Main class
public class Ch13_Telephone {
	public static void main(String[] args) {
		//Prompt the user
		System.out.println("Enter a phone number (xxx-xxx-xxxx) to convert to numerical form: ");
		
		//Instantiate a PhoneNumber object
		Scanner input = new Scanner(System.in);
		PhoneNumber number = new PhoneNumber(input.nextLine());
		
		//Display the number
		System.out.println("The phone number in numerical form is:\n" + number.toString());
	}
}

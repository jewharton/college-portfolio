import java.util.Scanner;

public class A3_AdditionQuiz2 {
	public static void main(String[] args) {
		//Generate 2 random numbers
		int number1 = (int)(Math.random() * 100);
		int number2 = (int)(Math.random() * 100);
		
		//Create a Scanner
		Scanner input = new Scanner(System.in);
		
		System.out.print(
				"What is " + number1 + " + " + number2 + "? ");
		
		int answer = input.nextInt();
		
		System.out.println(
			number1 + " + " + number2 + " = " + answer + " is " +
			(number1 + number2 == answer));
	}
}
import java.util.Scanner;

public class A2_FahrenheitToCelsius {
	public static void main(String[] args) {
		//Initialize the input
		Scanner input = new Scanner(System.in);
		
		//Prompt the user
		System.out.print("Enter a degree in Celsius: ");
		
		//Accept a double, insert into "celsius"
		double celsius = input.nextDouble();
		
		//Calculate fahrenheit
		double fahrenheit = (9.0 / 5.0) * celsius + 32;
		
		System.out.println(celsius + " Celsius is " + fahrenheit + " Fahrenheit");
	}
}

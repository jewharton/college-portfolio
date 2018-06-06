import java.util.Scanner;

public class A6_TemperatureConverter {
	public static void main(String[] args) {
		//Initialize the input
		Scanner input = new Scanner(System.in);
		
		//Prompt the user to enter temperature
		System.out.print("Enter the temperature to convert: ");
		
		//Accept a double, insert into "celsius"
		double temperature = input.nextDouble();
		
		
		//Prompt the user to enter temperature unit
		System.out.print("Enter the unit to convert to (Fahrenheit or Celsius): ");
		
		//Accept a string
		String temperature_unit = input.next().toUpperCase();
		
		
		double converted_temperature = 0;
		
		if (temperature_unit.equals("FAHRENHEIT")) {
			converted_temperature = celsiusToFahrenheit(temperature);
		} else if (temperature_unit.equals("CELSIUS")) {
			converted_temperature = fahrenheitToCelsius(temperature);
		} else {
			System.out.println("Unit must be either Fahrenheit or Celsius.");
			System.exit(1);
		}
		
		System.out.println("Converted temperature is " + converted_temperature);
	}
	
	/** Convert from Celsius to Fahrenheit */
	public static double celsiusToFahrenheit(double celsius) {
		double fahrenheit = (9.0 / 5) * celsius + 32;
		return fahrenheit;
	}
	
	/** Convert from Fahrenheit to Celsius */
	public static double fahrenheitToCelsius(double fahrenheit) {
		double celsius = (5.0 / 9) * (fahrenheit - 32);
		return celsius;
	}
}

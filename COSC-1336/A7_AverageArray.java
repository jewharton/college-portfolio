import java.util.Scanner;

public class A7_AverageArray {
	
	public static int average(int[] array) {
		int sum = 0;
		for (int num: array) {
			sum += num;
		}
		int avg = sum/array.length;
		return avg;
	}
	
	public static double average(double[] array) {
		double sum = 0;
		for (double num: array) {
			sum += num;
		}
		double avg = sum/array.length;
		return avg;
	}
	
	public static void main(String[] args) {
		//Initialize the input
		Scanner input = new Scanner(System.in);
				
		//Prompt the user
		System.out.print("Enter 10 numbers to average: ");
		
		//Initialize numbers array
		double[] numbers = new double[10];
		for (int i = 0; i < 10; i++) {
			numbers[i] = input.nextInt();
		}
		
		//Get the average
		double averageNumber = average(numbers);
		
		//Print it
		System.out.println("The average of all 10 numbers is " + averageNumber);
	}
	
}

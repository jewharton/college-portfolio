import java.util.Scanner;

public class A5_Average {
	public static void main(String[] args) {
		//Initialize the input
		Scanner input = new Scanner(System.in);
		
		//Prompt the user
		System.out.print(" Enter an integer, the input ends if it is 0: ");
		
		//Accept numbers as long as it isn't 0
		int positive_count = 0;
		int negative_count = 0;
		double sum = 0.0;
		boolean end = false;
		
		while (!end) {
			double thisnumber = input.nextDouble();
			if (thisnumber == 0) {
				end = true;
			} else {
				sum += thisnumber;
				if (thisnumber > 0) {
					positive_count++;
				} else {
					negative_count++;
				}
			}
		}
		
		if ((positive_count + negative_count != 0)) {
			System.out.println("The number of positives is " + positive_count);
			System.out.println("The number of negatives is " + negative_count);
			System.out.println("The total is " + sum);
			System.out.println("The average is " + sum / (double)(positive_count + negative_count));
		} else {
			System.out.println("No numbers entered except 0");
		}
	}
}

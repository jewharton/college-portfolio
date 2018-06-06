import java.util.Scanner;

public class A2_CalculateTip {
	public static void main(String[] args) {
		//Initialize the input
		Scanner input = new Scanner(System.in);
		
		//Prompt the user
		System.out.print("Enter the subtotal and gratuity rate: ");
		
		//Accept 2 doubles, insert into respective variables
		double subtotal = input.nextDouble();
		double gratuityRate = input.nextDouble();
		
		//Calculate the total and gratuity
		double gratuity = subtotal * (gratuityRate/100);
		double total = subtotal + gratuity;
		
		System.out.println("The gratuity is $" + gratuity + " and total is $" + total);
	}
}

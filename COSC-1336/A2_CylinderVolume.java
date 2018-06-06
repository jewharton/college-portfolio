import java.util.Scanner;

public class A2_CylinderVolume {
	public static void main(String[] args) {
		//Define PI
		final double PI = 3.14159;
		
		//Initialize the input
		Scanner input = new Scanner(System.in);
		
		//Prompt the user
		System.out.print("Enter the radius and length of a cylinder: ");
		
		//Accept 2 doubles, insert into respective variables
		double radius = input.nextDouble();
		double length = input.nextDouble();
		
		//Calculate area and volume
		double area = radius * radius * PI;
		double volume = area * length;
		
		System.out.println("The area is " + area);
		System.out.println("The volume is " + volume);
	}
}

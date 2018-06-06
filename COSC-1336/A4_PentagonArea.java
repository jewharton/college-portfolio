import java.util.Scanner;

public class A4_PentagonArea {
	public static void main(String[] args) {
		//Initialize the input
		Scanner input = new Scanner(System.in);
		
		//Prompt the user
		System.out.print("Enter a length from the center to a vertex: ");
		
		//Accept a double, insert into "radius"
		double radius = input.nextDouble();
		
		//Calculate side length
		double side = 2 * radius * Math.sin(Math.PI / 5);
		
		//Calculate area
		double area = (5 * Math.pow(side, 2)) / (4 * Math.tan(Math.PI / 5));
		
		System.out.println("The area of the pentagon is " + Math.round(area * 100.0) / 100.0);
	}
}

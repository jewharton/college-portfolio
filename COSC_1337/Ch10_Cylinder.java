/* Ch10_Cylinder.java
Jeremy Wharton
Programming Fundamentals II
COSC 1337 2801
2/28/2018

This program builds on the previous assignment.  A typical cylinder is built on the basis of a circle.  In your last assignment you created a class Circle.  

Your task for this assignment is to use the idea of Aggregation to build a class called Cylinder.  (10 pts)
The private members of the Cylinder should be a Circle object as well as a double variable for the height. (20 pts)
Create appropriate constructors for the Cylinder class including a copy constructor. You may also want to create a copy constructor for the Circle class as well. (20 pts)
Along with the appropriate getters and setters, create methods that determine the area of a cylinder (using methods that the Circle object has already provided), and the volume of a cylinder. (20 pts)
Create a demo class to demonstrate your methods. Create an object of type Cylinder. Call the getters setters and use the constructors. Ask the user the size of the radius and height and print out the Volume of the Cylinder. (30 pts)
*/


import java.util.Scanner;

//Circle class
class Circle {
	//Radius of the Circle object
	private double radius;
	
	//No-arg constructor; automatically set radius
	Circle() {
		this.radius = 1;
	}
	
	//Parameterized constructor; explicitly set radius
	Circle(double radiusNum) {
		this.radius = radiusNum;
	}
	
	//Get the area of the Circle object
	public double getArea() {
		//Area = pi*radius^2
		return (Math.PI * Math.pow(this.radius, 2));
	}
	
	//Get the circumference of the Circle object
	public double getCircumference() {
		//Circumference = 2*pi*radius
		return (2 * Math.PI * this.radius);
	}
	
	//Get the diameter of the Circle object
	public double getDiameter() {
		//Diameter = 2*radius
		return (2*this.radius);
	}
	
	//Get the radius of the Circle object
	public double getRadius() {
		return this.radius;
	}
	
	//Set the radius of the Circle object
	public void setRadius(double radiusNum) {
		this.radius = radiusNum;
	}
}

//Cylinder class
class Cylinder {
	//Circle of the Cylinder object
	private Circle circle;
	
	//Height of the Cylinder object
	private double height;
	
	//No-arg constructor; automatically set radius and height
	Cylinder() {
		this.circle = new Circle();
		this.height = 1;
	}
	
	//Parameterized constructor; explicitly set radius
	Cylinder(double radiusNum, double heightNum) {
		this.circle = new Circle(radiusNum);
		this.height = heightNum;
	}
	
	//Copy constructor
	Cylinder(Cylinder cylinder) {
		this.circle = new Circle(cylinder.circle.getRadius());
		this.height = cylinder.getHeight();
	}
	
	//Set the radius of the Cylinder's Circle object
	public void setRadius(double radiusNum) {
		this.circle.setRadius(radiusNum);
	}
	
	//Set the height of the Cylinder
	public void setHeight(double heightNum) {
		this.height = heightNum;
	}
	
	//Get the diameter of the Cylinder's Circle object
	public double getDiameter() {
		return this.circle.getDiameter();
	}
	
	//Get the area of the Cylinder's Circle object
	public double getRadius() {
		return this.circle.getRadius();
	}
	
	//Get the area of the Cylinder's Circle object
	public double getArea() {
		return this.circle.getArea();
	}
	
	//Get the height of the Cylinder object
	public double getHeight() {
		return this.height;
	}
	
	//Get the volume of the Cylinder object
	public double getVolume() {
		return this.getArea() * this.getHeight();
	}
}

public class Ch10_Cylinder {
	//Round a number to a specified number of decimal places
	public static double round(double number, int decimalPlaces) {
		//Casting to int takes off the
		//fractional portion of a double
		
		//Number to multiply and divide by
		int multFactor = (int)Math.pow(10, decimalPlaces);
		
		/* roundAdd is the number to add in the calculation to
		 * round to the nearest whole number (up or down) instead
		 * of just rounding down */
		double roundAdd = number < 0 ? -5 : 5;
		roundAdd = roundAdd / Math.pow(10, decimalPlaces+1);
		
		//Return the rounded number
		return ( (double)((int)((number + roundAdd) * multFactor)) / multFactor );
	}
	
	//Main method
	public static void main(String[] args) {
		//Initialize the input
		Scanner input = new Scanner(System.in);
		
		//Create a new cylinder
		Cylinder thisCylinder = new Cylinder();
		
		//Prompt the user
		System.out.print("Enter the radius and height of a cylinder to calculate its volume: ");
		
		//Set the radius to the entered value
		thisCylinder.setRadius(input.nextDouble());
		
		//Set the height to the entered value
		thisCylinder.setHeight(input.nextDouble());
		
		//Notify the user of the Cylinder's properties
		//rounded to 4 decimal points
		System.out.println("The cylinder's properties are as follows:");
		System.out.println(" * Area of the base: " + round(thisCylinder.getArea(), 4) + " square units");
		System.out.println(" * Volume:           " + round(thisCylinder.getVolume(), 4) + " units");
		System.out.println(" * Diameter:         " + round(thisCylinder.getDiameter(), 4) + " units");
		System.out.println(" * Radius:           " + thisCylinder.getRadius() + " units");
	}
}
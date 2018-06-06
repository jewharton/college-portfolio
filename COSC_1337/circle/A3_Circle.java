/* A3_Circle.java
Jeremy Wharton
Programming Fundamentals II
COSC 1337 2801
2/12/2018

For this module you are exploring the infant stages of object-oriented programming by create abstract data types.  For this assignment, you are to create a class called Circle.  

Circle should have a double attribute called radius. (5pts) 

The class should have appropriate getters and setters. (20pts)

A no-arg and a parameterized constructor. (20pts)

Also include the following member method: getArea()(10pts), getCircumference() (10pts) and getDiameter()(10pts).

Write a demo program that creates an object of the Circle class you created and demonstrates uses of the methods defined.

Circle object (10pts)

Call the setters after user input (5pts)

Call the getter to output the answers (5pts)

You program should declare a package named circle. (5pts)
*/

package circle;

import java.util.Scanner;

//Circle class
class Circle {
	//Radius of the Circle object
	private double radius;
	
	//No-arg constructor; automatically set radius
	Circle() {
		radius = 1;
	}
	
	//Parameterized constructor; explicitly set radius
	Circle(double radiusNum) {
		radius = radiusNum;
	}
	
	//Get the area of the Circle object
	public double getArea() {
		//Area = pi*radius^2
		return (Math.PI * Math.pow(radius, 2));
	}
	
	//Get the circumference of the Circle object
	public double getCircumference() {
		//Circumference = 2*pi*radius
		return (2 * Math.PI * radius);
	}
	
	//Get the diameter of the Circle object
	public double getDiameter() {
		//Diameter = 2*radius
		return (2*radius);
	}
	
	//Get the radius of the Circle object
	public double getRadius() {
		return radius;
	}
	
	//Set the radius of the Circle object
	public void setRadius(double radiusNum) {
		radius = radiusNum;
	}
}

public class A3_Circle {
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
		
		//Create a new circle
		Circle thisCircle = new Circle();
		
		//Allow the user to modify the circle
		modifyCircle(thisCircle, input);
	}
	
	//Allow the user to modify the circle
	//and display the circle's properties afterwards
	public static void modifyCircle(Circle thisCircle, Scanner input) {
		//Prompt the user
		System.out.print("Enter the radius of a circle to calculate its properties: ");
		
		//Set the radius to the entered value
		thisCircle.setRadius(input.nextDouble());
		
		//Notify the user of the circle's properties
		//rounded to 4 decimal points
		System.out.println("The circle's properties are as follows:");
		System.out.println( " * Area: " + round(thisCircle.getArea(), 4) + " square units");
		System.out.println(" * Circumference: " + round(thisCircle.getCircumference(), 4) + " units");
		System.out.println(" * Diameter: " + round(thisCircle.getDiameter(), 4) + " units");
		System.out.println(" * Radius: " + thisCircle.getRadius() + " units");
		System.out.println("--------------------");
		
		//Start the process over again
		modifyCircle(thisCircle, input);
	}
}

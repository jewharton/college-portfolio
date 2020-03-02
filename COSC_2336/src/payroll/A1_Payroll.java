/*A1_Payroll.java
Jeremy Wharton
Programming Fundamentals III
COSC 2336 2801
9/2/2018
In this assignment, you are to create a class named Payroll.  

In the class, you are to have the following data members:

name: String (5 pts)
id: String   (5 pts)
hours: int   (5 pts)
rate: double (5 pts)
private members (5 pts)
You are to create no-arg and parameterized constructors and the appropriate setters(accessors) and getters (mutators).  (20 pts)

The class definition should also handle the following exceptions:

An employee name should not be empty, otherwise an exception should be thrown. (10 pts)
An employee id should have the form LLNNNN.  If that form is not received, an exception should be thrown. (10 pts)
An employee's hours should neither be negative nor greater than 84.  An exception should be thrown otherwise. (10 pts)
An employee's pay rate should neither be negative nor greater than 25.00. An exception should be thrown otherwise. (10 pts)
Demonstrate this class in a program (separate class or in the same class).  (5 pts)

The exception messages should be appropriate to the specific exception that has occurred. (5 pts)

Create a package payroll for the project. (5 pts)
*/

package payroll;

import java.text.DecimalFormat;

//Employee class
class Employee {
	private String name;	//Employee name
	private String id;		//Employee id
	private int hours;		//Employee hours worked
	private double rate;	//Employee pay rate
	
	//Get employee name
	public String getName() {
		return this.name;
	}
	
	//Set employee name
	public void setName(String str) {
		if (str.isEmpty() || str.replace(" ", "").length() < 1)
			throw new IllegalArgumentException("Employee name must not be empty.");
		this.name = str;
	}
	
	//Get employee id
	public String getID() {
		return this.id;
	}
	
	//Set employee id
	public void setID(String str) {
		str = str.toUpperCase();
		if (!str.matches("[A-Z]{2}[0-9]{4}"))	//2 letters followed by 4 numbers
			throw new IllegalArgumentException("Employee ID must be in the format LLNNNN, where L is a letter and N is a number.");
		this.id = str;
	}
	
	//Get number of hours
	public int getHours() {
		return this.hours;
	}
	
	//Set number of hours
	public void setHours(int num) throws IllegalArgumentException {
		if (num < 0)
			throw new IllegalArgumentException("Number of hours must not be less than 0.");
		this.hours = num;
	}
	
	//Get pay rate
	public double getPayRate() {
		return this.rate;
	}
	
	//Set pay rate
	public void setPayRate(double num) {
		if (num < 0 || num > 25.0)
			throw new IllegalArgumentException("Pay rate must be between 0 and 25.00.");
		this.rate = num;
	}
	
	//Get Employee's pay
	public double getCurrentPay() {
		return this.getPayRate()*this.getHours();
	}
	
	//No-arg constructor
	Employee() throws IllegalArgumentException {
		throw new IllegalArgumentException("Employee must be created with a name.");
	}
	
	//Employee name
	Employee(String namestr) {
		this.setName(namestr);
	}
	
	//Employee name and ID
	Employee(String namestr, String idstr) {
		this(namestr);
		this.setID(idstr);
	}
	
	//Employee name, ID, and hours
	Employee(String namestr, String idstr, int numHours) {
		this(namestr, idstr);
		this.setHours(numHours);
	}
	
	//Employee name, ID, hours, and rate
	Employee(String namestr, String idstr, int numHours, double payRate) {
		this(namestr, idstr, numHours);
		this.setPayRate(payRate);
	}
	
	//Return string representation of the Employee
	public String toString() {
		return this.getName() + " (ID: " + this.getID() + ")";
	}
}

//Main class
public class A1_Payroll {
	public static void main(String[] args) {
		/* Create various employees and display
		 * their earnings to the user. */
		Employee[] employees = {
			new Employee("John Doe", "JD0001", 40, 24.30),
			new Employee("Ann Boroughs", "AB2753", 37, 23.90),
			new Employee("Frank Jones", "FJ0389", 25, 22.20),
			new Employee("Mary Williams", "MW5830", 32, 21.70)
		};
		
		DecimalFormat formatter = new DecimalFormat("0.00");
		for (Employee thisEmployee: employees) {
			String formattedPay = formatter.format(thisEmployee.getCurrentPay());
			System.out.println(thisEmployee.toString() + " earned " + formattedPay + " this week.");
		}
	}
}

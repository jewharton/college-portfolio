/* Ch11_Ships.java
Jeremy Wharton
Programming Fundamentals II
COSC 1337 2801
3/11/2018

Design a Ship class that the following members:
A field for the name of the ship (a string).
A field for the year that the ship was built (a string).
A constructor and appropriate accessors and mutators.
A toString method that displays the ship’s name and the year it was built.

Design a CruiseShip class that extends the Ship class. The CruiseShip class should have the following members:
A field for the maximum number of passengers (an int).
A constructor and appropriate accessors and mutators.
A toString method that overrides the toString method in the base class. The CruiseShip class’s toString method should display only the ship’s name and the maximum number of passengers.

Design a CargoShip class that extends the Ship class. The CargoShip class should have the following members:
A field for the cargo capacity in tonnage (an int).
A constructor and appropriate accessors and mutators.
A toString method that overrides the toString method in the base class. The CargoShip class’s toString method should display only the ship’s name and the ship’s cargo capacity.

Demonstrate the classes in a program that has a Ship array. Assign various Ship, CruiseShip, and CargoShip objects to the array elements. The program should then step through the array, calling each object’s toString method.
*/


//Ship class
class Ship {
	//Ship name
	private String name;
	
	//Year the ship was build
	private String yearBuilt;
	
	//No-arg constructor
	Ship() {
		this("Unknown", "Unknown");
	}
	
	//Name constructor
	Ship(String shipName) {
		this(shipName, "Unknown");
	}
	
	//Name and year built constructor
	Ship(String shipName, String shipYear) {
		name = shipName;
		yearBuilt = shipYear;
	}
	
	//Set the ship's name
	public void setName(String shipName) {
		name = shipName;
	}
	
	//Set the year the ship was built
	public void setYearBuilt(String year) {
		yearBuilt = year;
	}
	
	//Get the ship's name
	public String getName() {
		return name;
	}
	
	//Get the year the ship was built
	public String getYearBuilt() {
		return yearBuilt;
	}
	
	//Return the string representation of the ship
	public String toString() {
		return "Ship Name: " + this.getName() + "\nYear Built: " + this.getYearBuilt();
	}
}

//CruiseShip class
class CruiseShip extends Ship {
	//Maximum number of passengers
	private int passengers;
	
	//No-arg constructor
	CruiseShip() {
		this("Unknown", "Unknown", 0);
	}
	
	//Name constructor
	CruiseShip(String shipName) {
		this(shipName, "Unknown", 0);
	}
	
	//Name and year built constructor
	CruiseShip(String shipName, String year) {
		this(shipName, year, 0);
	}
	
	//Name, year built, and max passenger constructor
	CruiseShip(String shipName, String year, int maxPassengers) {
		this.setName(shipName);
		this.setYearBuilt(year);
		passengers = maxPassengers;
	}
	
	//Set max passengers
	public void setPassengers(int maxPassengers) {
		passengers = maxPassengers;
	}
	
	//Get max passengers
	public int getPassengers() {
		return passengers;
	}
	
	//Return the string representation of the cruise ship
	public String toString() {
		return "Ship Name: " + this.getName() + "\nMaximum number of passengers: " + this.getPassengers();
	}
}

//CargoShip class
class CargoShip extends Ship {
	//Cargo capacity
	private int tonnage;
	
	//No-arg constructor
	CargoShip() {
		this("Unknown", "Unknown", 0);
	}
	
	//Name constructor
	CargoShip(String shipName) {
		this(shipName, "Unknown", 0);
	}
	
	//Name and year built constructor
	CargoShip(String shipName, String year) {
		this(shipName, year, 0);
	}
	
	//Name, year built, and cargo capacity constructor
	CargoShip(String shipName, String year, int maxCapacity) {
		this.setName(shipName);
		this.setYearBuilt(year);
		tonnage = maxCapacity;
	}
	
	//Set max capacity
	public void setTonnage(int maxCapacity) {
		tonnage = maxCapacity;
	}
	
	//Get max capacity
	public int getTonnage() {
		return tonnage;
	}
	
	//Return the string representation of the cargo ship
	public String toString() {
		return "Ship Name: " + this.getName() + "\nTonnage: " + this.getTonnage();
	}
}

//Main class
public class Ch11_Ships {
	//Main method
	public static void main(String[] args) {
		/* Create various ships and
		 * display their information
		 * to the user. */
		
		//Array that holds the ships
		Ship[] shipArray = {
				new Ship("Lake Drifter", "2011"),
				new CruiseShip("Cruiser V", "2000", 4827),
				new CargoShip("Supply Carrier", "2007", 150000)
			};
		
		/* Iterate through the ship
		 * array and call each ship's
		 * toString method.
		 */
		for (Ship thisShip : shipArray) {
			System.out.println(thisShip.toString()+"\n");
		}
	}
}

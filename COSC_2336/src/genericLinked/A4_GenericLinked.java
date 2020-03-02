/*A4_GenericLinked.java
Jeremy Wharton
Programming Fundamentals III
COSC 2336 2801
10/21/2018
Your job in this assignment is to create a generic linked list.  The linked list should have the ability to perform the following actions:
(If you use the Java library functions for a LinkedList, you will not be able to receive full credit, it will be a passing score but not full credit.)
 * check if the list is empty
 * check the size of the list
 * add data to the list
 * add data to a specific (valid) location in the list
 * remove an item from the list based on position
 * remove an item from the list based on data value
 * removes all elements from the list
 * gets an item from the list based on the position
 * outputs the contents of the list 
Use the dat file from the previous assignment (Generic Quicksort) to demonstrate effectiveness of the linked list you created.
(Hint: Create a main module that demonstrates your methods.)
*/

package genericLinked;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Class for the Node of the GenericLinkedList
class GenericLinkedNode<E> {
	E element;					//This node element
	GenericLinkedNode<E> next;	//Next node element
	GenericLinkedNode<E> prev;	//Previous node element
	
	//Constructor
	GenericLinkedNode(E elem) {
		element = elem;
	}
}

//Generic Linked List class
class GenericLinkedList<E> {
	GenericLinkedNode<E> head;	//First member of the list
	
	/** Check if the list is empty.
	 * @return true or false. */
	boolean isEmpty() {
		return head == null;
	}
	
	/** Return the size of the list.
	 * @return the list size. */
	int size() {
		if (this.isEmpty()) return 0;
		int i = 0;								//Iteration index
		GenericLinkedNode<E> current = head;	//Current node in iteration
		while (current != null) {
			i++;
			current = current.next;
		}
		return i;
	}
	
	/** Add data to the list at a certain position.
	 * @param elem - The element to add; pos - Position of the element */
	void addAt(GenericLinkedNode<E> elem, int pos) {
		if (this.isEmpty()) {	//If the list is empty, set head = elem
			head = elem;
			return;
		}
		if (pos == 0) {			//Special condition for the start of the list
			elem.next = head;
			head.prev = elem;
			head = elem;
		}
		
		GenericLinkedNode<E> current = head;	//Current node in iteration
		for (int i=0; i<=pos; i++) {
			if (current.next == null) {			//Cut short because too few elements
				current.next = elem;
				elem.prev = current;
				break;
			}
			if (i != pos) {						//Skip if this isn't the needed element
				current = current.next;
				continue;
			}
			current.prev.next = elem;
			elem.next = current;
			elem.prev = current.prev;
			current.prev = elem;
			break;
		}
	}
	
	/** Add data to the list.
	 * @param elem - The element to add */
	void add(GenericLinkedNode<E> elem) {
		addAt(elem, this.size()+1);
	}
	
	/** Remove data in the list from a certain position.
	 * @param pos - Position of the element */
	void removeFrom(int pos) {
		if (this.isEmpty()) return;		//Nothing to remove
		if (pos >= this.size()) return;
		if (pos == 0) {
			head = head.next;
			head.prev = null;
			return;
		}
		
		GenericLinkedNode<E> current = head;	//Current node in iteration
		for (int i=0; i<=pos; i++) {
			if (i != pos) {						//Skip if this isn't the needed element
				current = current.next;
				continue;
			}
			if (current.next == null) {
				current.prev.next = null;
				return;
			}
			current.prev.next = current.next;
			current.next.prev = current.prev;
		}
	}
	
	/** Remove first instance of a node with
	 * the element matching val in the list.
	 * @param val - The value to compare to. */
	void remove(E val) throws IllegalArgumentException {
		if (this.isEmpty()) return;		//Nothing to remove
		
		GenericLinkedNode<E> current = head;	//Current node in iteration
		while (current != null) {
			E val2 = current.element;			//Value to check val against
			boolean equals;						//If val and val2 are equal
			
			if (val instanceof Number) {
				equals = ((Number)val).equals((Number)val2);
			}else if (val instanceof Character) {
				equals = ((Character)val).equals((Character)val2);
			} else if (val instanceof String) {
				equals = ((String)val).equals((String)val2);
			} else
				throw new IllegalArgumentException("Unsupported type '"+val.getClass()+"' for comparison");
			
			if (equals) {
				if (current == head) {
					head = head.next;
					head.prev = null;
					return;
				}
				if (current.next == null) {
					current.prev.next = null;
					return;
				}
				current.prev.next = current.next;
				current.next.prev = current.prev;
				return;
			}
			
			current = current.next;
		}
	}
	
	/** Remove all items from the list. */
	void clear() {
		head = null;
	}
	
	/** Get data in the list at a certain position.
	 * @param pos - Position */
	E get(int pos) throws IndexOutOfBoundsException {
		if (pos >= this.size())
			throw new IndexOutOfBoundsException("Index "+pos+" is out of bounds. Must be less than "+this.size()+".");
		
		GenericLinkedNode<E> current = head;	//Current node in iteration
		int i = 0;								//Iteration index
		while (current != null) {
			if (i == pos)
				return current.element;
			current = current.next;
			i++;
		}
		return null;
	}
	
	/** Output the contents of the list. */
	void print() {
		GenericLinkedNode<E> current = head;	//Current node in iteration
		while (current != null) {
			System.out.println(" - "+current.element.toString());
			current = current.next;
		}
	}
}

public class A4_GenericLinked {
	public static void main(String[] args) throws FileNotFoundException {
		GenericLinkedList<String> list = new GenericLinkedList<String>();
		
		//Check if names file exists
		String filePath = System.getProperty("user.home")+"\\Desktop\\names.dat";
		File namesFile = new File(filePath);
		if (!namesFile.exists()) {
			System.out.println(filePath+" does not exist.");
			System.exit(0);
		}
		
		//Initialize input
		try (Scanner input = new Scanner(namesFile)) {
			while (true) {
				//If there are no more lines
				if (!input.hasNext())
					break;
				
				list.add(new GenericLinkedNode<>(input.nextLine()));
			}
		}
		System.out.println("Initial state of list:");
		list.print();
		
		System.out.println("\nList after adding an element:");
		list.add(new GenericLinkedNode<>("Bobby"));
		list.print();
		
		System.out.println("\nList after removing the first element:");
		list.removeFrom(0);
		list.print();
	}
}

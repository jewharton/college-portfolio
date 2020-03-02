/*A6_SetOperations.java
Jeremy Wharton
Programming Fundamentals III
COSC 2336 2801
11/4/2018
Use the Sets and Maps Mathematics Review to understand basic set operations.
Go over the practice problem provided under this module titled Sets and Maps Practice.
You may use the Sets and Maps Starter Solution to help you solve the following operations using Java set operations. 
Solve the following Set Operations using Java code as shown on the sample Sets and Maps Starter Solution. 

1) A’
2) B’
3) A Union B
4) A' Union B'
5) A Intersection B
6) A' Intersection B'
7) A' Intersection B
8) A Intersection B'
9) A' Union B
10) A Union B'
 */

package setOperations;

import java.util.*;

public class A6_SetOperations {
	/** Return difference of setA and setB */
	public static <E> Set<E> complement(Set<E> setA, Set<E> setB) {
		Set<E> setC = new TreeSet<E>();
		setC.addAll(setB);				//Add all elements of setB
		setC.removeAll(setA);			//Remove all elements of setA
		return setC;
	}
	
	/** Return union of setA and setB */
	public static <E> Set<E> union(Set<E> setA, Set<E> setB) {
		Set<E> setC = new TreeSet<E>();
		setC.addAll(setA);				//Add all elements of setA
		setC.removeAll(setB);			//Remove all elements of setB to avoid duplicates
		setC.addAll(setB);				//Add all elements of setB
		return setC;
	}
	
	/** Return intersection of setA and setB */
	public static <E> Set<E> intersection(Set<E> setA, Set<E> setB) {
		Set<E> setC = new TreeSet<E>();
		
		//Add all elements both in setA and setB
		for (E elem : setA)
			if (setB.contains(elem))
				setC.add(elem);
		
		return setC;
	}
	
	public static void main(String[] args) {
		//Create two tree sets (tree sets used because of proper ordering)
		Set<String> setA = new TreeSet<String>(Arrays.asList("Alpha", "Beta", "Gamma", "Delta", "Epsilon"));
		Set<String> setB = new TreeSet<String>(Arrays.asList("Delta", "Epsilon", "Zeta", "Eta", "Theta"));
		
		//Display the operations
		System.out.println(
				"A = "+						setA.toString()+"\n"+
				"B = "+						setB.toString()+"\n"+
				"\n"+
				"1) A' = "+					complement(setA, setB).toString()+"\n"+
				"2) B' = "+					complement(setB, setA).toString()+"\n"+
				"3) A Union B = "+			union(setA, setB).toString()+"\n"+
				"4) A' Union B' = "+		union(complement(setA,setB), complement(setB,setA)).toString()+"\n"+
				"5) A Intersection B = "+	intersection(setA,setB).toString()+"\n"+
				"6) A' Intersection B' = "+	intersection(complement(setA,setB),complement(setB,setA)).toString()+"\n"+
				"7) A' Intersection B = "+	intersection(complement(setA,setB),setB).toString()+"\n"+
				"8) A Intersection B' = "+	intersection(setA,complement(setB,setA)).toString()+"\n"+
				"9) A' Union B = "+			union(complement(setA,setB), setB).toString()+"\n"+
				"10) A Union B' = "+		union(setA, complement(setB,setA)).toString()
		);
	}

}

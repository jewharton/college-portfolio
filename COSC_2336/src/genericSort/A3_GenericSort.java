/*A3_GenericSort.java
Jeremy Wharton
Programming Fundamentals III
COSC 2336 2801
10/7/2018
This assignment simply requests that you take the quicksort algorithm that you have been working with and make it generic.
You are to demonstrate the effectiveness of your work by defining a main method that passes a list or array of Integers, Doubles, and Strings and sorts them all.
The package should be genericSort.
*/

package genericSort;

import java.util.ArrayList;

//Generic ArrayList class
class GenericList<T> extends ArrayList<T> {
	/**
	 * Quicksort the GenericList
	 * @return the quicksorted list
	 */
	public GenericList<T> quicksort() {
		int size = this.size();
		if (size<=1)
			return this;
		int pivot = size%2==0 ? size/2-1 : size/2+1;	//Pivot number
		GenericList<T> left = new GenericList<T>();		//Less than the pivot value
		GenericList<T> right = new GenericList<T>();	//Greater than the pivot value
		for (int i=this.size()-1; i>=0; i--) {			//Iterate backwards through the list
			T element = this.get(i);
			if (compareTo(element,this.get(pivot)) <= 0)	//Value is < or = pivot value so move it left
				left.add(element);
			else											//Else move it right
				right.add(element);
		}
		this.clear();
		
		this.addAll(left.quicksort());		//Combine sorted left side
		this.addAll(right.quicksort());		//with sorted right side
		
		return this;
	}
	
	/** Compare one value to another. Must be numeric or string.
	 * @return 1 if v1 > v2, -1 if v1 < v2, else returns 0
	 * @param v1 - value 1; v2 - value 2
	 */
	public int compareTo(T v1, T v2) throws IllegalArgumentException {
		if (v1 instanceof Number) {
			double n1 = ((Number)v1).doubleValue();
			double n2 = ((Number)v2).doubleValue();
			return n1<n2 ? -1 : n1>n2 ? 1 : 0;
		} else if (v1 instanceof String) {
			char c1 = ((String)v1).toUpperCase().charAt(0);
			char c2 = ((String)v2).toUpperCase().charAt(0);
			return c1<c2 ? -1 : c1>c2 ? 1 : 0;
		} else
			throw new IllegalArgumentException("Unsupported type for compareTo.");
	}
}

public class A3_GenericSort {
	public static void main(String[] args) {
		//List of ints
		GenericList<Integer> list = new GenericList<>();
			list.add(1);
			list.add(5);
			list.add(4);
			list.add(7);
			list.add(3);
		
		System.out.println("Unsorted integer list:");
		for (Integer n : list)
			System.out.println(" - "+n);
		System.out.println("");
		
		System.out.println("Sorted integer list:");
		list.quicksort();
		for (Integer n : list)
			System.out.println(" - "+n);
		System.out.println("");
		
		//List of strings
		GenericList<String> strlist = new GenericList<>();
			strlist.add("Mary");
			strlist.add("Bob");
			strlist.add("Jones");
			strlist.add("Arthur");
			strlist.add("Cathy");
		
		System.out.println("Unsorted string list:");
		for (String str : strlist)
			System.out.println(" - "+str);
		System.out.println("");
		
		System.out.println("Sorted string list:");
		strlist.quicksort();
		for (String str : strlist)
			System.out.println(" - "+str);
		System.out.println("");
		
		//List of doubles
		GenericList<Double> doublelist = new GenericList<>();
		doublelist.add(8.5);
		doublelist.add(6.22);
		doublelist.add(2.9);
		doublelist.add(0.3);
		doublelist.add(3.7);
		
		System.out.println("Unsorted double list:");
		for (Double n : doublelist)
			System.out.println(" - "+n);
		System.out.println("");
		
		System.out.println("Sorted double list:");
		doublelist.quicksort();
		for (Double n : doublelist)
			System.out.println(" - "+n);
		System.out.println("");
	}
}
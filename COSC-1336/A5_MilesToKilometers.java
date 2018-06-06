public class A5_MilesToKilometers {
	public static void main(String[] args) {
		//Print the table header
		System.out.println("Miles		Kilometers");
		
		//Print the rest of the table for all integers between 1 and 10
		for (int count = 1; count <= 10; count++) {
			double kilometers = (double)count * 1.609;
			System.out.printf("%d		%.3f\n", count, kilometers);
		}
	}
}

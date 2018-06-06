public class A6_PrimesBefore10000 {
	/** Check whether number is prime */
	public static boolean isPrime(int number) {
		for (int divisor = 2; divisor <= number / 2; divisor++) {
			if (number % divisor == 0) {	// If true, number is not prime
				return false;	// Number is not a prime
			}
		}
		
		return true;	// Number is prime
	}
	
	/** Find number of prime numbers before 10000 */
	public static void main(String[] args) {
		int number_of_primes = 0;
		for (int number = 1; number < 10000; number++) {
			if (isPrime(number)) {
				number_of_primes += 1;
			}
		}
		System.out.println("The number of primes before 10,000 is " + number_of_primes);
	}
}
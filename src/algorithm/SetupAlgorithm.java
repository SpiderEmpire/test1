package algorithm;

import java.util.Random;

public class SetupAlgorithm {
	public int e, g, u, s, G1, G2,q;

	public static void setup() {
		// input security parameter
		// output public system paramater(include a description of a nite
		// message space M, and a description of a nite
		// ciphertext space C.) and master key ->private
		int num = 0;
		String primeNumbers = "";
		for (int i = 1; i <= 10; i++) {
			int counter = 0;
			for (num = i; num >= 1; num--) {
				if (i % num == 0) {
					counter = counter + 1;
				}
			}
			if (counter == 2) {
				// Appended the Prime number to the String
				primeNumbers = primeNumbers + i + " ";
			}
		}
		System.out.println("Prime numbers from 1 to n are :");
		System.out.println(primeNumbers);
		
		
		//////////////////////////
		//u=(int) Math.pow(g, s);
	}

	public static int masterkey() {

		Random r = new Random();
		int s = r.nextInt(10) + 1;
		return s;
	}
}

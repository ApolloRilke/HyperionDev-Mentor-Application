import java.util.InputMismatchException;
import java.util.Scanner;

public class SayTheNumber {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		long number = 1;
		System.out.println("Enter a positive integer. (-1 to quit) \n");
		while (number != -1) {
			while (true) {
				try {
					System.out.println("Enter an integer: ");
					number = scanner.nextLong();
				} catch (InputMismatchException e) {
					System.out.println("Please enter a number");
					scanner.nextLine();
					continue;
				}
				if ((number < 0) && (number != -1)) {
					System.out.println("Enter a positive integer");
					continue;
				}
				break;
			}
			if (number == -1) {
				System.out.println("Goodbye");
				scanner.close();
				System.exit(0);
			}
			System.out.println(numberToWords(number));
		}
	}

	public static String numberToWords(long num) {
		// Return case if num is 0.
		if (num == 0) {
			return "Zero.";
		}

		// Number is put into words.
		String solution = readNumbers(num);

		// The words must be formatted for correct grammar and endings.
		if (solution.endsWith(",")) {
			// Cannot end with a comma, must be removed.
			solution = solution.substring(0,solution.length() - 1);
		}
		if (solution.endsWith(" and")) {
			// Cannot end with " and", must be removed.
			solution = solution.substring(0,solution.length() - 4);
		}
		// Capitalise first letter
		solution = solution.substring(0, 1).toUpperCase() + solution.substring(1);

		return solution + ".";
	}

	public static String readNumbers(long num) {

		// Initalise words in a list for reference. List position corresponds to the appropriate
		// number, eg underTwentyList[11] = "eleven". 
		String[] underTwentyList = {"", "one", "two", "three", "four", "five", "six", "seven", 
				"eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", 
				"sixteen", "seventeen", "eighteen", "nineteen"};
		String[] tensList = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", 
				"eighty", "ninety"};

		// Initialise the bases of 10 for reference in conditionals:
		int ten = 10;
		int oneHundred = 100;
		int oneThousand = 1000;
		int oneMillion = 1000000;
		int oneBillion = 1000000000;
		long oneTrillion = 1000000000000L;
		long oneQuadrillion = 1000000000000000L;

		// Initialise string for constructing the number
		String words = "";

		// Reference underTwentyList accordingly.
		if (num < 20) {
			words = underTwentyList[(int) num];
		}

		// Ensure correct punctuation, -, correctly placed between ten' and one's units.
		// eg "twenty-three" vs "twenty three" and "twenty".
		else if ((num < oneHundred) && (num % ten != 0)) {
			words = tensList[(int) (num/ten)] + "-" + readNumbers(num%ten);
		}
		else if (num < oneHundred) {
			words = tensList[(int) (num/ten)] + " " + readNumbers(num%ten);
		}

		// Ensure correct placement of "and" after "hundred". eg "two hundred and one"
		// vs "two hundred"
		else if ((num < oneThousand) && (num % oneHundred != 0)) {
			words = readNumbers(num/oneHundred) + " hundred and " + 
					readNumbers(num%oneHundred);
		}
		else if (num < oneThousand) {
			words = readNumbers(num/oneHundred) + " hundred ";
		}

		// Ensure correct placement of "and" and comma after "thousand". eg "two thousand and one"
		// vs "two thousand, six hundred"
		else if ((num < oneMillion) && (num % oneThousand < oneHundred)) {
			words = readNumbers(num / oneThousand) + " thousand and " + 
					readNumbers(num % oneThousand);
		}
		else if (num < oneMillion) {
			words = readNumbers(num/oneThousand) + " thousand, " + 
					readNumbers(num%oneThousand);
		}

		// Ensure correct placement of "and" and comma after "million". eg "two million and one."
		// vs "two million, six hundred."
		else if ((num <oneBillion) && (num % oneMillion < oneHundred)) {
			words = readNumbers(num/oneMillion) + " million and " + 
					readNumbers(num%oneMillion);
		}
		else if (num < oneBillion) {
			words = readNumbers(num/oneMillion) + " million, " + 
					readNumbers(num%oneMillion);
		}

		// Ensure correct placement of "and" and comma after "billion". eg "two billion and one."
		// vs "two billion, six hundred."
		else if ((num < oneTrillion) && (num % oneBillion < oneHundred)) {
			words = readNumbers(num/oneBillion) + " billion and " + 
					readNumbers(num%oneBillion);
		}
		else if ((num < oneTrillion)) {
			words = readNumbers(num/oneBillion) + " billion, " + 
					readNumbers(num%oneBillion);
		}

		// Ensure correct placement of "and" + comma after "trillion". eg "two trillion and one."
		// vs "two trillion, six hundred."
		else if ((num < oneQuadrillion) && (num % oneTrillion < oneHundred)) {
			words = readNumbers(num/oneTrillion) + " trillion and " + 
					readNumbers(num%oneTrillion);
		}
		else {
			words = readNumbers(num/oneTrillion) + " trillion, " + 
					readNumbers(num%oneTrillion);
		}

		// Remove white space before returning.
		return words.trim();
	}
}

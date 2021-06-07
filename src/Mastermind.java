import java.util.Random;
import java.util.Scanner;

public class Mastermind {
	static Random rnd = new Random();
	static Scanner scanner = null;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		
		String choice = "";
		
		while(!choice.equals("q")) {
		
		System.out.print("Enter max code length (3-10): ");
		String codeLenString = scanner.nextLine();
		int codeLen = Integer.parseInt(codeLenString);
		
		System.out.print("Enter max number of digits (2-8): ");
		String digitsString = scanner.nextLine();
		int digits = Integer.parseInt(digitsString);
		
		playGame(codeLen, digits);
		
		System.out.print("Enter 'q' to quit: ");
		choice = scanner.nextLine();
		
		}
	}

	static void playGame(int codeLength, int maxNum) {
		//Generate code
		String code = "";
		int[] digitCounts = new int[maxNum +1];
		for(int i = 0; i<digitCounts.length; i++) {
			digitCounts[i] = 0;
		}
		
		//int[] digitCounts = {0, 0, 0, 0};
		
		for(int i = 0; i < codeLength; i++) {
			int r = rnd.nextInt(maxNum +1); // so 3 is included
			code += Integer.toString(r); //add digit to code string
			digitCounts[r] = digitCounts[r] +1;
		}
		
		// get guess from player
		String guess = "";
		int attempts = 0;
		while(!code.equals(guess)) {
			System.out.print("Enter your guess: ");
			guess = scanner.nextLine();
			
			if(guess.length() != codeLength) {
				System.out.println("Guess must be " + codeLength + " digits long");
				continue;
			}
			
			attempts++;
			
			if(guess.equals(code)) {
				System.out.println("You got it in: " + attempts + " attempts");
				return;
			}
			
			//check digit counts
			int [] testCounts = new int [maxNum +1];
			for(int i = 0; i< testCounts.length; i++) {
				testCounts[i] = 0;
			}
			for(Character ch: guess.toCharArray()) {
				int num = Integer.parseInt(ch.toString());
				if(num >= 0 && num <= maxNum) {
					testCounts[num] = testCounts[num] +1;
				}
			}
			int numCt = 0; // number of correct digits
			for(int i = 0; i < testCounts.length; i++) {
				numCt += Math.min(testCounts[i], digitCounts[i]);
			}
			
			// correct digit and position
			int posCt = 0;
			for(int i = 0; i < guess.length(); i++) {
				if(code.charAt(i) == guess.charAt(i)){
					posCt++;
				}
			}
			
			System.out.println("Correct digits: " + numCt + " - correct digits and position: " +posCt);
			
		}
	}

}
